package com.afklm.exercises.authclient.airport.service;

import com.afklm.exercises.authclient.airport.model.Location;
import com.afklm.exercises.authclient.exception.FareException;
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
public class AirportServiceImpl implements AirportService {

    @Value("${spring.resource.airport}")
    private String airportResourceUrl;

    @Autowired
    private RestTemplate oauth2RestTemplate;

    public CompletableFuture<String> getAirportList(String lang, String size, String page) {
        String url = getAirportListUrl(lang, size, page);
        CompletableFuture<String> cf = getAirportListAsync(url);
        return createAsyncAirport(cf);
    }

    public CompletableFuture<Location> getAirportByCode(String lang, String key) {
        String url = getAirportListByCodeUrl(lang, key);
        CompletableFuture<Location> cf =  getAirportByCodeAsync(url);
        return createAsyncAirportByCode(cf);
    }

    public CompletableFuture<String> findAirportsByCode(String lang, String term, String size, String page) {
        String url = findAirportsByCodeUrl(lang, term, size, page);
        CompletableFuture<String> cf = getAirportListAsync(url);
        return createAsyncAirport(cf);
    }

    private CompletableFuture<String> getAirportListAsync(String url) {
        return CompletableFuture.supplyAsync(() -> {
            return getAiportRest(url);
        });
    }

    private CompletableFuture<Location> getAirportByCodeAsync(String url) {
        return CompletableFuture.supplyAsync(() -> {
            return getAiportByCodeRest(url);
        });
    }

    private String getAiportRest(String url) {
        try {
            String result = oauth2RestTemplate.getForObject(url, String.class);
            return result;
        } catch (Exception e) {
            throw new FareException("Airport not found", e);
        }
    }

    private Location getAiportByCodeRest(String url) {
        try {
            Location result = oauth2RestTemplate.getForObject(url, Location.class);
            return result;
        } catch (Exception e) {
            throw new FareException("Airport not found", e);
        }
    }

    private String getAirportListUrl(String lang, String size, String page) {
        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportResourceUrl)
                // Add query parameter
                .queryParam("lang", lang)
                .queryParam("size", size)
                .queryParam("page", page);
        return builder.toUriString();
    }

    private String getAirportListByCodeUrl(String lang, String key) {
        String url = airportResourceUrl + "/{key}";

        // URI (URL) parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("key", key);

        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("lang", lang);
        return builder.buildAndExpand(uriParams).toUriString();
    }

    private String findAirportsByCodeUrl(String lang, String term, String size, String page) {
        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportResourceUrl)
                .queryParam("lang", lang)
                .queryParam("term", term)
                .queryParam("size", size)
                .queryParam("page", page);
        return builder.toUriString();
    }

    private CompletableFuture<String> createAsyncAirport(CompletableFuture<String> result) {
        return result.thenApplyAsync(s -> {
            log.info(Thread.currentThread().getName());
            return s;
        }).whenComplete((asyncResult, throwable) -> {
            if (throwable == null) {
                result.complete(asyncResult);
            } else {
                result.completeExceptionally(throwable);
            }
        });
    }

    private CompletableFuture<Location> createAsyncAirportByCode(CompletableFuture<Location> result) {
        return result.thenApplyAsync(s -> {
            log.info(Thread.currentThread().getName());
            return s;
        }).whenComplete((asyncResult, throwable) -> {
            if (throwable == null) {
                result.complete(asyncResult);
            } else {
                result.completeExceptionally(throwable);
            }
        });
    }
}
