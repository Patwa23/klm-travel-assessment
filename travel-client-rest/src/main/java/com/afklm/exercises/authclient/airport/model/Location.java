package com.afklm.exercises.authclient.airport.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String code, name, description;
    private Coordinates coordinates;
    @JsonInclude(NON_NULL)
    private Location parent;
    @JsonInclude(NON_NULL)
    private Set<Location> children;

}
