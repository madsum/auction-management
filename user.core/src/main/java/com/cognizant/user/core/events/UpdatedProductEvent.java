package com.cognizant.user.core.events;

import com.cognizant.user.core.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatedProductEvent {
    private String id;
    private Product product;
}
