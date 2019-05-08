package com.afklm.exercises.authclient.fare.controller;

import com.afklm.exercises.authclient.fare.dto.FareDTO;
import com.afklm.exercises.authclient.fare.service.FareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Slf4j
@RestController
public class FareController {

    @Autowired
    private FareService fareService;

    @GetMapping("/fares/{origin}/{destination}")
    public CompletableFuture<ResponseEntity> getFares(@PathVariable("origin") String origin,
                                                      @PathVariable("destination") String destination,
                                                      @RequestParam(value = "currency", defaultValue = "EUR") String currency) {
        return fareService.getFares(origin, destination, currency)
                          .<ResponseEntity>thenApply(ResponseEntity::ok)
                          .exceptionally(handleFindFaresFailure);
    }

    private static Function<Throwable, ResponseEntity> handleFindFaresFailure = throwable -> {
        log.error("Unable to retrieve fares", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };


}
