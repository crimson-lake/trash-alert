package pl.zielinska.outdoor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiExceptionPayload payload = new ApiExceptionPayload(e.getMessage(), status);
        return new ResponseEntity<>(payload, status);
    }

}
