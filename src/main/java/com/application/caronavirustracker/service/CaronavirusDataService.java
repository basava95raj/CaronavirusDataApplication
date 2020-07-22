package com.application.caronavirustracker.service;

import java.lang.reflect.Type;
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

import com.application.caronavirustracker.models.AllData;
import com.application.caronavirustracker.models.Data;
import com.application.caronavirustracker.models.DistrictData;
import com.application.caronavirustracker.models.Regional;
import com.application.caronavirustracker.models.Summary;
import com.application.caronavirustracker.utility.CaronaVirusDataUtility;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@Service
public class CaronavirusDataService {

	@Value("${caronavirus.india.data.latest.api}")
	private String caronavirusIndiaDataLatestApi;

	@Value("${caronavirus.india.data.history.api}")
	private String caronavirusIndiaDataHistoryApi;

	@Value("${caronavirus.state.district.data.api}")
	private String caronavirusStateDistrictDataApi;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Gson gson;

	private AllData allData;

	private JsonObject allStateDistrictData;

	private static final Logger LOGGER = LoggerFactory.getLogger(CaronavirusDataService.class);

	@PostConstruct
	@Scheduled(fixedDelayString = "${timer.fetch.caronavirus.data.delay}")
	public void fetchCaronaVirusData() {
		try {
			AllData newAllData = fetchAllData();
			this.allData = newAllData;
			LOGGER.debug(allData.toString());
		} catch (Exception e) {
			LOGGER.error(CaronaVirusDataUtility.apiErrorMessage + ":AllDataAPI: " + e.getMessage());
		}

		try {
			JsonObject newDistrictDataJson = fetchDistrictData();
			this.allStateDistrictData = newDistrictDataJson;
			LOGGER.debug(allStateDistrictData.toString());
		} catch (Exception e) {
			LOGGER.error(CaronaVirusDataUtility.apiErrorMessage + ":DistrictDataAPI: " + e.getMessage());
		}
	}

	private AllData fetchAllData() {
		AllData allData = restTemplate.getForObject(caronavirusIndiaDataLatestApi, AllData.class);
		String allDataHistoryJson = restTemplate.getForObject(caronavirusIndiaDataHistoryApi, String.class);
		JsonObject newAllDataHistoryJson = gson.fromJson(allDataHistoryJson, JsonObject.class);
		JsonElement dataListElement = newAllDataHistoryJson.get("data");
		Type listType = new TypeToken<List<Data>>() {
		}.getType();
		List<Data> dataList = gson.fromJson(dataListElement, listType);
		Data data = dataList.get(dataList.size() - 2);

		// CountryData
		Summary previousDaySummary = data.getSummary();
		Summary currentDaySummary = allData.getData().getSummary();
		currentDaySummary.setActive(
				currentDaySummary.getTotal() - (currentDaySummary.getDischarged() + currentDaySummary.getDeaths()));
		currentDaySummary.setDelta_change_confirmed_cases(currentDaySummary.getTotal() - previousDaySummary.getTotal());
		currentDaySummary.setDelta_change_recovered_cases(
				currentDaySummary.getDischarged() - previousDaySummary.getDischarged());
		currentDaySummary.setDelta_change_death_cases(currentDaySummary.getDeaths() - previousDaySummary.getDeaths());
		currentDaySummary.setDelta_change_active_cases(currentDaySummary.getDelta_change_confirmed_cases()
				- (currentDaySummary.getDelta_change_recovered_cases()
						+ currentDaySummary.getDelta_change_death_cases()));

		// StateData
		int index = 0;
		List<Regional> previousStateDataList = data.getRegional();
		for (Regional state : allData.getData().getRegional()) {
			Regional previousDayStateData = previousStateDataList.get(index);
			state.setActive(state.getTotalConfirmed() - (state.getDischarged() + state.getDeaths()));
			state.setDelta_change_confirmed_cases(state.getTotalConfirmed() - previousDayStateData.getTotalConfirmed());
			state.setDelta_change_recovered_cases(state.getDischarged() - previousDayStateData.getDischarged());
			state.setDelta_change_death_cases(state.getDeaths() - previousDayStateData.getDeaths());
			state.setDelta_change_active_cases(state.getDelta_change_confirmed_cases()
					- (state.getDelta_change_recovered_cases() + state.getDelta_change_death_cases()));
			index++;
		}

		return allData;
	}

	private JsonObject fetchDistrictData() {
		String districtDataJson = restTemplate.getForObject(caronavirusStateDistrictDataApi, String.class);
		JsonObject newDistrictDataJson = gson.fromJson(districtDataJson, JsonObject.class);
		return newDistrictDataJson;
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

	public AllData getAllData() {
		return allData;
	}

	public void setAllData(AllData allData) {
		this.allData = allData;
	}

	public JsonObject getAllStateDistrictData() {
		return allStateDistrictData;
	}

	public void setAllStateDistrictData(JsonObject allStateDistrictData) {
		this.allStateDistrictData = allStateDistrictData;
	}
}
