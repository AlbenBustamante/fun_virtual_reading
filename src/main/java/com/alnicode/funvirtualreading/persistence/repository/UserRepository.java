package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The user model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by the username or the email
     *
     * @param username the username to search
     * @param email the email to search
     * @return an {@code Optional} of the user found
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * Find a user by the username
     *
     * @param username the username to search
     * @return an {@code Optional} of the user found
     */
    Optional<User> findByUsername(String username);

    /**
     * Find a user by the email.
     *
     * @param email the email to search
     * @return the person found
     */
    Optional<User> findByEmail(String email);

    /**
     * Find an user by the published book id.
     *
     * @param bookId the id to search
     * @return the person found.
     */
    Optional<User> findByPublishedBooksBookId(long bookId);

    /**
     * Find the users with the same nationality id.
     *
     * @param nationalityId the id to search.
     * @return the persons list
     */
    Optional<List<User>> findByNationalityId(long nationalityId);

    /**
     * Find the users with the same book liked.
     *
     * @param bookId the book id to search
     * @return the persons list
     */
    Optional<List<User>> findByLikesBookId(long bookId);

}
