package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.Genre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The genre model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    /**
     * Find a genre by the book id.
     *
     * @param bookId the id to search
     * @return the genre found
     */
    Optional<Genre> findByBooksBookId(long bookId);

    /**
     * Check if a genre exists by the name.
     *
     * @param name the name to check
     * @return {@code true} if already exists
     */
    boolean existsByName(String name);

    /**
     * Check if a genre exists by the description.
     *
     * @param description the description to check
     * @return {@code true} if already exists
     */
    boolean existsByDescription(String description);

}
