package com.afklm.exercises.authclient.fare.service;

import com.afklm.exercises.authclient.airport.service.AirportService;
import com.afklm.exercises.authclient.airport.model.Location;
import com.afklm.exercises.authclient.exception.FareException;
import com.afklm.exercises.authclient.fare.dto.FareDTO;
import com.afklm.exercises.authclient.fare.model.Fare;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class FareServiceImpl implements FareService {

    @Value("${spring.resource.fare}")
    private String fareResourceUrl;

    @Autowired
    private RestTemplate oauth2RestTemplate;

    @Autowired
    private AirportService airportService;

    public CompletableFuture<FareDTO> getFares(String origin, String destination, String currency) {
        CompletableFuture<Location> originResponse = airportService.getAirportByCode("en", origin);
        CompletableFuture<Location> destinationResponse = airportService.getAirportByCode("en", destination);
        CompletableFuture<Fare> fareResponse = getFareAsync(origin, destination, currency);

        try {
            FareDTO fareDTO = new FareDTO();
            CompletableFuture<FareDTO> fareDTOResponse = fareResponse.thenApply(fare -> {
                log.info(Thread.currentThread().getName());
                fareDTO.setAmount(fare.getAmount());
                fareDTO.setCurrency(fare.getCurrency());
                return fareDTO;
            });

            fareDTOResponse.join().setOrigin(originResponse.get());
            fareDTOResponse.join().setDestination(destinationResponse.get());
            return fareDTOResponse;
        } catch (Exception e) {
            throw new FareException("Fare and Airport join problem", e);
        }
    }

    private CompletableFuture<Fare> getFareAsync(String origin, String destination, String currency) {
        return CompletableFuture.supplyAsync(() -> getFareRest(origin, destination, currency));
    }

    private String getFareUrl(String origin, String destination, String currency) {
        String url = fareResourceUrl + "/{origin}/{destination}";

        // URI (URL) parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("origin", origin);
        uriParams.put("destination", destination);

        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("currency", currency);
        return builder.buildAndExpand(uriParams).toUriString();
    }

    private Fare getFareRest(String origin, String destination, String currency) {
        String url = getFareUrl(origin, destination, currency);
        try {
            Fare fare = oauth2RestTemplate.getForObject(url, Fare.class);
            return fare;
        } catch (FareException e) {
            throw new FareException("Fares not found", e);

        }
    }
}
