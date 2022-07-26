package com.cognizant.product.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RemoveProductCommand implements Serializable {
    @TargetAggregateIdentifier
    private String id;
}
