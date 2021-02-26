package com.examroom.service;

import java.util.List;
import java.util.Optional;

import com.examroom.domain.School;

/**
 * Service Interface for managing {@link School}.
 */
public interface SchoolService {

    /**
     * Save a school.
     *
     * @param school the entity to save.
     * @return the persisted entity.
     */
    School save(School school);

    /**
     * Get all the schools.
     *
     * @return the list of entities.
     */
    List<School> findAll();


    /**
     * Get the "id" school.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<School> findOne(Long id);

    /**
     * Delete the "id" school.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    School findUserByEmail(String emailId);
}
