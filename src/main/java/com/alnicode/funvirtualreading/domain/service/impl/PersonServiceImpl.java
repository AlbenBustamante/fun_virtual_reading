package com.alnicode.funvirtualreading.domain.service.impl;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.domain.dto.PersonRequest;
import com.alnicode.funvirtualreading.domain.dto.PersonResponse;
import com.alnicode.funvirtualreading.domain.service.IPersonService;
import com.alnicode.funvirtualreading.persistence.entity.Person;
import com.alnicode.funvirtualreading.persistence.mapper.PersonMapper;
import com.alnicode.funvirtualreading.persistence.repository.BookRepository;
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

    @Autowired
    private BookRepository bookRepository;

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

	@Override
    @Transactional
	public Optional<PersonResponse> addLike(long personId, long bookId) {
		var person = this.repository.findById(personId);
        var book = this.bookRepository.findById(bookId);

        if (!(person.isPresent() && book.isPresent())) {
            return Optional.empty();
        }

        person.get().addBook(book.get());

        return Optional.of(this.mapper.toResponse(this.repository.save(person.get())));
	}

    @Override
    @Transactional
    public Optional<PersonResponse> removeLike(long personId, long bookId) {
        var person = this.repository.findById(personId);
        var book = this.bookRepository.findById(bookId);

        if (!(person.isPresent() && book.isPresent())) {
            return Optional.empty();
        }

        person.get().removeBook(book.get());

        return Optional.of(this.mapper.toResponse(this.repository.save(person.get())));
    }

    @Override
    public Optional<PersonResponse> getByEmail(String email) {
        return this.repository.findByEmail(email).map(mapper::toResponse);
    }

    @Override
    public Optional<List<PersonResponse>> getByNationality(long nationalityId) {
        return this.repository.findByNationalityId(nationalityId).map(mapper::toResponses);
    }

    @Override
    public Optional<List<PersonResponse>> getByBooksLiked(long bookId) {
        return this.repository.findByLikesBookId(bookId).map(mapper::toResponses);
    }
    
}
