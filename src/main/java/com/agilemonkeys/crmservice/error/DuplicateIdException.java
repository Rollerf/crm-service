package com.agilemonkeys.crmservice.error;

import org.springframework.expression.AccessException;

public class DuplicateIdException extends AccessException {

    public DuplicateIdException(String message) {
        super(message);
    }

    public DuplicateIdException(String message, Exception cause) {
        super(message, cause);
    }
}
