package com.springboot.wikimedia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikiMediaConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaConsumerService.class);

    @KafkaListener(topics = "${wikimedia.kafka.recentChanges.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "wikimediaRecentChangesListenerContainerFactory")
    public void consumeRecentChanges(String message){
        LOGGER.info(message);
    }
}
