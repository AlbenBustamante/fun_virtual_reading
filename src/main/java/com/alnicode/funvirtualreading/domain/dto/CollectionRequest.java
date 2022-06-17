package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.CollectionConstants;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank(message = CollectionConstants.NAME_BLANK)
    @Size(min = 6, max = 200, message = CollectionConstants.NAME_SIZE)
    private String name;

    @NotNull(message = CollectionConstants.USER_NULL)
    @Min(value = 1L, message = CollectionConstants.USER_MIN)
    private long userId;

}
