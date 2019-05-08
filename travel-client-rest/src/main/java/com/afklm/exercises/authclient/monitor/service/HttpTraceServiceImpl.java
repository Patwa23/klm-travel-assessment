package com.afklm.exercises.authclient.monitor.service;

import com.afklm.exercises.authclient.monitor.model.Traffic;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class HttpTraceServiceImpl implements HttpTraceService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.resource.trace}")
    private String traceResourceUrl;

    Map<String, Integer> trafficMap = new HashMap<>();

    public String getTraffic() {
        return restTemplate.getForObject(traceResourceUrl, String.class);
    }

    public String getTrafficCurrentStatus() throws IOException {
        Map<String, Integer> map = calculateRequestResponse();
        return new ObjectMapper().writeValueAsString(map);
    }

    public Map<String, Integer> calculateRequestResponse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(getTraffic());
        Traffic trafficObj = new Traffic();
        trafficObj = countTotalOKResponse(actualObj, trafficObj);
        trafficMap = new HashMap<>();
        trafficMap.put("totalRequest", trafficObj.getTotalRequest());
        trafficMap.put("totalOkResponse", trafficObj.getTotalOkResponse());
        trafficMap.put("total4xxResponse", trafficObj.getTotal4xxResponse());
        trafficMap.put("total5xxResponse", trafficObj.getTotal5xxResponse());
        trafficMap.put("avgResponseTime", trafficObj.getAvgResponseTime());
        trafficMap.put("minResponseTime", trafficObj.getMinResponseTime());
        trafficMap.put("maxResponseTime", trafficObj.getMaxResponseTime());
        return trafficMap;

    }

    private int countTotalRequest(JsonNode actualObj) {
        return actualObj.get("traces").size();
    }

    private Traffic countTotalOKResponse(JsonNode actualObj, Traffic trafficObj) {
        JsonNode list = actualObj.findValue("traces");
        trafficObj.setTotalRequest(countTotalRequest(actualObj));
        for (JsonNode a : list) {
            int intValue = a.get("response").get("status").getIntValue();
            if (intValue == 200) {
                trafficObj.setTotalOkResponse(trafficObj.getTotalOkResponse() + 1);
            } else if (Integer.toString(intValue).startsWith("4")) {
                trafficObj.setTotal4xxResponse(trafficObj.getTotal4xxResponse() + 1);
            } else if (Integer.toString(intValue).startsWith("5")) {
                trafficObj.setTotal5xxResponse(trafficObj.getTotal5xxResponse() + 1);
            }

            int responseTime = a.get("timeTaken").getIntValue();
            int minResponseTime = trafficObj.getMinResponseTime();
            if (responseTime < minResponseTime || minResponseTime == 0) {
                trafficObj.setMinResponseTime(responseTime);
            }
            if (responseTime > trafficObj.getMaxResponseTime()) {
                trafficObj.setMaxResponseTime(responseTime);
            }
            int totalResponseTime = 0;
            totalResponseTime = totalResponseTime + responseTime;
            trafficObj.setAvgResponseTime(totalResponseTime / trafficObj.getTotalRequest());
        }
        return trafficObj;
    }


}
