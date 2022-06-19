package com.alnicode.funvirtualreading.domain.service;

import com.alnicode.funvirtualreading.domain.dto.TagRequest;
import com.alnicode.funvirtualreading.domain.dto.TagResponse;
import java.util.List;
import java.util.Optional;

/**
 * The tag service template.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public interface ITagService extends ICrudService<TagRequest, TagResponse> {

    /**
     * Get a tag response by the name.
     *
     * @param name the name to search
     * @return an optional of the tag found
     */
    Optional<TagResponse> getByName(String name);

    /**
     * Get all the tags with the same book ID.
     *
     * @param bookId the ID to search
     * @return an optional of the list found
     */
    Optional<List<TagResponse>> getByBookId(long bookId);

}
