package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * The genre request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class GenreRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
