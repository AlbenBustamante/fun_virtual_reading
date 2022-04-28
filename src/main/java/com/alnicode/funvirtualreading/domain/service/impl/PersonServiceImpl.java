package com.alnicode.funvirtualreading.domain.service.impl;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;
import com.alnicode.funvirtualreading.domain.service.IPersonService;
import com.alnicode.funvirtualreading.persistence.entity.Person;
import com.alnicode.funvirtualreading.persistence.mapper.PersonMapper;
import com.alnicode.funvirtualreading.persistence.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl extends DeleteService<Person> implements IPersonService {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    @Override
    @Transactional
    public PersonResponse save(PersonRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> getAll() {
        return this.mapper.toResponses(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonResponse> get(long id) {
        return this.repository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public Optional<PersonResponse> update(long id, PersonRequest request) {
        var person = this.repository.findById(id);

        if (!person.isPresent()) {
            return Optional.empty();
        }

        var entity = this.mapper.toEntity(request);
        entity.setId(id);
        entity.setDate(person.get().getDate());
        
        return Optional.of(this.mapper.toResponse(this.repository.save(entity)));
    }

    @Override
    protected CrudRepository<Person, Long> repository() {
        return this.repository;
    }
    
}
