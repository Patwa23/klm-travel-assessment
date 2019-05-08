package com.afklm.exercises.authclient.airport.controller;

import com.afklm.exercises.authclient.airport.service.AirportService;
import com.afklm.exercises.authclient.airport.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Slf4j
@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public CompletableFuture<ResponseEntity> getAirportList(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                                            @RequestParam(value = "size", defaultValue = "100") String size,
                                                            @RequestParam(value = "page", defaultValue = "1") String page) {
        return airportService.getAirportList(lang, size, page)
                             .<ResponseEntity>thenApply(ResponseEntity::ok)
                             .exceptionally(handleGetAirportListFailure);
    }

    @GetMapping("/{key}")
    public CompletableFuture<ResponseEntity> getAirportByCode(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                                                  @PathVariable("key") String key) {
        return airportService.getAirportByCode(lang, key)
                             .<ResponseEntity>thenApply(ResponseEntity::ok)
                             .exceptionally(handleGetAirportByCodeFailure.apply(key));
    }

    @GetMapping(params = "term")
    public CompletableFuture<ResponseEntity> findAirportsByCode(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                                         @RequestParam("term") String term,
                                                         @RequestParam(value = "size", defaultValue = "100") String size,
                                                         @RequestParam(value = "page", defaultValue = "1") String page) {
        return airportService.findAirportsByCode(lang, term, size, page)
                             .<ResponseEntity>thenApply(ResponseEntity::ok)
                             .exceptionally(handleGetAirportListFailure);
    }

    private static Function<Throwable, ResponseEntity> handleGetAirportListFailure = throwable -> {
        log.error("Unable to retrieve airports", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    private static Function<String, Function<Throwable, ResponseEntity>> handleGetAirportByCodeFailure = key -> throwable -> {
        log.error(String.format("Unable to retrieve airport for code: %s", key), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
