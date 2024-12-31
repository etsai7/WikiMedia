package com.springboot.wikimedia;

import com.springboot.wikimedia.service.WikiMediaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WikiMediaProducerApplication implements CommandLineRunner {
    @Autowired
    private WikiMediaProducerService wikiMediaProducerService;

    public static void main(String [] args){
        SpringApplication.run(WikiMediaProducerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        wikiMediaProducerService.sendMessage();
    }
}
