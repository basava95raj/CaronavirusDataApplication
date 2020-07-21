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
import com.application.caronavirustracker.utility.CaronaVirusDataUtility;

@Controller
public class HomeController {

	@Autowired
	CaronavirusDataService caronavirusDataService;

	@GetMapping("/")
	public String home(Model model) {
		CountryData countryData = caronavirusDataService.getCountryData();
		List<StateData> stateDataList = caronavirusDataService.getStateDataList();
		if (null != countryData && !CollectionUtils.isEmpty(stateDataList)) {
			model.addAttribute("countryData", countryData);
			model.addAttribute("stateDataList", stateDataList);
			return "home";
		} else if (null == countryData && !CollectionUtils.isEmpty(stateDataList)) {
			//fetching data from State API when country API is Down
			countryData = new CountryData();
			countryData.setConfirmed_cases(stateDataList.stream().mapToInt(e -> e.getConfirmed()).sum());
			countryData.setActive_cases(stateDataList.stream().mapToInt(e -> e.getActive()).sum());
			countryData.setRecovered_cases(stateDataList.stream().mapToInt(e -> e.getRecovered()).sum());
			countryData.setDeath_cases(stateDataList.stream().mapToInt(e -> e.getDeaths()).sum());
			model.addAttribute("countryData", countryData);
			model.addAttribute("stateDataList", stateDataList);
			return "home";
		} else {
			model.addAttribute("errorMessage", CaronaVirusDataUtility.apiErrorMessage);
			return "error";
		}
	}

	@GetMapping("/{stateName}")
	public String districts(@PathVariable("stateName") String stateName, Model model) {
		List<StateData> allStateDataList = caronavirusDataService.getStateDataList();
		Stream<StateData> streamStateDataList = allStateDataList.stream()
				.filter(states -> states.getState().equalsIgnoreCase(stateName));
		List<StateData> stateDataList = streamStateDataList.collect(Collectors.toList());
		Map<String, DistrictData> districtDataMap = caronavirusDataService.getDistrictDataMapFromStateName(stateName);
		if (!CollectionUtils.isEmpty(stateDataList) && null != districtDataMap) {
			StateData stateData = stateDataList.get(0);
			List<DistrictData> districtDataList = districtDataMap.entrySet().stream().map(entry -> entry.getValue())
					.collect(Collectors.toList());
			stateData.setDelta_change_confirmed_cases(
					districtDataList.stream().mapToInt(e -> e.getDelta().getConfirmed()).sum());
			stateData.setDelta_change_recovered_cases(
					districtDataList.stream().mapToInt(e -> e.getDelta().getRecovered()).sum());
			stateData.setDelta_change_death_cases(
					districtDataList.stream().mapToInt(e -> e.getDelta().getDeceased()).sum());
			stateData.setDelta_change_active_cases(stateData.getDelta_change_confirmed_cases()
					- (stateData.getDelta_change_recovered_cases() + stateData.getDelta_change_death_cases()));
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
