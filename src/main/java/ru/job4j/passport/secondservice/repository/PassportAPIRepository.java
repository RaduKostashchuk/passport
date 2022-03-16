package ru.job4j.passport.secondservice.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passport.firstservice.model.Passport;

import java.util.List;

@Repository
public class PassportAPIRepository {
    @Value("${api-url}")
    private String url;

    private final RestTemplate client;

    public PassportAPIRepository(RestTemplate client) {
        this.client = client;
    }

    public Passport save(Passport passport) {
        String saveUrl = url + "/save";
        return client
                .postForEntity(saveUrl, passport, Passport.class)
                .getBody();
    }

    public HttpStatus update(Passport passport) {
        String updateUrl = url + "/update?id=" + passport.getId();
        HttpStatus result;
        try {
            result = client
                    .exchange(updateUrl, HttpMethod.PATCH, new HttpEntity<>(passport), Void.class)
                    .getStatusCode();
        } catch (HttpClientErrorException.NotFound e) {
            result = HttpStatus.NOT_FOUND;
            return result;
        }
        return result;
    }

    public void delete(int id) {
        String deleteUrl = url + "/delete?id=" + id;
        client.delete(deleteUrl);
    }

    public List<Passport> findAll(String series) {
        String getAllUrl = url + "/find" + (series != null ? "?series=" + series : "");
        return client
                .exchange(getAllUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() { })
                .getBody();
    }

    public List<Passport> findExpired() {
        String expiredUrl = url + "/unavailable";
        return client
                .exchange(expiredUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() { })
                .getBody();
    }

    public List<Passport> findToReplace() {
        String toReplaceUrl = url + "/find-replaceable";
        return client
                .exchange(toReplaceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() { })
                .getBody();
    }
}
