package com.alnicode.funvirtualreading.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.persistence.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    Optional<List<Person>> findByNationalityId(long nationalityId);
    Optional<List<Person>> findByLikesBookId(long bookId);
}
