package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;
import java.util.List;
import java.util.Optional;

/**
 * The person service template.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface IPersonService extends ICrudService<PersonRequest, PersonResponse> {

    /**
     * Add a book to the likes collection.
     *
     * @param personId the id of the list owner
     * @param bookId   the book id to be added
     * @return an optional of the {@link PersonResponse}
     */
    Optional<PersonResponse> addLike(long personId, long bookId);

    /**
     * Remove a book to the likes collection.
     *
     * @param personId the id of the list owner
     * @param bookId   the book id to be removed
     * @return an optional of the {@link PersonResponse}
     */
    Optional<PersonResponse> removeLike(long personId, long bookId);

    /**
     * Get a person response DTO by the email.
     *
     * @param email the email to search
     * @return the {@link PersonResponse} found
     */
    Optional<PersonResponse> getByEmail(String email);

    /**
     * Get the book's author.
     *
     * @param bookId the id to search
     * @return the {@link PersonResponse} found
     */
    Optional<PersonResponse> getByPublishedBook(long bookId);

    /**
     * Get all the persons with the same nationality id.
     *
     * @param nationalityId the id to search
     * @return the responses list
     */
    Optional<List<PersonResponse>> getByNationality(long nationalityId);

    /**
     * Get all the persons with the same books liked.
     *
     * @param bookId the id to search
     * @return the responses list
     */
    Optional<List<PersonResponse>> getByBooksLiked(long bookId);

}
