package pl.zielinska.outdoor.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter @Getter
public class ApiExceptionPayload {

    private final String message;
    private final HttpStatus status;
}
