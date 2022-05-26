package com.cognizant.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueueCustomMessage {
    private String message;
    private byte[] pictureByte;
}
