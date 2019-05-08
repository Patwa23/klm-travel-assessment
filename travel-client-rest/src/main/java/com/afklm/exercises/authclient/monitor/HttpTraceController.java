package com.afklm.exercises.authclient.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HttpTraceController {

    @Autowired
    private HttpTraceService httpTraceService;

    @GetMapping("/trace")
    public String getTraffic(){
        return httpTraceService.getTraffic();
    }

    @GetMapping("/traffic")
    public String calculateTraffic()throws IOException {
        return httpTraceService.getTrafficCurrentStatus();
    }


}
