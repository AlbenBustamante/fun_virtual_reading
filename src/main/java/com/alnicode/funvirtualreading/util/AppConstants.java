package com.alnicode.funvirtualreading.util;

public interface AppConstants {
    String DATE_FORMAT = "dd-MM-yyyy";
    String DATE_TIME_FORMAT = DATE_FORMAT + " hh:mm:ss";
    String COLLECTIONS_BOOKS_PATH = "/collection/{collectionId}/book/{bookId}";
    String LIKES_PATH = "{id}/book/{bookId}";
    String CONTROLLERS_PACKAGE = "com.alnicode.funvirtualreading.web.controller";
}
