package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * The nationality request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class NationalityRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String country;
}
