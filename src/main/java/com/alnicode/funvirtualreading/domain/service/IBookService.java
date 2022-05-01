package com.alnicode.funvirtualreading.domain.service;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.BookRequest;
import com.alnicode.funvirtualreading.domain.dto.BookResponse;

public interface IBookService extends ICrudService<BookRequest, BookResponse> {
    Optional<List<BookResponse>> getByAuthorId(long personId);
    Optional<List<BookResponse>> getByGenre(long genreId);
}
