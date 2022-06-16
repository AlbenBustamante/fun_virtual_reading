package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The comment model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Find the comments with the same person id.
     *
     * @param personId the id to search
     * @return a comments list
     */
    Optional<List<Comment>> findByPersonId(long personId);

    /**
     * Find the comments with the same book id.
     *
     * @param bookId the id to search
     * @return a comments list
     */
    Optional<List<Comment>> findByBookId(long bookId);

}
