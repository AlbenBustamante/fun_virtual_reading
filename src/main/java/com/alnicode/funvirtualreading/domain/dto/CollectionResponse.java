package com.alnicode.funvirtualreading.domain.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 * The collection response DTO.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
public class CollectionResponse {
    private long id;
    private String name;
    private String user;
    private String date;
    private Set<CollectionsBookResponse> books = new HashSet<>();
}
