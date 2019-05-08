package com.afklm.exercises.authclient.airport.service;

import com.afklm.exercises.authclient.airport.model.Location;

import java.util.concurrent.CompletableFuture;


public interface AirportService {

    /**
     * Get a list of Airport
     *
     * @param lang the language, supported ones are nl and en
     * @param size the size of the result
     * @param page the page to be selected in the paged response
     * @return
     */
    public CompletableFuture<String> getAirportList(String lang, String size, String page);

    /**
     * Retrieve a specific airport
     *
     * @param lang the language, supported ones are nl and en
     * @param key  Airport Code
     * @return Retrieve a specific airport
     */
    public CompletableFuture<Location> getAirportListByCode(String lang, String key);

    /**
     * Search term that searches through code, name and description.
     *
     * @param lang the language, supported ones are nl and en
     * @param term A search term that searches through code, name and description.
     * @param size the size of the result
     * @param page the page to be selected in the paged response
     * @return
     */
    public CompletableFuture<String> findAirport(String lang, String term, String size, String page);


}

