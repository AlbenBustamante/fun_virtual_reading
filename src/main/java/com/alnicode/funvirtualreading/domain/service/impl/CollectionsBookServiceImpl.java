package com.alnicode.funvirtualreading.domain.service.impl;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.CollectionsBookRequest;
import com.alnicode.funvirtualreading.domain.dto.CollectionsBookResponse;
import com.alnicode.funvirtualreading.domain.service.ICollectionsBookService;
import com.alnicode.funvirtualreading.persistence.entity.CollectionsBookPK;
import com.alnicode.funvirtualreading.persistence.mapper.CollectionsBookMapper;
import com.alnicode.funvirtualreading.persistence.repository.CollectionsBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectionsBookServiceImpl implements ICollectionsBookService {
    @Autowired
    private CollectionsBookMapper mapper;

    @Autowired
    private CollectionsBookRepository repository;

    @Override
    @Transactional
    public CollectionsBookResponse save(CollectionsBookRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
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
    
}
