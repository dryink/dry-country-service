package com.dryascent;

import org.json.JSONObject;

public class CountryService {
	String mashapeKey;
	UnirestUtil unirestUtil;

	public CountryService(String mashapeKey) {
		this.mashapeKey = mashapeKey;
		unirestUtil = new UnirestUtil(this.mashapeKey);
	}

	/**
	 * Intelligent lookup based on supplied country name. Input can be full, partial, or code of desired country.
	 *
	 * @param country full, partial, or code of country to lookup
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject lookup(String country) throws Exception {
		if (country == null) {
			throw new Exception("country is required");
		}
		JSONObject countryObj = unirestUtil.getSingleCountryByName(country);
		if (countryObj != null) {
			return countryObj;
		} else {
			return unirestUtil.getCountryByCode(country);
		}
	}
}
