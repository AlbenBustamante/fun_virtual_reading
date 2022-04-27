package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.CollectionsBook;
import com.alnicode.funvirtualreading.persistence.entity.CollectionsBookPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionsBookRepository extends JpaRepository<CollectionsBook, CollectionsBookPK> {
    
}
