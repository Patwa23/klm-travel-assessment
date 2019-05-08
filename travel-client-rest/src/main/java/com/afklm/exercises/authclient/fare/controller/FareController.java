package com.afklm.exercises.authclient.fare.controller;

import com.afklm.exercises.authclient.fare.dto.FareDTO;
import com.afklm.exercises.authclient.fare.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
public class FareController {

    @Autowired
    private FareService fareService;

    @GetMapping("/fares/{origin}/{destination}")
    public CompletableFuture<FareDTO> getFares(@PathVariable("origin") String origin,
                                               @PathVariable("destination") String destination,
                                               @RequestParam(value = "currency", defaultValue = "EUR") String currency) {

        return fareService.getFare(origin, destination, currency);
    }

}
