package com.examroom.service.impl;

import com.examroom.domain.Credential;
import com.examroom.exception.CustomException;
import com.examroom.repository.CredentialRepository;
import com.examroom.service.CredentialService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Credential}.
 */
@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

    private final Logger log = LoggerFactory.getLogger(CredentialServiceImpl.class);
    public static final String USER_NOT_REGISTERED ="No user registered with mailid: : ";

    private final CredentialRepository credentialRepository;

    public CredentialServiceImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Credential save(Credential credential) {
        log.debug("Request to save Credential : {}", credential);
        return credentialRepository.save(credential);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Credential> findAll() {
        log.debug("Request to get all Credentials");
        return credentialRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Credential> findOne(Long id) {
        log.debug("Request to get Credential : {}", id);
        return credentialRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Credential : {}", id);
        credentialRepository.deleteById(id);
    }
    
	@Override
	public Boolean existsByIdAndPassword(String emailId, String password) throws CustomException {
		if (credentialRepository.checkMailExistence(emailId) == 0 ) {
			throw new CustomException(USER_NOT_REGISTERED + ""+ emailId);
		}
		else {
			ExampleMatcher modelMatcher = ExampleMatcher.matching().withIgnorePaths("id");
			Credential cred = new Credential();
			cred.setEmailId(emailId);
			cred.setPassword(password);
			Example<Credential> credExample = Example.of(cred, modelMatcher);
			return credentialRepository.exists(credExample);
		}
	}
}
