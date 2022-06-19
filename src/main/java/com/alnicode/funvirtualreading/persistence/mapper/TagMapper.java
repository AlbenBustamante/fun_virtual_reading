package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.TagRequest;
import com.alnicode.funvirtualreading.domain.dto.TagResponse;
import com.alnicode.funvirtualreading.persistence.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The tag entity/DTO mapper.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper<TagResponse, Tag> {

    /**
     * Map a tag entity to the tag response DTO.
     *
     * @param entity the {@link Tag} entity to be mapped
     * @return the {@link TagResponse} DTO
     */
    TagResponse toResponse(Tag entity);

    /**
     * Map a tag request DTO to the tag entity.
     *
     * @param request the {@link TagRequest} DTO to be mapped
     * @return the {@link Tag} entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Tag toEntity(TagRequest request);

}
