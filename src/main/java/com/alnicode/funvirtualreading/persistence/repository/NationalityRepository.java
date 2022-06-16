package com.alnicode.funvirtualreading.persistence.repository;

import com.alnicode.funvirtualreading.persistence.entity.Nationality;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The nationality model repository.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Long> {

    /**
     * Find all the nationalities ascending.
     *
     * @return the nationalities list sorted
     */
    List<Nationality> findAllByOrderByCountry();

    /**
     * Find a nationality by the country name.
     *
     * @param country the name to search
     * @return the nationality found
     */
    Optional<Nationality> findByCountry(String country);

    /**
     * Find a nationality by the person id.
     *
     * @param personId the id to search
     * @return the nationality found
     */
    Optional<Nationality> findByPersonsId(long personId);

}
