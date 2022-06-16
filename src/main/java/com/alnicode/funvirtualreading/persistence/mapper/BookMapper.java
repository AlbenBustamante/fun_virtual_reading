package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.BookRequest;
import com.alnicode.funvirtualreading.domain.dto.BookResponse;
import com.alnicode.funvirtualreading.persistence.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

/**
 * The book mapper.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<BookResponse, Book> {

    /**
     * Map a book entity to the book response DTO.
     *
     * @param entity the {@link Book} entity to be mapped
     * @return the {@link BookResponse} DTO
     */
    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "author", source = "user.username")
    @Mapping(target = "genre", source = "genre.name")
    @Mapping(target = "date", dateFormat = DATE_TIME_FORMAT)
    BookResponse toResponse(Book entity);

    /**
     * Map a book request DTO to the book entity.
     *
     * @param request the {@link BookRequest} DTO to be mapped
     * @return the {@link Book} entity
     */
    @Mapping(target = "bookId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "collections", ignore = true)
    @Mapping(target = "users", ignore = true)
    Book toEntity(BookRequest request);
}
