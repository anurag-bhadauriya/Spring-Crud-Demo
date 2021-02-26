package com.examroom.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.examroom.domain.Teacher;

/**
 * Spring Data  repository for the Teacher entity.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	@Query(nativeQuery = true, value="select * from teacher where email_id=?")
	Teacher findByEmailId(String emailId);
}
