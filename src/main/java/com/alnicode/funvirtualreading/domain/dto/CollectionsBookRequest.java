package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.CollectionsBookConstants;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = CollectionsBookConstants.RATING_NULL)
    @Min(value = 1, message = CollectionsBookConstants.RATING_MIN_MAX)
    @Max(value = 10, message = CollectionsBookConstants.RATING_MIN_MAX)
    private Integer rating;

    @NotBlank(message = CollectionsBookConstants.NOTE_BLANK)
    @Size(min = 10, max = 200, message = CollectionsBookConstants.NOTE_SIZE)
    private String note;

}
