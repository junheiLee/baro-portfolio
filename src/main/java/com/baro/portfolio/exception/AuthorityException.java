package com.baro.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AuthorityException extends RuntimeException {

    public AuthorityException() {
        super();
    }

    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityException(Throwable cause) {
        super(cause);
    }

    protected AuthorityException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
