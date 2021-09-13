package nl.minor.clsd.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AccountHolderHasAccountException extends RuntimeException {
    public AccountHolderHasAccountException(String message) {
        super(message);
    }
}
