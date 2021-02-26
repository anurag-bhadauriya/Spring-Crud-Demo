package com.examroom.service;

import java.util.List;
import java.util.Optional;

import com.examroom.domain.Credential;
import com.examroom.exception.CustomException;

/**
 * Service Interface for managing {@link Credential}.
 */
public interface CredentialService {

    /**
     * Save a credential.
     *
     * @param credential the entity to save.
     * @return the persisted entity.
     */
    Credential save(Credential credential);

    /**
     * Get all the credentials.
     *
     * @return the list of entities.
     */
    List<Credential> findAll();


    /**
     * Get the "id" credential.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Credential> findOne(Long id);

    /**
     * Delete the "id" credential.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    Boolean existsByIdAndPassword(String emailId, String Password) throws CustomException;
}
