package com.alnicode.funvirtualreading.persistence.mapper;

import java.util.List;

import com.alnicode.funvirtualreading.domain.dto.GenreRequest;
import com.alnicode.funvirtualreading.domain.dto.GenreResponse;
import com.alnicode.funvirtualreading.persistence.entity.Genre;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreResponse toResponse(Genre entity);
    List<GenreResponse> toResponses(List<Genre> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Genre toEntity(GenreRequest request);
}
