package com.alnicode.funvirtualreading.constants;

/**
 * Here is the nationality constants.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public final class NationalityConstants {

    /* ===============================
       -> REQUEST MAPPING PATHS
       =============================== */
    public static final String MAIN_PATH = "/nationalities";
    public static final String SORTED_PATH = "/sorted";
    public static final String MAIN_SORTED_PATH = "/nationalities/sorted";

    /* ===============================
       -> EXCEPTION MESSAGES
       =============================== */
    public static final String COUNTRY_EXISTS = "This country is already in use, try another";
    /* =============================== */

    /* ===============================
       -> VALIDATION MESSAGES
       =============================== */
    // BLANK
    public static final String NAME_BLANK = "The name cannot be blank";
    public static final String COUNTRY_BLANK = "The country cannot be blank";
    // SIZE
    public static final String NAME_SIZE = "The name must be between 5 and 80 characters";
    public static final String COUNTRY_SIZE = "The country must be between 3 and 70 characters";
    /* =============================== */

    private NationalityConstants() { }

}
