package com.alnicode.funvirtualreading.persistence.mapper;

import java.util.List;

import com.alnicode.funvirtualreading.domain.dto.BookRequest;
import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.persistence.entity.Book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

@Mapper(componentModel = "spring", uses = { GenreMapper.class })
public interface BookMapper {
    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "author", source = "person.email")
    @Mapping(target = "genre", source = "genre.name")
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    BookResponse toResponse(Book entity);
    List<BookResponse> toResponses(List<Book> entities);

    @Mapping(target = "bookId", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "collections", ignore = true)
    @Mapping(target = "persons", ignore = true)
    Book toEntity(BookRequest request);
}
