package com.alnicode.funvirtualreading.constants;

/**
 * Here is the genre constants.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public class GenreConstants {

    /* ===============================
       -> EXCEPTION MESSAGES
       =============================== */
    public static final String NAME_EXISTS = "This name is already in use, try another";
    public static final String DESCRIPTION_EXISTS = "This description is already in use, try another";
    /* =============================== */

    /* ===============================
       -> VALIDATION MESSAGES
       =============================== */
    // BLANK
    public static final String NAME_BLANK = "The name cannot be blank";
    public static final String DESCRIPTION_BLANK = "The description cannot be blank";
    // SIZE
    public static final String NAME_SIZE = "The name must be between 10 and 100 characters";
    public static final String DESCRIPTION_SIZE = "The name must be between 40 and 355 characters";
    /* =============================== */

    private GenreConstants() { }

}
