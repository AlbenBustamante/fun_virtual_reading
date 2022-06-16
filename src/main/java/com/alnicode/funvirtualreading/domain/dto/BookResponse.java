package com.alnicode.funvirtualreading.domain.dto;

import lombok.Data;

/**
 * The book response DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class BookResponse {
    private long id;
    private String title;
    private String synopsis;
    private String body;
    private String author;
    private String genre;
    private String date;
}
