package com.cognizant.seller.cmd.api.controllers;

import com.cognizant.seller.cmd.api.user.commands.RegisterUserCommand;
import com.cognizant.seller.cmd.api.dto.RegisterUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/registerUser")
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.sendAndWait(command);

            return new ResponseEntity<>(new RegisterUserResponse(id, "User successfully registered! "+command.getUser().getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register user request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new RegisterUserResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
