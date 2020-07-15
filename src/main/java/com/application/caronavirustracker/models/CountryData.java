package com.application.caronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryData {

	private int active_cases;

	private double active_rate;

	private int confirmed_cases;

	private int death_cases;

	private double death_rate;

	private int delta_change_active_cases;

	private int delta_change_death_cases;

	private int delta_change_recovered_cases;

	private int last_total_active_cases;

	private int last_total_death_cases;

	private int last_total_recovered_cases;

	private String last_updated;

	private int migrated_cases;

	private int passengers_screened;

	private int recovered_cases;

	private double recovered_rate;

	public void setActive_cases(int active_cases) {
		this.active_cases = active_cases;
	}

	public int getActive_cases() {
		return this.active_cases;
	}

	public void setActive_rate(double active_rate) {
		this.active_rate = active_rate;
	}

	public double getActive_rate() {
		return this.active_rate;
	}

	public void setConfirmed_cases(int confirmed_cases) {
		this.confirmed_cases = confirmed_cases;
	}

	public int getConfirmed_cases() {
		return this.confirmed_cases;
	}

	public void setDeath_cases(int death_cases) {
		this.death_cases = death_cases;
	}

	public int getDeath_cases() {
		return this.death_cases;
	}

	public void setDeath_rate(double death_rate) {
		this.death_rate = death_rate;
	}

	public double getDeath_rate() {
		return this.death_rate;
	}

	public void setDelta_change_active_cases(int delta_change_active_cases) {
		this.delta_change_active_cases = delta_change_active_cases;
	}

	public int getDelta_change_active_cases() {
		return this.delta_change_active_cases;
	}

	public void setDelta_change_death_cases(int delta_change_death_cases) {
		this.delta_change_death_cases = delta_change_death_cases;
	}

	public int getDelta_change_death_cases() {
		return this.delta_change_death_cases;
	}

	public void setDelta_change_recovered_cases(int delta_change_recovered_cases) {
		this.delta_change_recovered_cases = delta_change_recovered_cases;
	}

	public int getDelta_change_recovered_cases() {
		return this.delta_change_recovered_cases;
	}

	public void setLast_total_active_cases(int last_total_active_cases) {
		this.last_total_active_cases = last_total_active_cases;
	}

	public int getLast_total_active_cases() {
		return this.last_total_active_cases;
	}

	public void setLast_total_death_cases(int last_total_death_cases) {
		this.last_total_death_cases = last_total_death_cases;
	}

	public int getLast_total_death_cases() {
		return this.last_total_death_cases;
	}

	public void setLast_total_recovered_cases(int last_total_recovered_cases) {
		this.last_total_recovered_cases = last_total_recovered_cases;
	}

	public int getLast_total_recovered_cases() {
		return this.last_total_recovered_cases;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	public String getLast_updated() {
		return this.last_updated;
	}

	public void setMigrated_cases(int migrated_cases) {
		this.migrated_cases = migrated_cases;
	}

	public int getMigrated_cases() {
		return this.migrated_cases;
	}

	public void setPassengers_screened(int passengers_screened) {
		this.passengers_screened = passengers_screened;
	}

	public int getPassengers_screened() {
		return this.passengers_screened;
	}

	public void setRecovered_cases(int recovered_cases) {
		this.recovered_cases = recovered_cases;
	}

	public int getRecovered_cases() {
		return this.recovered_cases;
	}

	public void setRecovered_rate(double recovered_rate) {
		this.recovered_rate = recovered_rate;
	}

	public double getRecovered_rate() {
		return this.recovered_rate;
	}

	@Override
	public String toString() {
		return "CountryData [active_cases=" + active_cases + ", active_rate=" + active_rate + ", confirmed_cases="
				+ confirmed_cases + ", death_cases=" + death_cases + ", death_rate=" + death_rate
				+ ", delta_change_active_cases=" + delta_change_active_cases + ", delta_change_death_cases="
				+ delta_change_death_cases + ", delta_change_recovered_cases=" + delta_change_recovered_cases
				+ ", last_total_active_cases=" + last_total_active_cases + ", last_total_death_cases="
				+ last_total_death_cases + ", last_total_recovered_cases=" + last_total_recovered_cases
				+ ", last_updated=" + last_updated + ", migrated_cases=" + migrated_cases + ", passengers_screened="
				+ passengers_screened + ", recovered_cases=" + recovered_cases + ", recovered_rate=" + recovered_rate
				+ "]";
	}
	
	
}
