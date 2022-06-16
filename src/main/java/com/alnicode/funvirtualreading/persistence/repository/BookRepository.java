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
     * Find the books with the same person id.
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

}
