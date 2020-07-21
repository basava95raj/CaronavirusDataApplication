package com.application.caronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Regional {
	private int confirmedCasesIndian;

	private int confirmedCasesForeign;

	private int discharged;

	private int deaths;

	private String loc;

	private int active;

	private int totalConfirmed;

	private int delta_change_confirmed_cases;

	private int delta_change_active_cases;

	private int delta_change_death_cases;

	private int delta_change_recovered_cases;

	public void setConfirmedCasesIndian(int confirmedCasesIndian) {
		this.confirmedCasesIndian = confirmedCasesIndian;
	}

	public int getConfirmedCasesIndian() {
		return this.confirmedCasesIndian;
	}

	public void setConfirmedCasesForeign(int confirmedCasesForeign) {
		this.confirmedCasesForeign = confirmedCasesForeign;
	}

	public int getConfirmedCasesForeign() {
		return this.confirmedCasesForeign;
	}

	public void setDischarged(int discharged) {
		this.discharged = discharged;
	}

	public int getDischarged() {
		return this.discharged;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getDeaths() {
		return this.deaths;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setTotalConfirmed(int totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}

	public int getTotalConfirmed() {
		return this.totalConfirmed;
	}

	public int getDelta_change_confirmed_cases() {
		return delta_change_confirmed_cases;
	}

	public void setDelta_change_confirmed_cases(int delta_change_confirmed_cases) {
		this.delta_change_confirmed_cases = delta_change_confirmed_cases;
	}

	public int getDelta_change_active_cases() {
		return delta_change_active_cases;
	}

	public void setDelta_change_active_cases(int delta_change_active_cases) {
		this.delta_change_active_cases = delta_change_active_cases;
	}

	public int getDelta_change_death_cases() {
		return delta_change_death_cases;
	}

	public void setDelta_change_death_cases(int delta_change_death_cases) {
		this.delta_change_death_cases = delta_change_death_cases;
	}

	public int getDelta_change_recovered_cases() {
		return delta_change_recovered_cases;
	}

	public void setDelta_change_recovered_cases(int delta_change_recovered_cases) {
		this.delta_change_recovered_cases = delta_change_recovered_cases;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Regional [confirmedCasesIndian=" + confirmedCasesIndian + ", confirmedCasesForeign="
				+ confirmedCasesForeign + ", discharged=" + discharged + ", deaths=" + deaths + ", loc=" + loc
				+ ", active=" + active + ", totalConfirmed=" + totalConfirmed + ", delta_change_confirmed_cases="
				+ delta_change_confirmed_cases + ", delta_change_active_cases=" + delta_change_active_cases
				+ ", delta_change_death_cases=" + delta_change_death_cases + ", delta_change_recovered_cases="
				+ delta_change_recovered_cases + "]";
	}
}
