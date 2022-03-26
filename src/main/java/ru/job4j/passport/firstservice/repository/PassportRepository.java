package ru.job4j.passport.firstservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.passport.firstservice.model.Passport;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {
    List<Passport> findBySeries(String series);

    boolean existsBySeriesAndNumber(String series, String number);

    @Query(value = "SELECT * FROM passport WHERE expiration <= current_date", nativeQuery = true)
    List<Passport> findExpired();

    @Query(value = "SELECT * FROM passport WHERE expiration >= current_date "
                                            + "and expiration <= current_date + interval '3 month'",
            nativeQuery = true)
    List<Passport> findToReplace();
}
