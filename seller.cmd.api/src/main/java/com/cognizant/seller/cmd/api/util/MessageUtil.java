package com.cognizant.seller.cmd.api.util;


import com.cognizant.seller.cmd.api.product.commands.AddProductCommand;
import com.cognizant.user.core.models.QueueCustomMessage;

public class MessageUtil {

    public static QueueCustomMessage makeCustomMessage(byte[] photoByte){
        QueueCustomMessage customMessage = new QueueCustomMessage();
        customMessage.setMessage("Sell it");
        customMessage.setPictureByte(photoByte);
        return customMessage;
    }

    public static QueueCustomMessage makeCustomMessage(AddProductCommand command){
        QueueCustomMessage customMessage = new QueueCustomMessage();
        customMessage.setPrice(command.getProduct().getPrice());
        customMessage.setName(command.getProduct().getName());
        return customMessage;
    }
}
