package com.afklm.exercises.authclient.fare.dto;

import com.afklm.exercises.authclient.airport.model.Location;
import com.afklm.exercises.authclient.fare.enums.Currency;
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
