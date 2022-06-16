package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * The collection request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class CollectionRequest {
    @NotBlank
    private String name;

    @NotNull
    @Min(1L)
    private long userId;
}
