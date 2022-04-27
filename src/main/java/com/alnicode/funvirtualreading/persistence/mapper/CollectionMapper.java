package com.alnicode.funvirtualreading.persistence.mapper;

import java.util.List;

import com.alnicode.funvirtualreading.domain.dto.CollectionRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionResponse;
import com.alnicode.funvirtualreading.persistence.entity.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

@Mapper(componentModel = "spring", uses = { CollectionsBookMapper.class })
public interface CollectionMapper {
    @Mapping(target = "id", source = "collectionId")
    @Mapping(target = "person", source = "person.email")
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    CollectionResponse toResponse(Collection entity);
    List<CollectionResponse> toResponses(List<Collection> entities);

    @Mapping(target = "collectionId", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "books", ignore = true)
    Collection toEntity(CollectionRequest request);
}
