package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.GenreConstants;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = GenreConstants.NAME_BLANK)
    @Size(min = 10, max = 100, message = GenreConstants.NAME_SIZE)
    private String name;

    @NotBlank(message = GenreConstants.DESCRIPTION_BLANK)
    @Size(min = 40, max = 355, message = GenreConstants.DESCRIPTION_SIZE)
    private String description;

}
