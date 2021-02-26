package com.examroom.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examroom.config.jwt.JwtTokenUtil;
import com.examroom.domain.AuthenticateRequestDTO;
import com.examroom.domain.AuthenticateResponseDTO;
import com.examroom.domain.School;
import com.examroom.domain.Student;
import com.examroom.domain.Teacher;
import com.examroom.enumeration.UserType;
import com.examroom.exception.CustomException;
import com.examroom.service.CredentialService;
import com.examroom.service.SchoolService;
import com.examroom.service.StudentService;
import com.examroom.service.TeacherService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticateResource {
	private final String INCORRECT_PASSWORD="Incorrect Password";
	
	@Autowired
	private CredentialService credService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateResponseDTO> createAuthenticationToken(
			@RequestBody AuthenticateRequestDTO authRequest) throws Exception {
		AuthenticateResponseDTO result = authenticateUser(authRequest);
		final UserDetails userDetails = new User( authRequest.getEmailId(), authRequest.getPassword(),
				new ArrayList<>());
		final String token = jwtTokenUtil.generateToken(userDetails);
		result.setToken("Bearer "+ token);
		return ResponseEntity.ok().body(result);
	}
		
	public AuthenticateResponseDTO authenticateUser(
			AuthenticateRequestDTO userData) throws CustomException{
		UserType userType = userData.getEntityType();
		Boolean isCredentialPresent = credService.existsByIdAndPassword(userData.getEmailId(), userData.getPassword());
		if(isCredentialPresent) {
			AuthenticateResponseDTO result = getEntityDetails(userData.getEmailId(), userType);
			return result;
		}
		else {
			throw new CustomException(INCORRECT_PASSWORD);
		}
	}
	
	public AuthenticateResponseDTO getEntityDetails(String emailId, UserType userType) {
		AuthenticateResponseDTO entityDetails = new AuthenticateResponseDTO();
		switch(userType) {
		case SCHOOL:
			School school = schoolService.findUserByEmail(emailId);
			entityDetails.setId(school.getId()); entityDetails.setEntityType(userType);
			entityDetails.setEmailId(school.getEmailId()); entityDetails.setAddress(school.getAddress());
			entityDetails.setDescription(school.getDescription()); entityDetails.setName(school.getSchoolName());
			break;
		case TEACHER:
			Teacher teacher = teacherService.findUserByEmail(emailId);
			entityDetails.setId(teacher.getId()); entityDetails.setEntityType(userType);
			entityDetails.setEmailId(teacher.getEmailId()); entityDetails.setAddress(teacher.getAddress());
			entityDetails.setDescription(teacher.getDescription()); entityDetails.setName(teacher.getTeacherName());
			System.out.println("Teacher");
			break;
		case STUDENT:
			Student student = studentService.findUserByEmail(emailId);
			entityDetails.setId(student.getId()); entityDetails.setEntityType(userType);
			entityDetails.setEmailId(student.getEmailId()); entityDetails.setAddress(student.getAddress());
			entityDetails.setDescription(student.getDescription()); entityDetails.setName(student.getStudentName());
			System.out.println("Student");
			break;
		}
		return entityDetails;
	}

}
