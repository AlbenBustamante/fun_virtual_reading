package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * The book request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class BookRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String synopsis;

    @NotBlank
    private String body;

    @NotNull
    @Min(1L)
    private long userId;

    @NotNull
    @Min(1L)
    private long genreId;

}
