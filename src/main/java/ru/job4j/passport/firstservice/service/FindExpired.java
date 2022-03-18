package ru.job4j.passport.firstservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.job4j.passport.firstservice.model.Passport;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class FindExpired {
    private final PassportService service;
    private final KafkaTemplate<Integer, Passport> template;

    public FindExpired(PassportService service, KafkaTemplate<Integer, Passport> template) {
        this.service = service;
        this.template = template;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    private void findExpired() {
        List<Passport> expired = service.findExpired();
        for (Passport passport : expired) {
            template.send("expired-passports", passport);
        }
    }
}
