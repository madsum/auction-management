package com.cognizant.user.query.api.product.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchProductQuery {
    private String filter;
}
