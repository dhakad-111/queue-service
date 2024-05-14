package com.nimesa.tech.sub.service.impl;

import com.nimesa.tech.config.KafkaConfiguration;
import com.nimesa.tech.sub.service.SubscriberService;
import com.nimesa.tech.utils.Redis;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceImpl.class);
    private final KafkaConfiguration configuration;


    @Override
    public List<String> getTopics() throws ExecutionException, InterruptedException {
        return configuration.listTopics();
    }

    @Override
    public List<String> subscribe(String topic, String name) {
        LOGGER.debug("subscribe method with topic : {} and name : {}", topic, name);
        Redis.subscribers.computeIfAbsent(name, key -> new ArrayList<>())
                .add(topic);
        return Redis.messages.get(topic);
    }
}
