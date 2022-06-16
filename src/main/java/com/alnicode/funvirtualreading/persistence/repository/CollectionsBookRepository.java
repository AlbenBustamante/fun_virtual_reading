package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.CollectionsBook;
import com.alnicode.funvirtualreading.persistence.entity.CollectionsBookPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The collections-books model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface CollectionsBookRepository extends JpaRepository<CollectionsBook, CollectionsBookPK> {

    /**
     * Find all the collections-books ascending.
     *
     * @return the collections-books list sorted
     */
    List<CollectionsBook> findAllByOrderByRatingAsc();

}
