package com.application.caronavirustracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.application.caronavirustracker.models.AllStateData;
import com.application.caronavirustracker.models.CountryData;
import com.application.caronavirustracker.models.DistrictData;
import com.application.caronavirustracker.models.StateData;
import com.application.caronavirustracker.utility.caronaVirusDataUtility;
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

	@PostConstruct
	@Scheduled(fixedDelayString = "${timer.fetch.caronavirus.data.delay}")
	public void fetchCaronaVirusData() {
		CountryData newCountryData = fetchCountryData();
		this.countryData = newCountryData;

		List<StateData> newStateDataList = fetchStateData();
		this.stateDataList = newStateDataList;

		JsonObject newDistrictDataJson = fetchDistrictData();
		this.allStateDistrictData = newDistrictDataJson;
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
		String mappedStateName=caronaVirusDataUtility.getDistrictStateName(stateName);
		if(null!=mappedStateName) {
		JsonObject stateDistrictData = allStateDistrictData.getAsJsonObject(caronaVirusDataUtility.getDistrictStateName(stateName));
		JsonObject districtData = stateDistrictData.getAsJsonObject(caronaVirusDataUtility.districtData);
		return districtData.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> gson.fromJson(entry.getValue(),DistrictData.class)));
	}
		return null;
	}
}
