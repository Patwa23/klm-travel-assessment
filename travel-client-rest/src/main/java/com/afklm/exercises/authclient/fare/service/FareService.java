package com.afklm.exercises.authclient.fare.service;

import com.afklm.exercises.authclient.fare.dto.FareDTO;

import java.util.concurrent.CompletableFuture;

public interface FareService {

    /**
     * Get Fare  between Source and Destination
     *
     * @param origin      Airport Code as source
     * @param destination Airport Code as destination
     * @param currency    the requested resulting currency, supported ones are EUR and USD
     * @return return a fare
     */
    public CompletableFuture<FareDTO> getFares(String origin, String destination, String currency);


}
