package com.application.caronavirustracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllData {

	private boolean success;
	
	@JsonProperty("data")
	private Data data;

	private String lastRefreshed;

	private String lastOriginUpdate;

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Data getData() {
		return this.data;
	}

	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public String getLastRefreshed() {
		return this.lastRefreshed;
	}

	public void setLastOriginUpdate(String lastOriginUpdate) {
		this.lastOriginUpdate = lastOriginUpdate;
	}

	public String getLastOriginUpdate() {
		return this.lastOriginUpdate;
	}

	@Override
	public String toString() {
		return "AllData [success=" + success + ", data=" + data + ", lastRefreshed=" + lastRefreshed
				+ ", lastOriginUpdate=" + lastOriginUpdate + "]";
	}
}
