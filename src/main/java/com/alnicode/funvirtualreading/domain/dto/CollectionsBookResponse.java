package com.alnicode.funvirtualreading.domain.dto;

import lombok.Data;

@Data
public class CollectionsBookResponse {
    private String collection;
    private String book;
    private Integer rating;
    private String note;
}
