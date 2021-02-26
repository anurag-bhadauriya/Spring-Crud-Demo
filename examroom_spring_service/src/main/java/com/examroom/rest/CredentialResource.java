package com.examroom.rest;

import com.examroom.domain.Credential;
import com.examroom.exception.CustomException;
import com.examroom.service.CredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.examroom.domain.Credential}.
 */
@RestController
@RequestMapping("/api")
public class CredentialResource {

    private final Logger log = LoggerFactory.getLogger(CredentialResource.class);

    private static final String ENTITY_NAME = "credential";

    private final CredentialService credentialService;

    public CredentialResource(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    /**
     * {@code POST  /credentials} : Create a new credential.
     *
     * @param credential the credential to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new credential, or with status {@code 400 (Bad Request)} if the credential has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/credentials")
    public ResponseEntity<Credential> createCredential(@Valid @RequestBody Credential credential) throws URISyntaxException, CustomException {
        log.debug("REST request to save Credential : {}", credential);
        if (credential.getId() != null) {
            throw new CustomException("A new credential cannot already have an ID"+ ENTITY_NAME+ "idexists");
        }
        Credential result = credentialService.save(credential);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /credentials} : Updates an existing credential.
     *
     * @param credential the credential to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated credential,
     * or with status {@code 400 (Bad Request)} if the credential is not valid,
     * or with status {@code 500 (Internal Server Error)} if the credential couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/credentials")
    public ResponseEntity<Credential> updateCredential(@Valid @RequestBody Credential credential) throws URISyntaxException, CustomException {
        log.debug("REST request to update Credential : {}", credential);
        if (credential.getId() == null) {
            throw new CustomException("Invalid id"+ ENTITY_NAME+ "idnull");
        }
        Credential result = credentialService.save(credential);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /credentials} : get all the credentials.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of credentials in body.
     */
    @GetMapping("/credentials")
    public List<Credential> getAllCredentials() {
        log.debug("REST request to get all Credentials");
        return credentialService.findAll();
    }

    /**
     * {@code GET  /credentials/:id} : get the "id" credential.
     *
     * @param id the id of the credential to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the credential, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/credentials/{id}")
    public ResponseEntity<Credential> getCredential(@PathVariable Long id) {
        log.debug("REST request to get Credential : {}", id);
        Optional<Credential> credential = credentialService.findOne(id);
        return ResponseEntity.ok().body(credential.get());
    }

    /**
     * {@code DELETE  /credentials/:id} : delete the "id" credential.
     *
     * @param id the id of the credential to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/credentials/{id}")
    public ResponseEntity<String> deleteCredential(@PathVariable Long id) {
        log.debug("REST request to delete Credential : {}", id);
        credentialService.delete(id);
        return ResponseEntity.ok().body("Credential successfully deleted with id: "+ id);
    }
}
