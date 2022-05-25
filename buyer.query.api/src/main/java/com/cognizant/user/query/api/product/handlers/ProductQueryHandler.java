package com.cognizant.user.query.api.product.handlers;

import com.cognizant.user.query.api.dto.ProductLookupResponse;
import com.cognizant.user.query.api.user.queries.FindAllUsersQuery;
import com.cognizant.user.query.api.user.queries.FindUserByIdQuery;
import com.cognizant.user.query.api.user.queries.SearchUsersQuery;

public interface ProductQueryHandler {
    ProductLookupResponse getProductById(FindUserByIdQuery query);
    ProductLookupResponse searchProducts(SearchUsersQuery query);
    ProductLookupResponse getAllProducts(FindAllUsersQuery query);
}
