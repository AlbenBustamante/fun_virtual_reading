package com.alnicode.funvirtualreading.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;


import static com.alnicode.funvirtualreading.constants.TagConstants.NAME_BLANK;
import static com.alnicode.funvirtualreading.constants.TagConstants.NAME_SIZE;

/**
 * The tag request model DTO.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Data
public class TagRequest {

    @NotBlank(message = NAME_BLANK)
    @Size(min = 3, max = 40, message = NAME_SIZE)
    private final String name;

}
