package assignMate.example.AssignMate.Base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private HttpStatus responseStatus;
    private String errorMessage;

    public ApiResponse(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ApiResponse(HttpStatus responseStatus, String errorMessage) {
        this.responseStatus = responseStatus;
        this.errorMessage = errorMessage;
    }

    public ApiResponse(T data, HttpStatus responseStatus) {
        this.data = data;
        this.responseStatus = responseStatus;
    }

}

