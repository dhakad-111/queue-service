package com.nimesa.tech.sub;

import com.nimesa.tech.config.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaListeners {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(topics = Constants.TOPIC_CHARACTER, groupId = Constants.GROUP_NAME)
    public void listenToCharTopic(String request){
        this.LOGGER.info("Received characters request: {}", request);
        // TO-DO needs to be implemented based on our requirements.
    }

    @KafkaListener(topics = Constants.TOPIC_NUMBER, groupId = Constants.GROUP_NAME)
    public void listenToNumberTopic(String request){
        this.LOGGER.info("Received numbers request: {}", request);
        // TO-DO needs to be implemented based on our requirements.
    }

}
