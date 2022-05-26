package com.cognizant.seller.cmd.api.aggregates;

import com.cognizant.seller.cmd.api.product.commands.AddProductCommand;
import com.cognizant.seller.cmd.api.product.commands.RemoveProductCommand;
import com.cognizant.user.core.events.AddProductEvent;
import com.cognizant.user.core.events.RemovedProductEvent;
import com.cognizant.user.core.events.UpdatedProductEvent;
import com.cognizant.user.core.models.Product;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private Product product;

    @CommandHandler
    public ProductAggregate(AddProductCommand command) {
        var newProduct = command.getProduct();
        newProduct.setId(command.getId());

        var event = AddProductEvent.builder()
                .id(command.getId())
                .product(newProduct)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdatedProductEvent command) {
        var updatedProduct = command.getProduct();
        updatedProduct.setId(command.getId());

        var event = UpdatedProductEvent.builder()
                .id(UUID.randomUUID().toString())
                .product(updatedProduct)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveProductCommand command) {
        var event = new RemoveProductCommand(command.getId());
        event.setId(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AddProductEvent event) {
        this.id = event.getId();
        this.product = event.getProduct();
    }

    @EventSourcingHandler
    public void on(UpdatedProductEvent event) {
        this.product = event.getProduct();
    }

    @EventSourcingHandler
    public void on(RemovedProductEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
