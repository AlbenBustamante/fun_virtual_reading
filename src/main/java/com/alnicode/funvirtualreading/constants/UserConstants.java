package com.alnicode.funvirtualreading.constants;

/**
 * Here is the user constants.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public final class UserConstants {

    public static final String MAIN_PATH = "/users";
    public static final String LIKES_PATH = "{id}/book/{bookId}";

    //Exception messages
    public static final String EMAIL_EXISTS = "This email is already in use, try another";
    public static final String USERNAME_EXISTS = "This username is already in use, try another";
    public static final String PASSWORDS_DO_NOT_MATCH = "The passwords do not match, try again";

    //Validation messages
    public static final String FIRSTNAME_BLANK = "The first name cannot be blank";
    public static final String LASTNAME_BLANK = "The last name cannot be blank";
    public static final String EMAIL_BLANK = "The email cannot be blank";
    public static final String USERNAME_BLANK = "The username cannot be blank";
    public static final String PASSWORD_BLANK = "The password cannot be blank";
    public static final String RPASSWORD_BLANK = "Please, re-type your password";
    public static final String BIRTHDAY_BLANK = "The birthday cannot be blank";
    public static final String NATIONALITY_NULL = "The nationality cannot be null";
    public static final String NATIONALITY_MIN = "The nationality ID must be at least 1";
    public static final String EMAIL_FORMAT = "Is not a valid email format";

    private UserConstants() { }

}
