package ru.job4j.passport.emailservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.passport.firstservice.model.Passport;

@Component
public class EmailService {
    @KafkaListener(topics = {"expired-passports"})
    public void onApiCall(ConsumerRecord<Integer, Passport> input) {
        Passport passport = input.value();
        System.out.println("Passport № "
                + passport.getSeries()
                + " "
                + passport.getNumber()
                + " is expired.");
    }
}
