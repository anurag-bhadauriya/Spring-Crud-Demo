package com.examroom.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.examroom.domain.Credential;

/**
 * Spring Data  repository for the Credential entity.
 */
@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {
	
	@Query(nativeQuery = true, value="select count(email_id) from credential where email_id=?")
	Long checkMailExistence(String emailId);
}
