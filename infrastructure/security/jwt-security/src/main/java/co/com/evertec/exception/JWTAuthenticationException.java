package co.com.evertec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class JWTAuthenticationException extends RuntimeException {

    public JWTAuthenticationException(String message) {
        super(message);
    }
}
