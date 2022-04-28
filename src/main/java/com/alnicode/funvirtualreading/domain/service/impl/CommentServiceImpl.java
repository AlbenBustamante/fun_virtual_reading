package com.alnicode.funvirtualreading.domain.service.impl;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.CommentRequest;
import com.alnicode.funvirtualreading.domain.dto.CommentResponse;
import com.alnicode.funvirtualreading.domain.service.ICommentService;
import com.alnicode.funvirtualreading.persistence.entity.Comment;
import com.alnicode.funvirtualreading.persistence.mapper.CommentMapper;
import com.alnicode.funvirtualreading.persistence.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl extends DeleteService<Comment> implements ICommentService {
    @Autowired
    private CommentRepository repository;

    @Autowired
    private CommentMapper mapper;

    @Override
    @Transactional
    public CommentResponse save(CommentRequest request) {
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

        if (!comment.isPresent()) {
            return Optional.empty();
        }

        var entity = this.mapper.toEntity(request);
        entity.setId(id);
        entity.setDate(comment.get().getDate());

        return Optional.of(this.mapper.toResponse(this.repository.save(entity)));
    }

    @Override
    protected CrudRepository<Comment, Long> repository() {
        return this.repository;
    }
    
}
