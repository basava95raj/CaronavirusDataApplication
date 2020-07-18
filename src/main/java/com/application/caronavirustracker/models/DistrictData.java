package com.application.caronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistrictData {

	private String notes;

	private int active;

	private int confirmed;

	private int deceased;

	private int recovered;

	@JsonProperty("delta")
	private Delta delta;

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getActive() {
		return this.active;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getConfirmed() {
		return this.confirmed;
	}

	public void setDeceased(int deceased) {
		this.deceased = deceased;
	}

	public int getDeceased() {
		return this.deceased;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getRecovered() {
		return this.recovered;
	}

	public void setDelta(Delta delta) {
		this.delta = delta;
	}

	public Delta getDelta() {
		return this.delta;
	}

	@Override
	public String toString() {
		return "DistrictData [notes=" + notes + ", active=" + active + ", confirmed=" + confirmed + ", deceased="
				+ deceased + ", recovered=" + recovered + ", delta=" + delta + "]";
	}

}
