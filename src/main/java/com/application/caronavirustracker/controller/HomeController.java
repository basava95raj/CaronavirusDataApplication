package com.application.caronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.application.caronavirustracker.models.CountryData;
import com.application.caronavirustracker.models.StateData;
import com.application.caronavirustracker.service.CaronavirusDataService;

@Controller
public class HomeController {

	@Autowired
	CaronavirusDataService caronavirusDataService;

	@GetMapping("/")
	public String home(Model model) {
		CountryData countryData = caronavirusDataService.getCountryData();
		List<StateData> stateDataList = caronavirusDataService.getStateDataList();
		model.addAttribute("countryData", countryData);
		model.addAttribute("stateDataList", stateDataList);
		return "home";
	}
}
