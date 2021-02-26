package com.examroom.domain;

import com.examroom.enumeration.UserType;

public class AuthenticateResponseDTO {

	private Long id;
	private UserType entityType;
	private String emailId;
	private String address;
	private String description;
	private String name;
	private String token;

	public AuthenticateResponseDTO() {}
	
	public AuthenticateResponseDTO(Long id, UserType entityType, String emailId, String address, String description,
			String name, String token) {
		super();
		this.id = id;
		this.entityType = entityType;
		this.emailId = emailId;
		this.address = address;
		this.description = description;
		this.name = name;
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserType getEntityType() {
		return entityType;
	}
	public void setEntityType(UserType entityType) {
		this.entityType = entityType;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AuthenticateResponseDTO [id=" + id + ", entityType=" + entityType + ", emailId=" + emailId
				+ ", address=" + address + ", description=" + description + ", name=" + name + ", token=" + token + "]";
	}
	
	
}
