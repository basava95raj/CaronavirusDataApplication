package com.application.caronavirustracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.application.caronavirustracker.models.AllStateData;
import com.application.caronavirustracker.models.CountryData;
import com.application.caronavirustracker.models.DistrictData;
import com.application.caronavirustracker.models.StateData;
import com.application.caronavirustracker.utility.CaronaVirusDataUtility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class CaronavirusDataService {

	@Value("${caronavirus.country.data.api}")
	private String caronaVirusCountryDataApi;

	@Value("${caronavirus.state.data.api}")
	private String caronaVirusStateDataApi;

	@Value("${caronavirus.state.district.data.api}")
	private String caronavirusStateDistrictDataApi;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Gson gson;

	private CountryData countryData;

	private List<StateData> stateDataList = new ArrayList<StateData>();

	private JsonObject allStateDistrictData;

	@Value("${last.confirmed.country.cases}")
	private int lastConfirmedCountryCases;
	
	@Value("${last.confirmed.country.recovered.cases}")
	private int lastConfirmedCountryRecoveredCases;
	
	@Value("${last.confirmed.country.death.cases}")
	private int lastConfirmedCountryDeathCases;
	
	@Value("${last.confirmed.country.active.cases}")
	private int lastConfirmedCountryActiveCases;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaronavirusDataService.class);

	@PostConstruct
	@Scheduled(fixedDelayString = "${timer.fetch.caronavirus.data.delay}")
	public void fetchCaronaVirusData() {
		try {
			CountryData newCountryData = fetchCountryData();
			this.countryData = newCountryData;
		} catch (Exception e) {
			LOGGER.error(CaronaVirusDataUtility.apiErrorMessage + ":CountryDataAPI: " + e.getMessage());
		}
		try {
			List<StateData> newStateDataList = fetchStateData();
			this.stateDataList = newStateDataList;
		} catch (Exception e) {
			LOGGER.error(CaronaVirusDataUtility.apiErrorMessage + ":StateDataAPI: " + e.getMessage());
		}
		try {
			JsonObject newDistrictDataJson = fetchDistrictData();
			this.allStateDistrictData = newDistrictDataJson;
		} catch (Exception e) {
			LOGGER.error(CaronaVirusDataUtility.apiErrorMessage + ":DistrictDataAPI: " + e.getMessage());
		}

	}

	private JsonObject fetchDistrictData() {
		String districtDataJson = restTemplate.getForObject(caronavirusStateDistrictDataApi, String.class);
		JsonObject newDistrictDataJson = gson.fromJson(districtDataJson, JsonObject.class);
		return newDistrictDataJson;
	}

	private List<StateData> fetchStateData() {
		String stateDataJson = restTemplate.getForObject(caronaVirusStateDataApi, String.class);
		AllStateData allStateData = gson.fromJson(
				stateDataJson.substring(stateDataJson.indexOf('{'), stateDataJson.lastIndexOf('}') + 1),
				AllStateData.class);
		return allStateData.getState_data();
	}

	private CountryData fetchCountryData() {
		String countryDataJson = restTemplate.getForObject(caronaVirusCountryDataApi, String.class);
		CountryData newCountryData = gson.fromJson(
				countryDataJson.substring(countryDataJson.indexOf('{'), countryDataJson.lastIndexOf('}') + 1),
				CountryData.class);
		newCountryData.setDelta_change_confirmed_cases(newCountryData.getDelta_change_active_cases()
				+ newCountryData.getDelta_change_recovered_cases() + newCountryData.getDelta_change_death_cases());
		return newCountryData;
	}

	public CountryData getCountryData() {
		return countryData;
	}

	public void setCountryData(CountryData countryData) {
		this.countryData = countryData;
	}

	public List<StateData> getStateDataList() {
		return stateDataList;
	}

	public void setStateDataList(List<StateData> stateDataList) {
		this.stateDataList = stateDataList;
	}

	public JsonObject getAllStateDistrictData() {
		return allStateDistrictData;
	}

	public void setAllStateDistrictData(JsonObject allStateDistrictData) {
		this.allStateDistrictData = allStateDistrictData;
	}

	public Map<String, DistrictData> getDistrictDataMapFromStateName(String stateName) {
		JsonObject allStateDistrictData = getAllStateDistrictData();
		String mappedStateName = CaronaVirusDataUtility.getDistrictStateName(stateName);
		if (null != mappedStateName) {
			JsonObject stateDistrictData = allStateDistrictData
					.getAsJsonObject(CaronaVirusDataUtility.getDistrictStateName(stateName));
			JsonObject districtData = stateDistrictData.getAsJsonObject(CaronaVirusDataUtility.districtData);
			return districtData.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(),
					entry -> gson.fromJson(entry.getValue(), DistrictData.class)));
		}
		return null;
	}

	public CountryData fetchCountryDataFromStateData(List<StateData> stateDataList2) {
		countryData = new CountryData();
		countryData.setConfirmed_cases(stateDataList2.stream().mapToInt(e -> e.getConfirmed()).sum());
		countryData.setActive_cases(stateDataList2.stream().mapToInt(e -> e.getActive()).sum());
		countryData.setRecovered_cases(stateDataList2.stream().mapToInt(e -> e.getRecovered()).sum());
		countryData.setDeath_cases(stateDataList2.stream().mapToInt(e -> e.getDeaths()).sum());
		
		int deltaConfirmedCountryCases = countryData.getConfirmed_cases() - lastConfirmedCountryCases;
		int deltaConfirmedRecoveredCases = countryData.getRecovered_cases() - lastConfirmedCountryRecoveredCases;
		int deltaConfirmedDeathCases = countryData.getDeath_cases() - lastConfirmedCountryDeathCases;
		int deltaConfirmedActiveCases = countryData.getActive_cases() - lastConfirmedCountryActiveCases;
		
		countryData.setDelta_change_confirmed_cases(deltaConfirmedCountryCases);
		countryData.setDelta_change_active_cases(deltaConfirmedActiveCases);
		countryData.setDelta_change_recovered_cases(deltaConfirmedRecoveredCases);
		countryData.setDelta_change_death_cases(deltaConfirmedDeathCases);
		
		lastConfirmedCountryCases+=deltaConfirmedCountryCases;
		lastConfirmedCountryActiveCases+=deltaConfirmedActiveCases;
		lastConfirmedCountryRecoveredCases+=deltaConfirmedRecoveredCases;
		lastConfirmedCountryDeathCases+=deltaConfirmedDeathCases;
		
		return countryData;
	}
}
