package com.alnicode.funvirtualreading.persistence.repository;

import java.util.Optional;

import com.alnicode.funvirtualreading.persistence.entity.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByBooksBookId(long bookId);
}
