package com.alnicode.funvirtualreading.domain.service.impl;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.NationalityRequest;
import com.alnicode.funvirtualreading.domain.dto.NationalityResponse;
import com.alnicode.funvirtualreading.domain.service.INationalityService;
import com.alnicode.funvirtualreading.persistence.entity.Nationality;
import com.alnicode.funvirtualreading.persistence.mapper.NationalityMapper;
import com.alnicode.funvirtualreading.persistence.repository.NationalityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NationalityServiceImpl extends DeleteService<Nationality> implements INationalityService {
    @Autowired
    private NationalityMapper mapper;

    @Autowired
    private NationalityRepository repository;

    @Override
    @Transactional
    public NationalityResponse save(NationalityRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NationalityResponse> getAll() {
        return this.mapper.toResponses(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NationalityResponse> get(long id) {
        return this.repository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public Optional<NationalityResponse> update(long id, NationalityRequest request) {
        if (this.repository.existsById(id)) {
            var entity = this.mapper.toEntity(request);
            entity.setId(id);
            return Optional.of(this.mapper.toResponse(entity));
        }
        return Optional.empty();
    }

    @Override
    protected CrudRepository<Nationality, Long> repository() {
        return this.repository;
    }

    @Override
    public List<NationalityResponse> getAllOrderByCountry() {
        return this.mapper.toResponses(this.repository.findAllByOrderByCountry());
    }

    @Override
    public Optional<NationalityResponse> getByCountry(String country) {
        return this.repository.findByCountry(country).map(mapper::toResponse);
    }

    @Override
    public Optional<NationalityResponse> getByAuthorId(long personId) {
        return this.repository.findByPersonsId(personId).map(mapper::toResponse);
    }
    
}
