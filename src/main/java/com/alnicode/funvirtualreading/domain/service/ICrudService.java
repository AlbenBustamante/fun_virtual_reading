package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.exception.RegisterNotValidException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * This is a generic service template with the CRUD methods.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface ICrudService<Request, Response> {

    /**
     * Set the repository to be used.
     *
     * @return the repository used.
     */
    CrudRepository<?, Long> repository();

    /**
     * Save a new entity by the request.
     *
     * @param request the request to be saved
     * @return the response
     */
    Response create(Request request) throws RegisterNotValidException;

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
    default boolean delete(long id) {
        try {
            repository().deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
