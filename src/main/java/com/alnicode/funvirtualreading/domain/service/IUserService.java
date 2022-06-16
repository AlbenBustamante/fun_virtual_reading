package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.UserRequest;
import com.alnicode.funvirtualreading.domain.dto.UserResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The user service template.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface IUserService extends ICrudService<UserRequest, UserResponse>, UserDetailsService {

    /**
     * Add a book to the likes collection.
     *
     * @param personId the id of the list owner
     * @param bookId   the book id to be added
     * @return an optional of the {@link UserResponse}
     */
    Optional<UserResponse> addLike(long personId, long bookId);

    /**
     * Remove a book to the likes collection.
     *
     * @param personId the id of the list owner
     * @param bookId   the book id to be removed
     * @return an optional of the {@link UserResponse}
     */
    Optional<UserResponse> removeLike(long personId, long bookId);

    /**
     * Get a person response DTO by the email.
     *
     * @param email the email to search
     * @return the {@link UserResponse} found
     */
    Optional<UserResponse> getByEmail(String email);

    /**
     * Get the book's author.
     *
     * @param bookId the id to search
     * @return the {@link UserResponse} found
     */
    Optional<UserResponse> getByPublishedBook(long bookId);

    /**
     * Get all the persons with the same nationality id.
     *
     * @param nationalityId the id to search
     * @return the responses list
     */
    Optional<List<UserResponse>> getByNationality(long nationalityId);

    /**
     * Get all the persons with the same books liked.
     *
     * @param bookId the id to search
     * @return the responses list
     */
    Optional<List<UserResponse>> getByBooksLiked(long bookId);

}
