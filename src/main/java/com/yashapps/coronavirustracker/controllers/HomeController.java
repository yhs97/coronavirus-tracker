package com.yashapps.coronavirustracker.controllers;

import com.yashapps.coronavirustracker.models.LocationStats;
import com.yashapps.coronavirustracker.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model){
        //System.out.println(covidDataService.getAllStats());
        List<LocationStats> allStats = covidDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int newCasesToday = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("allStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("newCasesToday", newCasesToday);
        return "home";
    }
}
