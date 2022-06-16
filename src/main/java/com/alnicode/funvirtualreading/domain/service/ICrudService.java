package com.alnicode.funvirtualreading.domain.service;

import java.util.List;
import java.util.Optional;

/**
 * This is a generic service template with the CRUD methods.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface ICrudService<Request, Response> {

    /**
     * Save a new entity by the request.
     *
     * @param request the request to be saved
     * @return the response
     */
    Response save(Request request);

    /**
     * Get all the responses.
     *
     * @return the responses list
     */
    List<Response> getAll();

    /**
     * Get a response by the id.
     *
     * @param id the id to search
     * @return the response found
     */
    Optional<Response> get(long id);

    /**
     * Update an existing entity by the id.
     *
     * @param id      the id to search and be updated
     * @param request the request with the data to use
     * @return the response updated
     */
    Optional<Response> update(long id, Request request);

    /**
     * Delete an existing entity by the id.
     *
     * @param id the id to search and be deleted
     * @return true if it was deleted
     */
    boolean delete(long id);

}
