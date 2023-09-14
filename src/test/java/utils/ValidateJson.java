package utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidateJson {
    private static final Logger logger = LogManager.getLogger(ValidateJson.class);

    public static <T> T validate(Class<T> clz, String jsonToCheck) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // Allow unquoted field names

        try {
            return objectMapper.readValue(jsonToCheck, clz);
        } catch (JsonProcessingException e) {
            logger.error("Error while parsing JSON: "
                    + "We are trying to parse the json to class = " + clz +
                    "*********************************\n" + e.getMessage());
            throw new RuntimeException("Error while parsing JSON: \n"
                    + "We are trying to parse the json to class = " + clz +
                    "\n*********************************\n" + e.getMessage());
        }
    }

    public static <T> T getNodeFromJsonString(Class<T> clz, String jsonToCheck,String node) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // Allow unquoted field names

        try {
            ObjectNode responseNode = objectMapper.readValue(jsonToCheck, ObjectNode.class);
            ObjectNode authUserNode = (ObjectNode) responseNode.get(node);

            return objectMapper.treeToValue(authUserNode,clz);
        } catch (JsonProcessingException e) {
            logger.error("Error while parsing JSON: "
                    + "We are trying to parse the json to class = " + clz +
                    "*********************************\n" + e.getMessage());
            throw new RuntimeException("Error while parsing JSON: \n"
                    + "We are trying to parse the json to class = " + clz +
                    "\n*********************************\n" + e.getMessage());
        }
    }
}
