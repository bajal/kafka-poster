package com.mohbajal.kafkaposter.service;

/**
 * Created by bajal on 5/10/2018.
 * kafka-poster
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

     public void send(String message, String kafkaTopic) {

        kafkaTemplate.send(kafkaTopic, message);
    }
}