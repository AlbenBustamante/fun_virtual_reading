package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import com.alnicode.funvirtualreading.persistence.entity.Comment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

/**
 * The comment mapper.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Map a comment entity to the comment response DTO.
     *
     * @param entity the {@link Comment} entity to be mapped
     * @return the {@link CommentResponse} DTO
     */
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "book", source = "book.title")
    @Mapping(target = "user", source = "user.username")
    CommentResponse toResponse(Comment entity);

    /**
     * Map an entities list to the responses list.
     *
     * @param entities the list to be mapped
     * @return the responses list
     */
    List<CommentResponse> toResponses(List<Comment> entities);

    /**
     * Map a comment request DTO to the comment entity
     *
     * @param request the {@link CommentRequest} DTO to be mapped
     * @return the {@link Comment} entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "book", ignore = true)
    Comment toEntity(CommentRequest request);
}
