package com.alnicode.funvirtualreading.persistence.mapper;

import com.alnicode.funvirtualreading.domain.dto.LikeResponse;
import com.alnicode.funvirtualreading.persistence.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The like mapper.
 * <p>This mapper is responsible for reducing the amount of data to be displayed from the books.</p>
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface LikeMapper {

    /**
     * Map a book entity to the like response DTO.
     *
     * @param book the {@link Book} entity to be mapped
     * @return the {@link LikeResponse} DTO
     */
    @Mapping(target = "id", source = "bookId")
    @Mapping(target = "book", source = "title")
    LikeResponse toLikeResponse(Book book);
}
