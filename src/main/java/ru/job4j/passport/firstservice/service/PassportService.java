package ru.job4j.passport.firstservice.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.firstservice.model.Passport;
import ru.job4j.passport.firstservice.repository.PassportRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PassportService {
    private final PassportRepository repository;

    public PassportService(PassportRepository repository) {
        this.repository = repository;
    }

    public Passport save(Passport passport) {
        if (repository.existsBySeriesAndNumber(passport.getSeries(), passport.getNumber())) {
            throw new IllegalArgumentException("Такой паспорт уже существует");
        }
        return repository.save(passport);
    }

    public Passport update(Passport passport) {
        Passport persisted = repository.findById(passport.getId()).orElse(null);
        String name = passport.getName();
        String surname = passport.getSurname();
        String series = passport.getSeries();
        String number = passport.getNumber();
        LocalDate expiration = passport.getExpiration();
        if (persisted != null) {
            if (name != null) {
                persisted.setName(name);
            }
            if (surname != null) {
                persisted.setSurname(surname);
            }
            if (series != null) {
                persisted.setSeries(series);
            }
            if (number != null) {
                persisted.setNumber(number);
            }
            if (expiration != null) {
                persisted.setExpiration(expiration);
            }
            repository.save(persisted);
        }
        return persisted;
    }

    public void delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("Такого паспорта не существует");
        }
    }

    public Iterable<Passport> find(String series) {
        return series != null
                ? repository.findBySeries(series)
                : repository.findAll();
    }

    public List<Passport> findExpired() {
        return repository.findExpired();
    }

    public List<Passport> findToReplace() {
        return repository.findToReplace();
    }
}
