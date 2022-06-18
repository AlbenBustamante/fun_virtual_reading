package com.alnicode.funvirtualreading.domain.service.impl;

import com.alnicode.funvirtualreading.domain.dto.UserRequest;
import com.alnicode.funvirtualreading.domain.dto.UserResponse;
import com.alnicode.funvirtualreading.domain.service.IUserService;
import com.alnicode.funvirtualreading.enums.RoleType;
import com.alnicode.funvirtualreading.exception.RegisterNotValidException;
import com.alnicode.funvirtualreading.persistence.entity.Role;
import com.alnicode.funvirtualreading.persistence.entity.User;
import com.alnicode.funvirtualreading.persistence.mapper.UserMapper;
import com.alnicode.funvirtualreading.persistence.repository.BookRepository;
import com.alnicode.funvirtualreading.persistence.repository.RoleRepository;
import com.alnicode.funvirtualreading.persistence.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.alnicode.funvirtualreading.constants.UserConstants.EMAIL_EXISTS;
import static com.alnicode.funvirtualreading.constants.UserConstants.PASSWORDS_DO_NOT_MATCH;
import static com.alnicode.funvirtualreading.constants.UserConstants.USERNAME_EXISTS;

/**
 * The user service implementation.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public UserResponse create(UserRequest request) throws RegisterNotValidException {
        checkData(request);

        final var entity = mapper.toEntity(request);

        entity.setPassword(encoder.encode(request.getPassword()));
        entity.addRole(getRole());

        return mapper.toResponse(repository.save(entity));
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
        var user = this.repository.findById(id);

        if (user.isEmpty()) {
            return Optional.empty();
        }

        var entity = this.mapper.toEntity(request);
        entity.setId(id);
        entity.setDate(user.get().getDate());

        return Optional.of(this.mapper.toResponse(this.repository.save(entity)));
    }

    @Override
    public CrudRepository<User, Long> repository() {
        return this.repository;
    }

    @Override
    @Transactional
    public Optional<UserResponse> addLike(long userId, long bookId) {
        var user = this.repository.findById(userId);
        var book = this.bookRepository.findById(bookId);

        if (!(user.isPresent() && book.isPresent())) {
            return Optional.empty();
        }

        user.get().addLike(book.get());

        return Optional.of(this.mapper.toResponse(this.repository.save(user.get())));
    }

    @Override
    @Transactional
    public Optional<UserResponse> removeLike(long userId, long bookId) {
        var user = this.repository.findById(userId);
        var book = this.bookRepository.findById(bookId);

        if (!(user.isPresent() && book.isPresent())) {
            return Optional.empty();
        }

        user.get().removeLike(book.get());

        return Optional.of(this.mapper.toResponse(this.repository.save(user.get())));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> getByEmail(String email) {
        return this.repository.findByEmail(email).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UserResponse>> getByNationality(long nationalityId) {
        return this.repository.findByNationalityId(nationalityId).map(mapper::toResponses);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UserResponse>> getByBooksLiked(long bookId) {
        return this.repository.findByLikesBookId(bookId).map(mapper::toResponses);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> getByPublishedBook(long bookId) {
        return this.repository.findByPublishedBooksBookId(bookId).map(mapper::toResponse);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = repository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or email not found, try again"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), toAuthorities(user.getRoles()));
    }

    /**
     * Map a roles' collection to {@link GrantedAuthority} collection.
     *
     * @param roles the collection to be mapped.
     * @return the collection mapped.
     */
    private Collection<GrantedAuthority> toAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    /**
     * Get an existing user role or create and get a new user role.
     *
     * @return the role found
     */
    private Role getRole() {
        final var role = roleRepository.findByName(RoleType.ROLE_USER.getName());

        return role.orElseGet(() -> new Role(RoleType.ROLE_USER));
    }

    private void checkData(UserRequest request) throws RegisterNotValidException {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RegisterNotValidException(USERNAME_EXISTS, "username");
        }

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegisterNotValidException(EMAIL_EXISTS, "email");
        }

        if (!request.passwordsMatch()) {
            throw new RegisterNotValidException(PASSWORDS_DO_NOT_MATCH, "rpassword");
        }
    }

}
