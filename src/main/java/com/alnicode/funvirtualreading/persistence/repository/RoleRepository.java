package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The role repository.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Find a role by the name.
     *
     * @param name the name to search
     * @return an {@code Optional} of the role found
     */
    Optional<Role> findByName(String name);

}
