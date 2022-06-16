package com.alnicode.funvirtualreading.domain.dto;

import lombok.Data;

/**
 * The comment response DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class CommentResponse {
    private long id;
    private String book;
    private String person;
    private String body;
    private String date;
}
