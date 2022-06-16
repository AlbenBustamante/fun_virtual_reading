package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import java.util.List;
import java.util.Optional;

/**
 * The comment service template.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface ICommentService extends ICrudService<CommentRequest, CommentResponse> {

    /**
     * Get a comment responses list with the same person id.
     *
     * @param userId the id to search
     * @return the responses list
     */
    Optional<List<CommentResponse>> getByUser(long userId);

    /**
     * Get a comment responses list with the same book id.
     *
     * @param bookId the id to search
     * @return the responses list
     */
    Optional<List<CommentResponse>> getByBook(long bookId);

}
