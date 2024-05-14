package com.nimesa.tech.sub.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SubscriberService {
    List<String> getTopics() throws ExecutionException, InterruptedException;

    List<String> subscribe(String topic, String name);
}
