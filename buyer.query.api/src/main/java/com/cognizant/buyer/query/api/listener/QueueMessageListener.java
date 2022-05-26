package com.cognizant.buyer.query.api.listener;

import com.cognizant.user.core.models.QueueCustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueMessageListener {

    @RabbitListener(queues = "auction_queue")
    public void listener(QueueCustomMessage message) {
        System.out.println(message);
    }

}
