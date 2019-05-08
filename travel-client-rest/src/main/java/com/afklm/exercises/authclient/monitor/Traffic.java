package com.afklm.exercises.authclient.monitor;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traffic {
    private int totalRequest=0;
    private int totalOkResponse=0;
    private int total4xxResponse=0;
    private int total5xxResponse=0;
    private int avgResponseTime=0;
    private int minResponseTime=0;
    private int maxResponseTime=0;

}
