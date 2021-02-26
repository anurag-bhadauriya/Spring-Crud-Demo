package com.examroom.rest;

import com.examroom.domain.Teacher;
import com.examroom.exception.CustomException;
import com.examroom.service.CredentialService;
import com.examroom.service.TeacherService;
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
 * REST controller for managing {@link com.examroom.domain.Teacher}.
 */
@RestController
@RequestMapping("/api")
public class TeacherResource {

    private final Logger log = LoggerFactory.getLogger(TeacherResource.class);
    private static final String ENTITY_NAME = "teacher";

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CredentialService credService;

    /**
     * {@code POST  /teachers} : Create a new teacher.
     *
     * @param teacher the teacher to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new teacher, or with status {@code 400 (Bad Request)} if the teacher has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) throws URISyntaxException, CustomException {
        log.debug("REST request to save Teacher : {}", teacher);
        if (teacher.getId() != null) {
            throw new CustomException("A new teacher cannot already have an ID"+ ENTITY_NAME + "idexists");
        }
        if(teacher.getCredential() != null) {
        	credService.save(teacher.getCredential());
        }
        Teacher result = teacherService.save(teacher);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code PUT  /teachers} : Updates an existing teacher.
     *
     * @param teacher the teacher to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teacher,
     * or with status {@code 400 (Bad Request)} if the teacher is not valid,
     * or with status {@code 500 (Internal Server Error)} if the teacher couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/teachers")
    public ResponseEntity<Teacher> updateTeacher(@Valid @RequestBody Teacher teacher) throws URISyntaxException, CustomException {
        log.debug("REST request to update Teacher : {}", teacher);
        if (teacher.getId() == null) {
            throw new CustomException("Invalid id" + ENTITY_NAME + "idnull");
        }
        Teacher result = teacherService.save(teacher);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /teachers} : get all the teachers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teachers in body.
     */
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        log.debug("REST request to get all Teachers");
        return teacherService.findAll();
    }

    /**
     * {@code GET  /teachers/:id} : get the "id" teacher.
     *
     * @param id the id of the teacher to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the teacher, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
        log.debug("REST request to get Teacher : {}", id);
        Optional<Teacher> teacher = teacherService.findOne(id);
        return ResponseEntity.ok().body(teacher.get());
    }

    /**
     * {@code DELETE  /teachers/:id} : delete the "id" teacher.
     *
     * @param id the id of the teacher to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        log.debug("REST request to delete Teacher : {}", id);
        teacherService.delete(id);
        return ResponseEntity.ok().body("Teacher deleted with id: "+ id);
    }
}
