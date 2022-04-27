package com.alnicode.funvirtualreading.domain.dto;

import lombok.Data;

@Data
public class CommentResponse {
    private long id;
    private String book;
    private String person;
    private String body;
    private String date;
}
