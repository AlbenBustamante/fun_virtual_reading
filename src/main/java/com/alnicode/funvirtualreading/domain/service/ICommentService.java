package com.alnicode.funvirtualreading.domain.service;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;

public interface ICommentService extends ICrudService<CommentRequest, CommentResponse> {
    Optional<List<CommentResponse>> getByPerson(long personId);
    Optional<List<CommentResponse>> getByBook(long bookId);
}
