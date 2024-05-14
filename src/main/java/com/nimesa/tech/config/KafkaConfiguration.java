package com.nimesa.tech.config;

import com.nimesa.tech.enums.Topic;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {
    private final KafkaAdmin kafkaAdmin;

    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @Bean
    public NewTopic characters() {
        return TopicBuilder.name(Topic.TOPIC_CHARACTER.getName()).build();
    }

    @Bean
    public NewTopic numbers() {
        return TopicBuilder.name(Topic.TOPIC_NUMBER.getName()).build();
    }

    public List<String> listTopics() throws ExecutionException, InterruptedException {
        return adminClient().listTopics(new ListTopicsOptions().listInternal(true)).listings().get().stream().map(TopicListing::name).toList();
    }
}
