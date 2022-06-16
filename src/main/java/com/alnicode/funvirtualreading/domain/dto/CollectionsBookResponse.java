package com.alnicode.funvirtualreading.domain.dto;

import lombok.Data;

/**
 * The collections-books response DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class CollectionsBookResponse {
    private String collection;
    private String book;
    private Integer rating;
    private String note;
}
