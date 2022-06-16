package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.GenreRequest;
import com.alnicode.funvirtualreading.domain.dto.GenreResponse;
import java.util.Optional;

/**
 * The genre service template.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
public interface IGenreService extends ICrudService<GenreRequest, GenreResponse> {

    /**
     * Get a genre response by the book id.
     *
     * @param bookId the id to search
     * @return the {@link GenreResponse} found
     */
    Optional<GenreResponse> getByBookId(long bookId);

}
