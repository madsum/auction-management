package com.cognizant.auctionusersingup.responses;

import com.cognizant.auctionusersingup.entities.User;

public class LoginResponse {
	
	private String token;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private User user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
