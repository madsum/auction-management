package com.cognizant.user.query.api.product.handlers;

import com.cognizant.user.core.events.AddProductEvent;
import com.cognizant.user.core.events.RemovedProductEvent;
import com.cognizant.user.core.events.UpdatedProductEvent;
import com.cognizant.user.query.api.repositories.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("product-group")
public class ProductEventHandlerImpl implements ProductEventHandler {

    private final ProductRepository productRepository;

    @Autowired
    public ProductEventHandlerImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    @Override
    public void on(AddProductEvent event) {
        productRepository.save(event.getProduct());
    }

    @EventHandler
    @Override
    public void on(UpdatedProductEvent event) {
        productRepository.save(event.getProduct());
    }

    @EventHandler
    @Override
    public void on(RemovedProductEvent event) {
        productRepository.deleteById(event.getId());
    }
}
