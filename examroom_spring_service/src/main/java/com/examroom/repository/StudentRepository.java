package com.examroom.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.examroom.domain.Student;

/**
 * Spring Data  repository for the Student entity.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query(nativeQuery = true, value="select * from student where email_id=?")
	Student findByEmailId(String emailId);
}
