package com.application.caronavirustracker.models;

public class StateData {
	
	private int active;

	private double active_rate;

	private int confirmed;

	private Double death_rate;

	private int deaths;

	private int recovered;

	private double recovered_rate;

	private String state;

	public void setActive(int active) {
		this.active = active;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive_rate(double active_rate) {
		this.active_rate = active_rate;
	}

	public double getActive_rate() {
		return this.active_rate;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getConfirmed() {
		return this.confirmed;
	}

	public Double getDeath_rate() {
		return death_rate;
	}

	public void setDeath_rate(Double death_rate) {
		this.death_rate = death_rate;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getDeaths() {
		return this.deaths;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getRecovered() {
		return this.recovered;
	}

	public void setRecovered_rate(double recovered_rate) {
		this.recovered_rate = recovered_rate;
	}

	public double getRecovered_rate() {
		return this.recovered_rate;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	@Override
	public String toString() {
		return "StateData [active=" + active + ", active_rate=" + active_rate + ", confirmed=" + confirmed
				+ ", death_rate=" + death_rate + ", deaths=" + deaths + ", recovered=" + recovered + ", recovered_rate="
				+ recovered_rate + ", state=" + state + "]";
	}
	
}
