package com.alnicode.funvirtualreading.persistence.mapper;

import java.util.List;

/**
 * This is a base mapper with some methods.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public interface BaseMapper<Response, Entity> {

    /**
     * Map an entities list to the responses list.
     *
     * @param entities the list to be mapped
     * @return the responses list
     */
    List<Response> toResponses(List<Entity> entities);

}
