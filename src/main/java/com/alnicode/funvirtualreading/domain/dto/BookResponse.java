package com.alnicode.funvirtualreading.domain.dto;

import lombok.Data;

@Data
public class BookResponse {
    private long id;
    private String title;
    private String sypnosis;
    private String body;
    private String author;
    private String genre;
    private String date;
}
