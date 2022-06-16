package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;
import com.alnicode.funvirtualreading.persistence.entity.CollectionsBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The collections-books mapper.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface CollectionsBookMapper extends BaseMapper<CollectionsBookResponse, CollectionsBook> {

    /**
     * Map a collections-books entity to the collections-books response DTO.
     *
     * @param entity the {@link CollectionsBook} entity to be mapped
     * @return the {@link CollectionsBookResponse} DTO
     */
    @Mapping(target = "collection", source = "collection.name")
    @Mapping(target = "book", source = "book.title")
    CollectionsBookResponse toResponse(CollectionsBook entity);

    /**
     * Map a collections-books request DTO to the collections-books entity.
     *
     * @param request the {@link CollectionsBookRequest} DTO to be mapped
     * @return the {@link CollectionsBook} entity
     */
    @Mapping(target = "id.collectionId", ignore = true)
    @Mapping(target = "id.bookId", ignore = true)
    @Mapping(target = "collection", ignore = true)
    @Mapping(target = "book", ignore = true)
    CollectionsBook toEntity(CollectionsBookRequest request);
}
