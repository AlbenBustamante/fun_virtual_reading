package com.alnicode.funvirtualreading.domain.service;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;

public interface ICollectionsBookService {
    CollectionsBookResponse save(CollectionsBookRequest request);
    List<CollectionsBookResponse> getAll();
    Optional<CollectionsBookResponse> get(long collectionId, long bookId);
    Optional<CollectionsBookResponse> update(long collectionId, long bookId, CollectionsBookRequest request);
    boolean delete(long collectionId, long bookId);
}
