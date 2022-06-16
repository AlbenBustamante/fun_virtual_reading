package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The user model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find a user by the username.
     *
     * @param username the username to search
     * @return the user found
     */
    User findByUsername(String username);

}
