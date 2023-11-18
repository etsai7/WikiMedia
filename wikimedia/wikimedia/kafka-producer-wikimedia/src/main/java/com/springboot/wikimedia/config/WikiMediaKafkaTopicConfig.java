package com.springboot.wikimedia.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class WikiMediaKafkaTopicConfig {
    @Value("${wikimedia.kafka.recentChanges.topic}")
    String wikimediaRecentChangesTopic;

    @Bean
    public NewTopic recentChangesTopic(){
        return TopicBuilder.name(wikimediaRecentChangesTopic)
                .partitions(2)
                .build();
    }
}
