package com.examroom.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.examroom.domain.School;

/**
 * Spring Data  repository for the School entity.
 */
@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
	
	@Query(nativeQuery = true, value="select * from school where email_id=?")
	School findByEmailId(String emailId);
}
