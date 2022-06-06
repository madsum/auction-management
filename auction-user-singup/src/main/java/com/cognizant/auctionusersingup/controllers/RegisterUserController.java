package com.cognizant.auctionusersingup.controllers;

import com.cognizant.auctionusersingup.entities.User;
import com.cognizant.auctionusersingup.repository.UserDetailsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class RegisterUserController {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserController(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = null;
        try {
            savedUser =  userDetailsRepository.save(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return  ResponseEntity.ok(savedUser);
    }
}
