package com.examroom.rest;

import com.examroom.domain.School;
import com.examroom.exception.CustomException;
import com.examroom.service.CredentialService;
import com.examroom.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.examroom.domain.School}.
 */
@RestController
@RequestMapping("/api")
public class SchoolResource {

    private final Logger log = LoggerFactory.getLogger(SchoolResource.class);
    private static final String ENTITY_NAME = "school";

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private CredentialService credService;

    /**
     * {@code POST  /schools} : Create a new school.
     *
     * @param school the school to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new school, or with status {@code 400 (Bad Request)} if the school has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/schools")
    public ResponseEntity<School> createSchool(@Valid @RequestBody School school) throws URISyntaxException, CustomException {
        log.debug("REST request to save School : {}", school);
        if (school.getId() != null) {
            throw new CustomException("A new school cannot already have an ID"+ ENTITY_NAME+ "idexists");
        }
        if(school.getCredential() !=null ) {
        	credService.save(school.getCredential());
        }
        School result = schoolService.save(school);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /schools} : Updates an existing school.
     *
     * @param school the school to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated school,
     * or with status {@code 400 (Bad Request)} if the school is not valid,
     * or with status {@code 500 (Internal Server Error)} if the school couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/schools")
    public ResponseEntity<School> updateSchool(@Valid @RequestBody School school) throws URISyntaxException, CustomException {
        log.debug("REST request to update School : {}", school);
        if (school.getId() == null) {
            throw new CustomException("Invalid id"+ ENTITY_NAME+ "idnull");
        }
        School result = schoolService.save(school);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /schools} : get all the schools.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of schools in body.
     */
    @GetMapping("/schools")
    public List<School> getAllSchools() {
        log.debug("REST request to get all Schools");
        return schoolService.findAll();
    }

    /**
     * {@code GET  /schools/:id} : get the "id" school.
     *
     * @param id the id of the school to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the school, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/schools/{id}")
    public ResponseEntity<School> getSchool(@PathVariable Long id) {
        log.debug("REST request to get School : {}", id);
        Optional<School> school = schoolService.findOne(id);
        return ResponseEntity.ok().body(school.get());
    }

    /**
     * {@code DELETE  /schools/:id} : delete the "id" school.
     *
     * @param id the id of the school to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/schools/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        log.debug("REST request to delete School : {}", id);
        schoolService.delete(id);
        return ResponseEntity.ok().body("School successfully deleted with id: "+ id);
    }
}
