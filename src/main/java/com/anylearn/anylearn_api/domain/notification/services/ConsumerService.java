package com.anylearn.anylearn_api.domain.notification.services;

public interface ConsumerService<T> {
    void consumeMessage(T message);
}
