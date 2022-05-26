package com.cognizant.seller.cmd.api.product.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class RemoveProductCommand {
    @TargetAggregateIdentifier
    private String id;
}
