package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentRequest {
    @NotBlank
    private String body;

    @NotNull
    @Min(1L)
    private long personId;

    @NotNull
    @Min(1L)
    private long bookId;
}
