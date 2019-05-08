package com.afklm.exercises.authclient.airport.controller;

import com.afklm.exercises.authclient.airport.service.AirportService;
import com.afklm.exercises.authclient.airport.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public CompletableFuture<String> getAirportList(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                                    @RequestParam(value = "size", defaultValue = "100") String size,
                                                    @RequestParam(value = "page", defaultValue = "1") String page) {
        return airportService.getAirportList(lang, size, page);
    }

    @GetMapping("/{key}")
    public CompletableFuture<Location> getAirportListByCode(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                                            @PathVariable("key") String key) {
        return airportService.getAirportListByCode(lang, key);
    }

    @GetMapping(params = "term")
    public CompletableFuture<String> findAirport(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                                 @RequestParam("term") String term,
                                                 @RequestParam(value = "size", defaultValue = "100") String size,
                                                 @RequestParam(value = "page", defaultValue = "1") String page) {
        return airportService.findAirport(lang, term, size, page);
    }

}
