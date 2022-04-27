package com.alnicode.funvirtualreading.domain.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class CollectionResponse {
    private long id;
    private String name;
    private String person;
    private String date;
    private Set<CollectionsBookResponse> books = new HashSet<>();
}
