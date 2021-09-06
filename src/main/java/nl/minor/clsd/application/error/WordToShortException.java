package nl.minor.clsd.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WordToShortException extends RuntimeException {
    public WordToShortException(String message) {
        super(message);
    }
}
