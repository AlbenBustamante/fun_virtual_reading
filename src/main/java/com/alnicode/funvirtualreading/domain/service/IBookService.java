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
     * Add an existing tag to the book.
     *
     * @param bookId the book to search
     * @param tagId the tag to add
     * @return an optional of book tag updated
     */
    Optional<BookResponse> addTag(long bookId, long tagId);

    /**
     * Remove an existing tag to the book.
     *
     * @param bookId the book to search
     * @param tagId the tag to add
     * @return an optional of the book updated
     */
    Optional<BookResponse> removeTag(long bookId, long tagId);

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

    /**
     * Get all the books with the same tag ID.
     *
     * @param tagId the id to search
     * @return an optional of the books found
     */
    Optional<List<BookResponse>> getByTagId(long tagId);

}
