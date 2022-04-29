package com.alnicode.funvirtualreading.domain.service;

import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;

public interface IPersonService extends ICrudService<PersonRequest, PersonResponse> {
    Optional<PersonResponse> addLike(long personId, long bookId);
    Optional<PersonResponse> removeLike(long personId, long bookId);
}
