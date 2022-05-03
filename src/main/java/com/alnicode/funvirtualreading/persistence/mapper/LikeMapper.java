package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.LikeResponse;
import com.alnicode.funvirtualreading.persistence.entity.Book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "book", source = "title")
    LikeResponse toLikeResponse(Book book);
}
