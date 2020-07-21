package com.application.caronavirustracker.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.application.caronavirustracker.models.AllData;
import com.application.caronavirustracker.models.DistrictData;
import com.application.caronavirustracker.models.Regional;
import com.application.caronavirustracker.service.CaronavirusDataService;
import com.application.caronavirustracker.utility.CaronaVirusDataUtility;

@Controller
public class HomeController {

	@Autowired
	CaronavirusDataService caronavirusDataService;

	@GetMapping("/")
	public String home(Model model) {
		AllData allData = caronavirusDataService.getAllData();
		if (null != allData) {
			model.addAttribute("allData", allData);
			return "home";
		} else {
			model.addAttribute("errorMessage", CaronaVirusDataUtility.apiErrorMessage);
			return "error";
		}
	}

	@GetMapping("/{stateName}")
	public String districts(@PathVariable("stateName") String stateName, Model model) {
		AllData allData = caronavirusDataService.getAllData();
		List<Regional> allStateDataList = allData.getData().getRegional();
		Stream<Regional> streamStateDataList = allStateDataList.stream()
				.filter(states -> states.getLoc().equalsIgnoreCase(stateName));
		List<Regional> stateDataList = streamStateDataList.collect(Collectors.toList());
		Map<String, DistrictData> districtDataMap = caronavirusDataService.getDistrictDataMapFromStateName(stateName);
		if (!CollectionUtils.isEmpty(stateDataList) && null != districtDataMap) {
			Regional stateData = stateDataList.get(0);
			model.addAttribute("stateData", stateData);
			model.addAttribute("allStateDataList", allStateDataList);
			model.addAttribute("districtData", districtDataMap);
			return "district";
		} else {
			model.addAttribute("errorMessage", CaronaVirusDataUtility.apiErrorMessage);
			return "error";
		}
	}
}
