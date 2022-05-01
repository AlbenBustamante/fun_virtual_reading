package com.alnicode.funvirtualreading.domain.service;

import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.GenreRequest;
import com.alnicode.funvirtualreading.domain.dto.GenreResponse;

public interface IGenreService extends ICrudService<GenreRequest, GenreResponse> {
    Optional<GenreResponse> getByBookId(long bookId);
}
