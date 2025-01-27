package com.anylearn.anylearn_api.application.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.application.dto.messages.EmailMessage;
import com.anylearn.anylearn_api.domain.notification.services.ConsumerService;

@Service
public class EmailConsumerService implements ConsumerService<EmailMessage> {

    @Override
    @RabbitListener(queues = "#{@environment.getProperty('queue.email.name')}")
    public void consumeMessage(EmailMessage message) {
        System.out.println("Receive email message" + message);
        //TODO implement email send
    }

}
