package com.alnicode.funvirtualreading.exception;

import lombok.Getter;

/**
 * This is a custom exception created for {@code create} and {@code register} methods.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Getter
public class RegisterNotValidException extends Exception {

    private final String field;

    public RegisterNotValidException(String message, String field) {
        super(message);
        this.field = field;
    }

}
