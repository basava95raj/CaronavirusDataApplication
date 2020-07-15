package com.application.caronavirustracker.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllStateData {

	private String source;

	@JsonProperty("state_data")
	private List<StateData> state_data;

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return this.source;
	}

	public void setState_data(List<StateData> state_data) {
		this.state_data = state_data;
	}

	public List<StateData> getState_data() {
		return this.state_data;
	}

	@Override
	public String toString() {
		return "AllStateData [source=" + source + ", state_data=" + state_data + "]";
	}
}
