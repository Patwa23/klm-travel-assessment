package com.afklm.exercises.authclient.monitor.service;

import java.io.IOException;
import java.util.Map;

public interface HttpTraceService {

    public String getTraffic();

    /**
     * @return statistics of the application
     * @throws IOException
     */
    public String getTrafficCurrentStatus() throws IOException;

    /**
     *
     * @return calculate all total number of request & response.
     * @throws IOException
     */
    public Map<String, Integer> calculateRequestResponse() throws IOException;


}
