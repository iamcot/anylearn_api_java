package com.anylearn.anylearn_api.application.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.application.dto.messages.EmailMessage;
import com.anylearn.anylearn_api.domain.notification.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${queue.email.name}")
    private String emailQueueName;

    @Override
    public void send(String to, String subject, String body) {
        EmailMessage email = new EmailMessage(to, subject, body);
        rabbitTemplate.convertAndSend(emailQueueName, email);
    }
}
