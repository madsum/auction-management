package com.cognizant.seller.cmd.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
