package com.alnicode.funvirtualreading.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

/**
 * The collections-books id model.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Data
@Embeddable
public class CollectionsBookPK implements Serializable {
    @Column(name = "collection_id")
    private Long collectionId;

    @Column(name = "book_id")
    private Long bookId;
}
