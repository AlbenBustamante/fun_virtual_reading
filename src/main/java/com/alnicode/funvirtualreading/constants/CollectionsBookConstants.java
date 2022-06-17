package com.alnicode.funvirtualreading.constants;

/**
 * Here is the collections-books constants.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public final class CollectionsBookConstants {

    /* ===============================
       -> REQUEST MAPPING PATHS
       =============================== */
    public static final String COLLECTIONS_BOOKS_PATH = "/collection/{collectionId}/book/{bookId}";
    public static final String MAIN_PATH = "/collection_books";
    /* =============================== */

    /* ===============================
       -> VALIDATION MESSAGES
       =============================== */
    // BLANK
    public static final String NOTE_BLANK = "The note cannot be blank";
    // NULL
    public static final String RATING_NULL = "The rating cannot be null";
    // MIN AND MAX
    public static final String RATING_MIN_MAX = "The rating must be between 1 and 10";
    // SIZE
    public static final String NOTE_SIZE = "The note must be between 10 and 200 characters";
    /* =============================== */

    private CollectionsBookConstants() { }

}
