package com.alnicode.funvirtualreading.constants;

/**
 * Here is the user constants.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public final class UserConstants {

    /* ===============================
       -> SECURITY
       =============================== */
    public static final String USER_DETAILS_SERVICE = "customUserDetailsServiceImpl";
    /* =============================== */

    /* ===============================
       -> REQUEST MAPPING PATHS
       =============================== */
    public static final String MAIN_PATH = "/users";
    public static final String LIKES_PATH = "{id}/book/{bookId}";
    public static final String AUTH_PATH = "/auth";
    /* =============================== */

    /* ===============================
       -> EXCEPTION MESSAGES
       =============================== */
    public static final String EMAIL_EXISTS = "This email is already in use, try another";
    public static final String USERNAME_EXISTS = "This username is already in use, try another";
    public static final String PASSWORDS_DO_NOT_MATCH = "The passwords do not match, try again";
    /* =============================== */

    /* ===============================
       -> VALIDATION MESSAGES
       =============================== */
    // BLANK
    public static final String FIRST_NAME_BLANK = "The first name cannot be blank";
    public static final String LAST_NAME_BLANK = "The last name cannot be blank";
    public static final String EMAIL_BLANK = "The email cannot be blank";
    public static final String USERNAME_BLANK = "The username cannot be blank";
    public static final String PASSWORD_BLANK = "The password cannot be blank";
    public static final String RPASSWORD_BLANK = "Please, re-type your password";
    public static final String BIRTHDAY_BLANK = "The birthday cannot be blank";
    // NULL
    public static final String NATIONALITY_NULL = "The nationality cannot be null";
    // MIN
    public static final String NATIONALITY_MIN = "The nationality ID must be at least 1";
    // EMAIL
    public static final String EMAIL_FORMAT = "Is not a valid email format";
    // SIZE
    public static final String FIRST_NAME_SIZE = "The first name must be between 3 and 40 characters";
    public static final String LAST_NAME_SIZE = "The last name must be between 3 and 60 characters";
    public static final String EMAIL_SIZE = "The email must be between 12 and 200 characters";
    public static final String USERNAME_SIZE = "The email must be between 3 and 60 characters";
    public static final String PASSWORD_SIZE = "The password must have at least 8 characters";
    /* =============================== */

    private UserConstants() { }

}
