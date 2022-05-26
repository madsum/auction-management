package com.cognizant.seller.cmd.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueueCustomMessage {
    private String message;
    private byte[] pictureByte;
}
