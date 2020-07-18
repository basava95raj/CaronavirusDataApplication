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

import com.application.caronavirustracker.models.CountryData;
import com.application.caronavirustracker.models.DistrictData;
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
	
	@GetMapping("/{stateName}")
	public String districts(@PathVariable("stateName") String stateName,Model model) {
		Stream<StateData> streamStateDataList = caronavirusDataService.getStateDataList().stream().filter(states -> states.getState().equalsIgnoreCase(stateName));
		List<StateData> stateDataList = streamStateDataList.collect(Collectors.toList());
		Map<String, DistrictData> districtDataMap = caronavirusDataService.getDistrictDataMapFromStateName(stateName);
		if(!CollectionUtils.isEmpty(stateDataList) && null!=districtDataMap) {
			StateData stateData = stateDataList.get(0);
			List<DistrictData> districtDataList = districtDataMap.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
			stateData.setDelta_change_confirmed_cases(districtDataList.stream().mapToInt(e -> e.getDelta().getConfirmed()).sum());
			stateData.setDelta_change_recovered_cases(districtDataList.stream().mapToInt(e -> e.getDelta().getRecovered()).sum());
			stateData.setDelta_change_death_cases(districtDataList.stream().mapToInt(e -> e.getDelta().getDeceased()).sum());
			stateData.setDelta_change_active_cases(stateData.getDelta_change_confirmed_cases() - (stateData.getDelta_change_recovered_cases() + stateData.getDelta_change_death_cases()));
			model.addAttribute("stateData", stateData);
			model.addAttribute("districtData", districtDataMap);
			return "district";
		}else {
			return "error";
		}
	}
}
