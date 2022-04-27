package com.alnicode.funvirtualreading.persistence.mapper;

import java.util.List;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;
import com.alnicode.funvirtualreading.persistence.entity.CollectionsBook;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollectionsBookMapper {
    @Mapping(target = "collection", source = "collection.name")
    @Mapping(target = "book", source = "book.title")
    CollectionsBookResponse toResponse(CollectionsBook entity);
    List<CollectionsBookResponse> toResponses(List<CollectionsBook> entities);

    @Mapping(target = "id.collectionId", ignore = true)
    @Mapping(target = "id.bookId", ignore = true)
    @Mapping(target = "collection", ignore = true)
    @Mapping(target = "book", ignore = true)
    CollectionsBook toEntity(CollectionsBookRequest request);
}
