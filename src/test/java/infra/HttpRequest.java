package infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.entites.enums.HttpMethods;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ValidateJson;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequest {
    private static final Logger logger = LogManager.getLogger(HttpRequest.class);
    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Map<String, String> queryParams, Object requestBody, Map<String, String> headers, Class<T> clz) {
        logger.info("Current method name: " + Thread.currentThread().getStackTrace()[2].getMethodName());

        // Set the param of the request if found!
        if (queryParams != null) {
            String queryString = queryParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            url += "?" + queryString;
        }

        ClassicHttpRequest request;
        switch (httpMethods) {
            case POST -> {
                // Create an instance of HttpPost with the URL
                request = new HttpPost(url);

                // Set the request body for POST requests
                if (requestBody != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonBody = null;
                    try {
                        jsonBody = objectMapper.writeValueAsString(requestBody);
                        StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
                        request.setEntity(entity);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // Set an empty request body
                    request.setEntity(new StringEntity("", ContentType.APPLICATION_JSON));
                }
            }
            case GET -> {
                // Create an instance of HttpGet with the URL
                request = new HttpGet(url);

            }
            case PATCH -> {
                request = new HttpPatch(url);
                // Set the request body for PATCH requests
                if (requestBody != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonBody = null;
                    try {
                        jsonBody = objectMapper.writeValueAsString(requestBody);
                        StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
                        request.setEntity(entity);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            case DELETE -> {
                request = new HttpDelete(url);
            }
            default -> {
                logger.error("Error of Bad Method:\n");
                throw new RuntimeException("Bad Method!");
            }
        }
        // Set the headers of the request
        if (headers != null) {
            for (String key : headers.keySet()) {
                request.setHeader(key, headers.get(key));
            }
        }
        logger.info("Request info : " + request);
        // Create an instance of CloseableHttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Execute
            return execute(httpClient, request, clz);
        } catch (IOException e) {
            logger.error("Error of creating an instance of CloseableHttpClient:\n" + e);
            throw new RuntimeException("Error of creating an instance of CloseableHttpClient:\n" + e);
        }
    }

    public static <T> ResponseWrapper<T> execute(CloseableHttpClient httpClient, ClassicHttpRequest httpMethod, Class<T> clz) {
        logger.info("Current method name: " + Thread.currentThread().getStackTrace()[2].getMethodName());
        // Execute the request and get the response
        try (CloseableHttpResponse response = httpClient.execute(httpMethod)) {
            ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();

            // Get the response status code
            responseWrapper.setStatus(response.getCode());

            // Get the response entity
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            // Validate Json
            responseWrapper.setData(ValidateJson.validate(clz, responseBody));
            logger.info("Response info : " + responseWrapper);
            return responseWrapper;
        } catch (IOException | ParseException e) {
            logger.error("Failed to execute request : " + httpMethod.getRequestUri() + "\n" + e);
            throw new RuntimeException(e);
        }
    }

    //request without param & header
    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Object body, Class<T> clz) {
        return request(httpMethods, url, null, body, null, clz);
    }

    //request without param & header & body
    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Class<T> clz) {
        return request(httpMethods, url, null, null, null, clz);
    }

    // Overloaded method for request without header
    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Map<String, String> queryParams, Class<T> clz) {
        return request(httpMethods, url, queryParams, null, null, clz);
    }

    // Overloaded method for request without param
    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Object body, Map<String, String> headers, Class<T> clz) {
        return request(httpMethods, url, null, body, headers, clz);
    }
}
