package com.alnicode.funvirtualreading.domain.service;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;

public interface IPersonService extends ICrudService<PersonRequest, PersonResponse> {
    Optional<PersonResponse> addLike(long personId, long bookId);
    Optional<PersonResponse> removeLike(long personId, long bookId);
    Optional<PersonResponse> getByEmail(String email);
    Optional<PersonResponse> getByPublishedBook(long bookId);
    Optional<List<PersonResponse>> getByNationality(long nationalityId);
    Optional<List<PersonResponse>> getByBooksLiked(long bookId);
}
