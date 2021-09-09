package nl.minor.clsd.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class AccountHolderNotFoundException extends RuntimeException {
    public AccountHolderNotFoundException(String message) {
        super(message);
    }
}
