package com.application.caronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Delta {

	private int confirmed;

    private int deceased;

    private int recovered;

    public void setConfirmed(int confirmed){
        this.confirmed = confirmed;
    }
    public int getConfirmed(){
        return this.confirmed;
    }
    public void setDeceased(int deceased){
        this.deceased = deceased;
    }
    public int getDeceased(){
        return this.deceased;
    }
    public void setRecovered(int recovered){
        this.recovered = recovered;
    }
    public int getRecovered(){
        return this.recovered;
    }
	@Override
	public String toString() {
		return "Delta [confirmed=" + confirmed + ", deceased=" + deceased + ", recovered=" + recovered + "]";
	}
    
    
	
}
