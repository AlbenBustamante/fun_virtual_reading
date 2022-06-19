package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * The tag request model DTO.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Data
public class TagRequest {

    @NotBlank
    @Size(min = 3, max = 40)
    private final String name;

}
