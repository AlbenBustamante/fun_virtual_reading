package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The tag repository.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    /**
     * Find a tag by the name to get the ID.
     *
     * @param name the name to search
     * @return an optional of the tag found
     */
    Optional<Tag> findByName(String name);

    /**
     * Find all the tags with the same book ID.
     *
     * @param bookId the id to search
     * @return an optional of tags list
     */
    Optional<List<Tag>> findByBooksBookId(long bookId);

}
