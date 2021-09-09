package nl.minor.clsd.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class WrongParameterTypeException extends RuntimeException {
    public WrongParameterTypeException(String message) {
        super(message);
    }
}
