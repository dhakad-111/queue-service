package com.nimesa.tech.sub.service.impl;

import com.nimesa.tech.sub.service.DynamicKafkaConsumerService;
import com.nimesa.tech.utils.Redis;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class DynamicKafkaConsumerServiceImpl implements DynamicKafkaConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicKafkaConsumerServiceImpl.class);
    private final ConsumerFactory<String, String> consumerFactory;
    private final ConcurrentHashMap<String, ConcurrentMessageListenerContainer<String, String>> listeners = new ConcurrentHashMap<>();


    @Override
    public String subscribe(String topic) {
        if (listeners.containsKey(topic)) {
            throw new IllegalArgumentException("Already subscribed to topic: " + topic);
        }
        MessageListener<String, String> messageListener = message -> LOGGER.debug("Received Message Key : {} and data :{} ", message.key(), message.value());
        ContainerProperties containerProperties = new ContainerProperties(topic);
        containerProperties.setMessageListener(messageListener);
        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties);
        container.setConcurrency(1); // Set concurrency (optional)
        container.setBeanName(topic); // Assign a bean name for easier management
        container.start();
        listeners.put(topic, container);
        return "success";
    }
}

