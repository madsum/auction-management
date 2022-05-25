package com.cognizant.user.core.events;

import com.cognizant.user.core.models.Product;
import com.cognizant.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddProductEvent {
    private String id;
    private Product product;
}
