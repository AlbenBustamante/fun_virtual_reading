package com.alnicode.funvirtualreading.constants;

/**
 * Here is the book constants.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public final class BookConstants {

    /* -< REQUEST MAPPING PATHS >- */
    public static final String BOOKS_TAGS_PATH = "/{bookId}/tag/{tagId}";

    /* ===============================
       -> EXCEPTION MESSAGES
       =============================== */
    public static final String TITLE_EXISTS = "This title is already in use, try another";
    public static final String SYNOPSIS_EXISTS = "This synopsis is already in use, try another";
    public static final String BODY_EXISTS = "The body is already in use, try another";
    /* =============================== */

    /* ===============================
       -> VALIDATION MESSAGES
       =============================== */
    // BLANK
    public static final String TITLE_BLANK = "The title cannot be blank";
    public static final String SYNOPSIS_BLANK = "The synopsis cannot be blank";
    public static final String BODY_BLANK = "The body cannot be blank";
    // NULL
    public static final String USER_NULL = "The user cannot be null";
    public static final String GENRE_NULL = "The genre cannot be null";
    // MIN
    public static final String USER_MIN = "The user ID must be at least 1";
    public static final String GENRE_MIN = "The genre ID must be at least 1";
    // SIZE
    public static final String TITLE_SIZE = "The title must be between 10 and 130 characters";
    public static final String SYNOPSIS_SIZE = "The synopsis must be between 100 and 600 characters";
    public static final String BODY_SIZE = "The body must be between 1000 and 4000 characters";
    /* =============================== */

    private BookConstants() { }

}
