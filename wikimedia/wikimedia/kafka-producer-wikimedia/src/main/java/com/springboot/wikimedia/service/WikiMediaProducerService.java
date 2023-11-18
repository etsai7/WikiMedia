package com.springboot.wikimedia.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.springboot.wikimedia.handler.WikiMediaChangesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikiMediaProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaProducerService.class);

    private final String WIKIMEDIA_URL = "https://stream.wikimedia.org/v2/stream/recentchange";

    @Value("${wikimedia.kafka.recentChanges.topic}")
    String wikimediaRecentChangesTopic;

    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    public WikiMediaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        // To read real time stream data from wikimedia, we use event source libraries: okhttp (2 pckgs)

        EventHandler eventHandler = new WikiMediaChangesHandler(kafkaTemplate, wikimediaRecentChangesTopic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(WIKIMEDIA_URL));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
