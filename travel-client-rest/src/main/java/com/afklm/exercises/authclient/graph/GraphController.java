package com.afklm.exercises.authclient.graph;

import com.afklm.exercises.authclient.monitor.HttpTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Map;

@Controller
public class GraphController {

    @Autowired
    HttpTraceService httpTraceService;

    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) throws IOException {
        Map<String,Integer> trafficMap=httpTraceService.calculateRequestResponse();
        model.addAttribute("trafficMap",trafficMap);
        return "barGraph";

    }

}
