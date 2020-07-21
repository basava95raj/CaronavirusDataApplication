package com.application.caronavirustracker.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	String day;

	@JsonProperty("summary")
	private Summary summary;

	@JsonProperty("regional")
	private List<Regional> regional;

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public Summary getSummary() {
		return this.summary;
	}

	public List<Regional> getRegional() {
		return regional;
	}

	public void setRegional(List<Regional> regional) {
		this.regional = regional;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "Data [day=" + day + ", summary=" + summary + ", regional=" + regional + "]";
	}
}
