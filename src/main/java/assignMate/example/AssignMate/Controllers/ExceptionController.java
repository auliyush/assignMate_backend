package assignMate.example.AssignMate.Controllers;
import assignMate.example.AssignMate.Base.ApiResponse;
import assignMate.example.AssignMate.Exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> generaException(Exception e){
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.OK);
        response.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setErrorMessage("INTERNAL_SERVER_ERROR");
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ApplicationException.class)
    public ApiResponse<Object> applicationExceptionHandler(ApplicationException e){
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.OK);
        response.setResponseStatus(HttpStatus.OK);
        response.setErrorMessage(e.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> badRequestExceptionHandler(Exception e){
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.OK);
        response.setResponseStatus(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(e.getMessage());
        return response;
    }
}

