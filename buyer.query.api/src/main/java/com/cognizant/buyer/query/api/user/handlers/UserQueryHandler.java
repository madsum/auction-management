package com.cognizant.buyer.query.api.user.handlers;

import com.cognizant.buyer.query.api.dto.UserLookupResponse;
import com.cognizant.buyer.query.api.user.queries.FindAllUsersQuery;
import com.cognizant.buyer.query.api.user.queries.FindUserByIdQuery;
import com.cognizant.buyer.query.api.user.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
