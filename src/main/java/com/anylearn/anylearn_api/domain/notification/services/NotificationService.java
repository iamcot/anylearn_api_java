package com.anylearn.anylearn_api.domain.notification.services;

public interface NotificationService {
    void send(String to, String subject, String body);
}
