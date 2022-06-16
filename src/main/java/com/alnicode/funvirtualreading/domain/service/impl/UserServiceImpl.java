package com.alnicode.funvirtualreading.domain.service.impl;

import com.alnicode.funvirtualreading.domain.dto.UserRequest;
import com.alnicode.funvirtualreading.domain.dto.UserResponse;
import com.alnicode.funvirtualreading.domain.service.IUserService;
import com.alnicode.funvirtualreading.persistence.entity.User;
import com.alnicode.funvirtualreading.persistence.mapper.UserMapper;
import com.alnicode.funvirtualreading.persistence.repository.BookRepository;
import com.alnicode.funvirtualreading.persistence.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends DeleteService<User> implements IUserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public UserResponse save(UserRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return this.mapper.toResponses(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> get(long id) {
        return this.repository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public Optional<UserResponse> update(long id, UserRequest request) {
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
    protected CrudRepository<User, Long> repository() {
        return this.repository;
    }

    @Override
    @Transactional
    public Optional<UserResponse> addLike(long personId, long bookId) {
        var person = this.repository.findById(personId);
        var book = this.bookRepository.findById(bookId);

        if (!(person.isPresent() && book.isPresent())) {
            return Optional.empty();
        }

        person.get().addLike(book.get());

        return Optional.of(this.mapper.toResponse(this.repository.save(person.get())));
    }

    @Override
    @Transactional
    public Optional<UserResponse> removeLike(long personId, long bookId) {
        var person = this.repository.findById(personId);
        var book = this.bookRepository.findById(bookId);

        if (!(person.isPresent() && book.isPresent())) {
            return Optional.empty();
        }

        person.get().removeLike(book.get());

        return Optional.of(this.mapper.toResponse(this.repository.save(person.get())));
    }

    @Override
    public Optional<UserResponse> getByEmail(String email) {
        return this.repository.findByEmail(email).map(mapper::toResponse);
    }

    @Override
    public Optional<List<UserResponse>> getByNationality(long nationalityId) {
        return this.repository.findByNationalityId(nationalityId).map(mapper::toResponses);
    }

    @Override
    public Optional<List<UserResponse>> getByBooksLiked(long bookId) {
        return this.repository.findByLikesBookId(bookId).map(mapper::toResponses);
    }

    @Override
    public Optional<UserResponse> getByPublishedBook(long bookId) {
        return this.repository.findByPublishedBooksBookId(bookId).map(mapper::toResponse);
    }

}
