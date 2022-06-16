package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.CollectionRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionResponse;
import com.alnicode.funvirtualreading.persistence.entity.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

/**
 * The collection mapper.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring", uses = {CollectionsBookMapper.class})
public interface CollectionMapper {

    /**
     * Map a collection entity to the collection response DTO.
     *
     * @param entity the {@link Collection} entity to be mapped
     * @return the {@link CollectionResponse} DTO
     */
    @Mapping(target = "id", source = "collectionId")
    @Mapping(target = "person", source = "person.email")
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    CollectionResponse toResponse(Collection entity);

    /**
     * Map an entities list to responses list.
     *
     * @param entities the list to be mapped
     * @return the responses list
     */
    List<CollectionResponse> toResponses(List<Collection> entities);

    /**
     * Map a collection request DTO to the collection entity.
     *
     * @param request the {@link CollectionRequest} DTO to be mapped
     * @return the {@link Collection} entity
     */
    @Mapping(target = "collectionId", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "books", ignore = true)
    Collection toEntity(CollectionRequest request);
}
