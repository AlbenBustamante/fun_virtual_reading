package com.alnicode.funvirtualreading.domain.dto;

import com.alnicode.funvirtualreading.constants.CommentConstants;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * The comment request DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class CommentRequest {

    @NotBlank(message = CommentConstants.BODY_BLANK)
    @Size(min = 1, max = 400, message = CommentConstants.BODY_SIZE)
    private String body;

    @NotNull(message = CommentConstants.USER_NULL)
    @Min(value = 1L, message = CommentConstants.USER_MIN)
    private long userId;

    @NotNull(message = CommentConstants.BOOK_NULL)
    @Min(value = 1L, message = CommentConstants.BOOK_MIN)
    private long bookId;

}
