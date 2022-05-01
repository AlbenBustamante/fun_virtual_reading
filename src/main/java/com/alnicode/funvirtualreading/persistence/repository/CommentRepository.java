package com.alnicode.funvirtualreading.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.persistence.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByPersonId(long personId);
    Optional<List<Comment>> findByBookId(long bookId);
}
