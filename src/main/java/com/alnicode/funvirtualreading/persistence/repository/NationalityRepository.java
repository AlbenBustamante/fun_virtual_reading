package com.alnicode.funvirtualreading.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.alnicode.funvirtualreading.persistence.entity.Nationality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Long> {
    List<Nationality> findAllByOrderByCountry();
    Optional<Nationality> findByCountry(String country);
    Optional<Nationality> findByPersonsId(long personId);
}
