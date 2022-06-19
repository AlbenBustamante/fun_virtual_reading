package com.alnicode.funvirtualreading.domain.service.impl;

import com.alnicode.funvirtualreading.domain.dto.TagRequest;
import com.alnicode.funvirtualreading.domain.dto.TagResponse;
import com.alnicode.funvirtualreading.domain.service.ITagService;
import com.alnicode.funvirtualreading.exception.RegisterNotValidException;
import com.alnicode.funvirtualreading.persistence.entity.Tag;
import com.alnicode.funvirtualreading.persistence.mapper.TagMapper;
import com.alnicode.funvirtualreading.persistence.repository.TagRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


import static com.alnicode.funvirtualreading.constants.TagConstants.NAME_EXISTS;

/**
 * The tag service implementation.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Service
public class TagServiceImpl implements ITagService {
    private final TagRepository repository;
    private final TagMapper mapper;

    @Autowired
    public TagServiceImpl(TagRepository repository, TagMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CrudRepository<Tag, Long> repository() {
        return repository;
    }

    @Override
    public TagResponse create(TagRequest tagRequest) throws RegisterNotValidException {
        checkData(tagRequest);

        return mapper.toResponse(repository.save(mapper.toEntity(tagRequest)));
    }

    @Override
    public List<TagResponse> getAll() {
        return mapper.toResponses(repository.findAll());
    }

    @Override
    public Optional<TagResponse> get(long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public Optional<TagResponse> update(long id, TagRequest tagRequest) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }

        final var entity = mapper.toEntity(tagRequest);
        entity.setId(id);

        return Optional.of(mapper.toResponse(repository.save(entity)));
    }

    @Override
    public Optional<TagResponse> getByName(String name) {
        return repository.findByName(name).map(mapper::toResponse);
    }

    @Override
    public Optional<List<TagResponse>> getByBookId(long bookId) {
        return repository.findByBooksBookId(bookId).map(mapper::toResponses);
    }

    private void checkData(TagRequest request) throws RegisterNotValidException {
        if (repository.findByName(request.getName()).isPresent()) {
            throw new RegisterNotValidException(NAME_EXISTS, "name");
        }
    }

}
