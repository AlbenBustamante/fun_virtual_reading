package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.BookConstants;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank(message = BookConstants.TITLE_BLANK)
    @Size(min = 10, max = 130, message = BookConstants.TITLE_SIZE)
    private String title;

    @NotBlank(message = BookConstants.SYNOPSIS_BLANK)
    @Size(min = 100, max = 600, message = BookConstants.SYNOPSIS_SIZE)
    private String synopsis;

    @NotBlank(message = BookConstants.BODY_BLANK)
    @Size(min = 1000, max = 4000, message = BookConstants.BODY_SIZE)
    private String body;

    @NotNull(message = BookConstants.USER_NULL)
    @Min(value = 1L, message = BookConstants.USER_MIN)
    private long userId;

    @NotNull(message = BookConstants.GENRE_NULL)
    @Min(value = 1L, message = BookConstants.GENRE_MIN)
    private long genreId;

}
