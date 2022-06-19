package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The book model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Get all the books with the same tag ID.
     *
     * @param tagId the id to search
     * @return an optional of the books found
     */
    Optional<List<Book>> findByTagsId(long tagId);

    /**
     * Find the books with the same user id.
     *
     * @param userId the id to search
     * @return a books list
     */
    Optional<List<Book>> findByUserId(long userId);

    /**
     * Find the books with the same genre id.
     *
     * @param genreId the id to search
     * @return a books list
     */
    Optional<List<Book>> findByGenreId(long genreId);

    /**
     * Find a book by the comment id.
     *
     * @param commentId the id to search
     * @return the book found
     */
    Optional<Book> findByCommentsId(long commentId);

    /**
     * Check if a book with the same title already exists.
     *
     * @param title the title to check
     * @return {@code true} if already exists
     */
    boolean existsByTitle(String title);

    /**
     * Check if a book with the same synopsis already exists.
     *
     * @param synopsis the synopsis to check
     * @return {@code true} if already exists
     */
    boolean existsBySynopsis(String synopsis);

    /**
     * Check if a book with the same body already exists.
     *
     * @param body the body to check
     * @return {@code true} if already exists
     */
    boolean existsByBody(String body);

}
