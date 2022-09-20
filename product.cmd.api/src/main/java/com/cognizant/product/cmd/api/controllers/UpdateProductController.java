package com.cognizant.product.cmd.api.controllers;

import com.cognizant.core.dto.BaseResponse;
import com.cognizant.product.cmd.api.service.ProductDbService;
import com.cognizant.product.cmd.api.commands.UpdateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/updateProduct")
public class UpdateProductController {
    private final CommandGateway commandGateway;
    private final ProductDbService productDbService;

    @Autowired
    public UpdateProductController(CommandGateway commandGateway, ProductDbService productDbService) {
        this.commandGateway = commandGateway;
        this.productDbService = productDbService;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id,
                                                   @Valid  @RequestBody UpdateProductCommand command) {
        try {
           // command.set   Id(id);
           // commandGateway.sendAndWait(command);
            productDbService.updateProduct(command.getProduct());
            return new ResponseEntity<>(new BaseResponse("Product successfully updated!"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing update product request for id - " + id;
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
