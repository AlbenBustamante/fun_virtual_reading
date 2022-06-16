package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The person model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a person by the email.
     *
     * @param email the email to search
     * @return the person found
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a person by the published book id.
     *
     * @param bookId the id to search
     * @return the person found.
     */
    Optional<User> findByPublishedBooksBookId(long bookId);

    /**
     * Find the persons with the same nationality id.
     *
     * @param nationalityId the id to search.
     * @return the persons list
     */
    Optional<List<User>> findByNationalityId(long nationalityId);

    /**
     * Find the persons with the same book liked.
     *
     * @param bookId the book id to search
     * @return the persons list
     */
    Optional<List<User>> findByLikesBookId(long bookId);

}
