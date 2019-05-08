package com.afklm.exercises.authclient.fare;

import com.afklm.exercises.authclient.airport.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FareDTO {
    double amount;
    Currency currency;
    Location origin;
    Location destination;
}
