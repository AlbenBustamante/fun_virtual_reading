package com.alnicode.funvirtualreading.domain.service.impl;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;
import com.alnicode.funvirtualreading.domain.service.ICollectionsBookService;
import com.alnicode.funvirtualreading.persistence.entity.CollectionsBookPK;
import com.alnicode.funvirtualreading.persistence.mapper.CollectionsBookMapper;
import com.alnicode.funvirtualreading.persistence.repository.CollectionsBookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The collections-books service implementation.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Service
public class CollectionsBookServiceImpl implements ICollectionsBookService {
    @Autowired
    private CollectionsBookMapper mapper;

    @Autowired
    private CollectionsBookRepository repository;

    @Override
    @Transactional
    public CollectionsBookResponse create(long collectionId, long bookId, CollectionsBookRequest request) {
        var entity = this.mapper.toEntity(request);
        entity.setId(this.toPK(collectionId, bookId));

        return this.mapper.toResponse(this.repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionsBookResponse> getAll() {
        return this.mapper.toResponses(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CollectionsBookResponse> get(long collectionId, long bookId) {
        return this.repository.findById(this.toPK(collectionId, bookId)).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public Optional<CollectionsBookResponse> update(long collectionId, long bookId, CollectionsBookRequest request) {
        var id = this.toPK(collectionId, bookId);

        if (!this.repository.existsById(id)) {
            return Optional.empty();
        }

        var entity = this.mapper.toEntity(request);
        entity.setId(id);

        return Optional.of(this.mapper.toResponse(this.repository.save(entity)));
    }

    @Override
    public boolean delete(long collectionId, long bookId) {
        try {
            this.repository.deleteById(this.toPK(collectionId, bookId));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private CollectionsBookPK toPK(long collectionId, long bookId) {
        var id = new CollectionsBookPK();
        id.setBookId(bookId);
        id.setCollectionId(collectionId);

        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionsBookResponse> getAllOrderByRating() {
        return this.mapper.toResponses(this.repository.findAllByOrderByRatingAsc());
    }

}
