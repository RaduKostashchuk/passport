package ru.job4j.passport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class PassportApplication {

    @Bean
    public static RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        template.setRequestFactory(factory);
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(PassportApplication.class, args);
    }

}
