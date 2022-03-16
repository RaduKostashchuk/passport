package ru.job4j.passport.secondservice.service;

import org.springframework.http.HttpStatus;
import ru.job4j.passport.firstservice.model.Passport;
import ru.job4j.passport.secondservice.repository.PassportAPIRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final PassportAPIRepository repository;

    public Service(PassportAPIRepository repository) {
        this.repository = repository;
    }

    public Passport save(Passport passport) {
        return repository.save(passport);
    }

    public HttpStatus update(Passport passport) {
        return repository.update(passport);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public List<Passport> findAll(String series) {
        return repository.findAll(series);
    }

    public List<Passport> findExpired() {
        return repository.findExpired();
    }

    public List<Passport> findToReplace() {
        return repository.findToReplace();
    }
}
