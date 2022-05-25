package com.cognizant.user.cmd.api.controllers;

import com.cognizant.user.cmd.api.product.commands.AddProductCommand;
import com.cognizant.user.cmd.api.dto.AddProductResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/addProduct")
public class AddProductController {
    private final CommandGateway commandGateway;

    @Autowired
    public AddProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<AddProductResponse> registerUser(@Valid @RequestBody AddProductCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.sendAndWait(command);

            return new ResponseEntity<>(new AddProductResponse(id, "Product added successfully!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing add product request for id - " + id;
            System.out.println(e);

            return new ResponseEntity<>(new AddProductResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
