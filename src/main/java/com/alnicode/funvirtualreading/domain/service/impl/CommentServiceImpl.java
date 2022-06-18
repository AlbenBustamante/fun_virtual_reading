package com.alnicode.funvirtualreading.domain.service.impl;

import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import com.alnicode.funvirtualreading.domain.service.ICommentService;
import com.alnicode.funvirtualreading.persistence.entity.Comment;
import com.alnicode.funvirtualreading.persistence.mapper.CommentMapper;
import com.alnicode.funvirtualreading.persistence.repository.CommentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The comment service implementation.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository repository;

    @Autowired
    private CommentMapper mapper;

    @Override
    public CrudRepository<Comment, Long> repository() {
        return repository;
    }

    @Override
    @Transactional
    public CommentResponse create(CommentRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getAll() {
        return this.mapper.toResponses(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommentResponse> get(long id) {
        return this.repository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public Optional<CommentResponse> update(long id, CommentRequest request) {
        var comment = this.repository.findById(id);

        if (comment.isEmpty()) {
            return Optional.empty();
        }

        var entity = this.mapper.toEntity(request);
        entity.setId(id);
        entity.setDate(comment.get().getDate());

        return Optional.of(this.mapper.toResponse(this.repository.save(entity)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CommentResponse>> getByUser(long userId) {
        return this.repository.findByUserId(userId).map(mapper::toResponses);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CommentResponse>> getByBook(long bookId) {
        return this.repository.findByBookId(bookId).map(mapper::toResponses);
    }

}
