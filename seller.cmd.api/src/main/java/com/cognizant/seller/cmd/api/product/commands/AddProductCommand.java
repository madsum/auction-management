package com.cognizant.seller.cmd.api.product.commands;

import com.cognizant.user.core.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
public class AddProductCommand implements Serializable {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no product details were supplied")
    @Valid
    private Product product;

    public AddProductCommand(){

    }
}
