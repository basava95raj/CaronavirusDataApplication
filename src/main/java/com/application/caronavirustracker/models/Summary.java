package com.application.caronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {

	private int total;

	private int confirmedCasesIndian;

	private int confirmedCasesForeign;

	private int discharged;

	private int deaths;
	
	private int active;

	private int confirmedButLocationUnidentified;

	private int delta_change_confirmed_cases;

	private int delta_change_active_cases;

	private int delta_change_death_cases;

	private int delta_change_recovered_cases;

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return this.total;
	}

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

	public void setConfirmedButLocationUnidentified(int confirmedButLocationUnidentified) {
		this.confirmedButLocationUnidentified = confirmedButLocationUnidentified;
	}

	public int getConfirmedButLocationUnidentified() {
		return this.confirmedButLocationUnidentified;
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
		return "Summary [total=" + total + ", confirmedCasesIndian=" + confirmedCasesIndian + ", confirmedCasesForeign="
				+ confirmedCasesForeign + ", discharged=" + discharged + ", deaths=" + deaths + ", active=" + active
				+ ", confirmedButLocationUnidentified=" + confirmedButLocationUnidentified
				+ ", delta_change_confirmed_cases=" + delta_change_confirmed_cases + ", delta_change_active_cases="
				+ delta_change_active_cases + ", delta_change_death_cases=" + delta_change_death_cases
				+ ", delta_change_recovered_cases=" + delta_change_recovered_cases + "]";
	}
}
