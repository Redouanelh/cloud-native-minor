package nl.minor.clsd.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class FailedToSaveObjectException extends RuntimeException {
    public FailedToSaveObjectException(String message) {
        super(message);
    }
}
