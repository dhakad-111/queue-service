package com.nimesa.tech.pub.service.impl;

import com.nimesa.tech.enums.Topic;
import com.nimesa.tech.pub.service.PublisherService;
import com.nimesa.tech.utils.Redis;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public String message(String message, String topicName) {
        LOGGER.debug("Publishing message to topic {} ", topicName);
        Topic topics = Topic.findByName(topicName);
        this.kafkaTemplate.send(topics.getName(), message);
        // TO-do need to be implemented in memory cache
        Redis.messages.computeIfAbsent(topicName, key -> new ArrayList<>()).add(message);
        return "success";
    }
}
