package com.afklm.exercises.authclient.fare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fare {
    double amount;
    Currency currency;
    String origin;
    String destination;
}
