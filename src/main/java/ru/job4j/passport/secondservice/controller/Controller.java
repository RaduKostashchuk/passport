package ru.job4j.passport.secondservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.firstservice.model.Passport;
import ru.job4j.passport.secondservice.service.Service;

import java.util.List;

@RestController
@RequestMapping("/second")
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        return new ResponseEntity<>(service.save(passport), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestParam(value = "id") int id,
                                       @RequestBody Passport passport) {
        passport.setId(id);
        return new ResponseEntity<>(service.update(passport));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam(value = "id") int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> find(@RequestParam(value = "series", required = false) String series) {
        return new ResponseEntity<>(service.findAll(series), HttpStatus.OK);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> findExpired() {
        return new ResponseEntity<>(service.findExpired(), HttpStatus.OK);
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findToReplace() {
        return new ResponseEntity<>(service.findToReplace(), HttpStatus.OK);
    }
}
