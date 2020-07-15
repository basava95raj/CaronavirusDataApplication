package com.application.caronavirustracker.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.application.caronavirustracker.models.AllStateData;
import com.application.caronavirustracker.models.CountryData;
import com.application.caronavirustracker.models.StateData;
import com.google.gson.Gson;

@Service
public class CaronavirusDataService {

	@Value("${caronavirus.country.data.api}")
	private String caronaVirusCountryDataApi;

	@Value("${caronavirus.state.data.api}")
	private String caronaVirusStateDataApi;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Gson gson;

	private CountryData countryData;

	private List<StateData> stateDataList = new ArrayList<StateData>();

	@PostConstruct
	@Scheduled(fixedDelayString = "${timer.fetch.caronavirus.data.delay}")
	public void fetchCaronaVirusData() {
		CountryData newCountryData = fetchCountryData();
		this.countryData = newCountryData;

		List<StateData> newStateDataList = fetchStateData();
		this.stateDataList = newStateDataList;

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

}
