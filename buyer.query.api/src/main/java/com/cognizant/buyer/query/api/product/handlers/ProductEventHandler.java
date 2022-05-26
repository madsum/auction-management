package com.cognizant.buyer.query.api.product.handlers;

import com.cognizant.user.core.events.*;

public interface ProductEventHandler {
    void on(AddProductEvent event);
    void on(UpdatedProductEvent event);
    void on(RemovedProductEvent event);
}
