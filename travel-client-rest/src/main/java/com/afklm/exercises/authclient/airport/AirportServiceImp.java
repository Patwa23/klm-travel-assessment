package com.afklm.exercises.authclient.airport;

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
public class AirportServiceImp implements AirportService{

    @Value("${oauth.resource.airport}")
    private String airportResourceUrl;

    @Autowired
    private RestTemplate oauth2resttemplate;

    public CompletableFuture<String> getAirportList(String lang,String size,String page) {
        String url = getAirportListUrl(lang,size,page);
        CompletableFuture<String> cf =  getAirportListAsync(url);
        return createAsyncAirport(cf);
    }

    public CompletableFuture<Location> getAirportListByCode(String lang,String key) {
        String url = getAirportListByCodeUrl(lang,key);
        return getAirportListAsync1(url);
//        CompletableFuture<Location> cf =  getAirportListAsync1(url);
//        return createAsyncAirport(cf);
    }

    public CompletableFuture<String> findAirport(String lang,String term,String size,String page) {
        String url = findAirportUrl(lang,term,size,page);
        CompletableFuture<String> cf =  getAirportListAsync(url);
        return createAsyncAirport(cf);
    }

    private CompletableFuture<String> getAirportListAsync(String url){
        return CompletableFuture.supplyAsync(() ->  {
            return getAiportRest(url);
        });
    }
    private CompletableFuture<Location> getAirportListAsync1(String url){
        return CompletableFuture.supplyAsync(() ->  {
            return getAiportRest1(url);
        });
    }

    private String getAiportRest(String url){
        try {
            String result = oauth2resttemplate.getForObject(url, String.class);
            return result;
        }catch (Exception e){
            throw new FareException("Airport not found",e);
        }
    }

    private Location getAiportRest1(String url){
        try {
            Location result = oauth2resttemplate.getForObject(url, Location.class);
            return result;
        }catch (Exception e){
            throw new FareException("Airport not found",e);
        }
    }

    private String getAirportListUrl(String lang,String size,String page){
        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportResourceUrl)
                // Add query parameter
                .queryParam("lang", lang)
                .queryParam("size", size)
                .queryParam("page", page);
        return builder.toUriString();
    }

    private String getAirportListByCodeUrl(String lang,String key) {
        String url = airportResourceUrl + "/{key}";

        // URI (URL) parameters
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("key", key);

        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("lang", lang);
        return builder.buildAndExpand(uriParams).toUriString();
    }

    private String findAirportUrl(String lang,String term,String size,String page) {
        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportResourceUrl)
                // Add query parameter
                .queryParam("lang", lang)
                .queryParam("term", term)
                .queryParam("size", size)
                .queryParam("page", page);
        return builder.toUriString();
    }

    private CompletableFuture<String> createAsyncAirport(CompletableFuture<String> result){
       return result.thenApplyAsync(s -> {
            log.info(Thread.currentThread().getName());
            return s;
        }).whenComplete((asyncResult,throwable) -> {
            if (throwable == null) {
                result.complete(asyncResult);
            } else {
                result.completeExceptionally(throwable);
            }
        });
    }

}
