package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;
import com.alnicode.funvirtualreading.exception.RegisterNotValidException;
import java.util.List;
import java.util.Optional;

/**
 * The collections-books service template.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface ICollectionsBookService {

    /**
     * Save a new entity by the {@code Collection} id and {@code Book} id.
     *
     * @param collectionId the collection id
     * @param bookId       the book id
     * @param request      the request to be saved
     * @return the response saved
     * @see com.alnicode.funvirtualreading.persistence.entity.Collection
     * @see com.alnicode.funvirtualreading.persistence.entity.Book
     */
    CollectionsBookResponse create(long collectionId, long bookId, CollectionsBookRequest request) throws RegisterNotValidException;

    /**
     * Get all the collections-books responses.
     *
     * @return the responses list
     */
    List<CollectionsBookResponse> getAll();

    /**
     * Get a collections-books response by the {@code Collection} id and {@code Book} id.
     *
     * @param collectionId the collection id
     * @param bookId       the book id
     * @return the response found
     * @see com.alnicode.funvirtualreading.persistence.entity.Collection
     * @see com.alnicode.funvirtualreading.persistence.entity.Book
     */
    Optional<CollectionsBookResponse> get(long collectionId, long bookId);

    /**
     * Update an existing entity by the {@code Collection} id and {@code Book} id.
     *
     * @param collectionId the collection id
     * @param bookId       the book id
     * @param request      the request with the data to be used
     * @return the response updated
     * @see com.alnicode.funvirtualreading.persistence.entity.Collection
     * @see com.alnicode.funvirtualreading.persistence.entity.Book
     */
    Optional<CollectionsBookResponse> update(long collectionId, long bookId, CollectionsBookRequest request);

    /**
     * Delete an existing entity by the {@code Collection} id and {@code Book} id.
     *
     * @param collectionId the collection id
     * @param bookId       the book id
     * @return true if it was deleted
     * @see com.alnicode.funvirtualreading.persistence.entity.Collection
     * @see com.alnicode.funvirtualreading.persistence.entity.Book
     */
    boolean delete(long collectionId, long bookId);

    /**
     * Get a sorted list with all the responses.
     *
     * @return the responses list
     */
    List<CollectionsBookResponse> getAllOrderByRating();

}
