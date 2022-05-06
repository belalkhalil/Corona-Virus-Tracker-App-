package com.example.cotonavirustracker.controllers;

import com.example.cotonavirustracker.Services.CronoaVirusDataService;
import com.example.cotonavirustracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CronoaVirusDataService cronoaVirusDataService;
    // Concept called Model. mapping root UML to home templete
    @GetMapping("/")
    public String atHome(Model model){
        // map to int and sum to get total reported cases
        List<LocationStats> allStats = cronoaVirusDataService.getAllStats();
       int totalReportedCases= allStats.stream().mapToInt(stat -> stat.getLastestTotalcases()).sum();
       int difFromPrevDay = allStats.stream().mapToInt(stat -> stat.getDifFromPrevDay()).sum();
        // print out locationStats column on screen
       model.addAttribute("locationStats",allStats);
        // print out total reported cases on screen
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("difFromPrevDay",difFromPrevDay);

    return "home";
    }
}
