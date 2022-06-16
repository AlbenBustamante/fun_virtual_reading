package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.BookRequest;
import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import java.util.List;
import java.util.Optional;

/**
 * The book service template.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface IBookService extends ICrudService<BookRequest, BookResponse> {

    /**
     * Get book response DTO by the comment id.
     *
     * @param commentId the id to search
     * @return the {@link BookResponse} DTO found
     */
    Optional<BookResponse> getByComment(long commentId);

    /**
     * Get the book responses with the same person id.
     *
     * @param userId the id to search
     * @return a list with the responses found
     */
    Optional<List<BookResponse>> getByAuthorId(long userId);

    /**
     * Get the book responses with the same genre id.
     *
     * @param genreId the id to search
     * @return a list with the responses found
     */
    Optional<List<BookResponse>> getByGenre(long genreId);

}
