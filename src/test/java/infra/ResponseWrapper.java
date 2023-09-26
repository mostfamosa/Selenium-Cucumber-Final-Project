package infra;

import lombok.Data;

@Data
public class ResponseWrapper<T> {
    private int status;
    private T data;

    public ResponseWrapper() {
    }
}
