package com.alnicode.funvirtualreading.exception;

/**
 * This is a custom exception created for {@code create} and {@code register} methods.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public class RegisterNotValidException extends Exception {

    public RegisterNotValidException(String message) {
        super(message);
    }

}
