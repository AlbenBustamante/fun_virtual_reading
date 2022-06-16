package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * The collections-books request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class CollectionsBookRequest {
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;

    @NotBlank
    private String note;
}
