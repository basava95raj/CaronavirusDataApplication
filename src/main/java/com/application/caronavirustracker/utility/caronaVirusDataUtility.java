package com.application.caronavirustracker.utility;

public final class CaronaVirusDataUtility {

	
	public static final String districtData="districtData";
	public static final String apiErrorMessage = "Unexpected Error occured while fetching the data from API";

	public static String getDistrictStateName(String stateName) {
		if("Andaman and Nicobar".equalsIgnoreCase(stateName)) {
			return "Andaman and Nicobar Islands";
		}else if("Andhra Pradesh".equalsIgnoreCase(stateName)) {
			return "Andhra Pradesh";
		}else if("Arunachal Pradesh".equalsIgnoreCase(stateName)) {
			return "Arunachal Pradesh";
		}else if("Assam".equalsIgnoreCase(stateName)) {
			return "Assam";
		}else if("Bihar".equalsIgnoreCase(stateName)) {
			return "Bihar";
		}else if("Chandigarh".equalsIgnoreCase(stateName)) {
			return "Chandigarh";
		}else if("Chhattisgarh".equalsIgnoreCase(stateName)) {
			return "Chhattisgarh";
		}else if("Dadra and Nagar Haveli and Daman".equalsIgnoreCase(stateName) || "Daman and Diu".equalsIgnoreCase(stateName)) {
			return "Dadra and Nagar Haveli and Daman and Diu";
		}else if("Delhi".equalsIgnoreCase(stateName)) {
			return "Delhi";
		}else if("Goa".equalsIgnoreCase(stateName)) {
			return "Goa";
		}else if("Gujarat".equalsIgnoreCase(stateName)) {
			return "Gujarat";
		}else if("Haryana".equalsIgnoreCase(stateName)) {
			return "Haryana";
		}else if("Himachal Pradesh".equalsIgnoreCase(stateName)) {
			return "Himachal Pradesh";
		}else if("Jammu and Kashmir".equalsIgnoreCase(stateName)) {
			return "Jammu and Kashmir";
		}else if("Jharkhand".equalsIgnoreCase(stateName)) {
			return "Jharkhand";
		}else if("Karnataka".equalsIgnoreCase(stateName)) {
			return "Karnataka";
		}else if("Kerala".equalsIgnoreCase(stateName)) {
			return "Kerala";
		}else if("Ladakh".equalsIgnoreCase(stateName)) {
			return "Ladakh";
		}else if("Lakshadweep".equalsIgnoreCase(stateName)) {
			return "Lakshadweep";
		}else if("Maharashtra".equalsIgnoreCase(stateName)) {
			return "Maharashtra";
		}else if("Manipur".equalsIgnoreCase(stateName)) {
			return "Manipur";
		}else if("Meghalaya".equalsIgnoreCase(stateName)) {
			return "Meghalaya";
		}else if("Mizoram".equalsIgnoreCase(stateName)) {
			return "Mizoram";
		}else if("Madhya Pradesh".equalsIgnoreCase(stateName)) {
			return "Madhya Pradesh";
		}else if("Nagaland".equalsIgnoreCase(stateName)) {
			return "Nagaland";
		}else if("Odisha".equalsIgnoreCase(stateName)) {
			return "Odisha";
		}else if("Puducherry".equalsIgnoreCase(stateName)) {
			return "Puducherry";
		}else if("Punjab".equalsIgnoreCase(stateName)) {
			return "Punjab";
		}else if("Rajasthan".equalsIgnoreCase(stateName)) {
			return "Rajasthan";
		}else if("Sikkim".equalsIgnoreCase(stateName)) {
			return "Sikkim";
		}else if("Tamil Nadu".equalsIgnoreCase(stateName)) {
			return "Tamil Nadu";
		}else if("Telengana".equalsIgnoreCase(stateName)) {
			return "Telangana";
		}else if("Tripura".equalsIgnoreCase(stateName)) {
			return "Tripura";
		}else if("Uttar Pradesh".equalsIgnoreCase(stateName)) {
			return "Uttar Pradesh";
		}else if("Uttarakhand".equalsIgnoreCase(stateName)) {
			return "Uttarakhand";
		}else if("West Bengal".equalsIgnoreCase(stateName)) {
			return "West Bengal";
		}
		return null;
	}
	
}
