package com.alnicode.funvirtualreading.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.persistence.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findByPersonId(long personId);
    Optional<List<Book>> findByGenreId(long genreId);
}
