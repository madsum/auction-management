package com.cognizant.buyer.query.api.product.handlers;

import com.cognizant.buyer.query.api.dto.ProductLookupResponse;
import com.cognizant.buyer.query.api.user.queries.FindAllUsersQuery;
import com.cognizant.buyer.query.api.user.queries.FindUserByIdQuery;
import com.cognizant.buyer.query.api.user.queries.SearchUsersQuery;

public interface ProductQueryHandler {
    ProductLookupResponse getProductById(FindUserByIdQuery query);
    ProductLookupResponse searchProducts(SearchUsersQuery query);
    ProductLookupResponse getAllProducts(FindAllUsersQuery query);
}
