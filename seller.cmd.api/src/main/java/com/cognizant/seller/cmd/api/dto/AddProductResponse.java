package com.cognizant.seller.cmd.api.dto;

import com.cognizant.user.core.dto.BaseResponse;

public class AddProductResponse extends BaseResponse {
    private String id;

    public AddProductResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
