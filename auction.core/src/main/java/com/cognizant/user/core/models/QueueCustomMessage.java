package com.cognizant.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueueCustomMessage  implements Serializable {
    private String message;
    private byte[] pictureByte;
}
