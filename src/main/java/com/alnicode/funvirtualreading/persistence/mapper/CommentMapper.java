package com.alnicode.funvirtualreading.persistence.mapper;

import java.util.List;

import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import com.alnicode.funvirtualreading.persistence.entity.Comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "book", source = "book.title")
    @Mapping(target = "person", source = "person.email")
    CommentResponse toResponse(Comment entity);
    List<CommentResponse> toResponses(List<Comment> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "book", ignore = true)
    Comment toEntity(CommentRequest request);
}
