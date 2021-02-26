package com.examroom.domain;

import com.examroom.enumeration.UserType;

public class AuthenticateRequestDTO {

	private String emailId;
	private String password;
	private UserType entityType;
	
	public AuthenticateRequestDTO(String emailId, String password, UserType entityType) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.entityType = entityType;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserType getEntityType() {
		return entityType;
	}
	
	public void setEntityType(UserType entityType) {
		this.entityType = entityType;
	}
	
	@Override
	public String toString() {
		return "AuthenticateDTO [emailId=" + emailId + ", password=" + password + ", entityType=" + entityType + "]";
	}
	
}
