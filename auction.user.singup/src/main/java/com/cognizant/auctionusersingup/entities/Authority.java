package com.cognizant.auctionusersingup.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "AUTHORITY")
//@Table(name = "AUTHORITY")
//@Entity

public class Authority implements GrantedAuthority, Serializable {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Column(name = "ROLE_CODE")
	private String roleCode;

	@Override
	public String getAuthority() {
		return roleCode;
	}
/*
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}*/

}
