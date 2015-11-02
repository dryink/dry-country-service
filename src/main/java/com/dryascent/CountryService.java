package com.dryascent;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

public class CountryService {
	String mashapeKey;

	public CountryService(String mashapeKey) {
		this.mashapeKey = mashapeKey;
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
		JSONObject countryObj = getSingleCountryByName(country);
		if (countryObj != null) {
			return countryObj;
		} else {
			return getCountryByCode(country);
		}
	}

	protected JSONObject getSingleCountryByName(String country) throws Exception {
		if (country == null) return null;
		String firstPartOfCountry = (country.split(" "))[0];
		HttpResponse<JsonNode> response = Unirest.get(
				"https://restcountries-v1.p.mashape.com/name/" + firstPartOfCountry)
				.header("X-Mashape-Key", mashapeKey)
				.header("Accept", "application/json")
				.asJson();
		if (response != null && response.getStatus() == 200 && response.getBody().getArray() != null) {
			if (response.getBody().getArray().length() == 1) {
				return response.getBody().getArray().getJSONObject(0);
			} else {
				for (int i = 0; i < response.getBody().getArray().length(); i++) {
					JSONObject jsonObject = response.getBody().getArray().getJSONObject(i);
					if (jsonObject.has("name") && country.equals(jsonObject.getString("name"))) {
						return jsonObject;
					}
				}
			}
		}
		return null;
	}

	protected JSONObject getCountryByCode(String country) throws Exception {
		String firstPartOfCountry = (country.split(" "))[0];
		HttpResponse<JsonNode> response = Unirest.get(
				"https://restcountries-v1.p.mashape.com/alpha/" + firstPartOfCountry)
				.header("X-Mashape-Key", mashapeKey)
				.header("Accept", "application/json")
				.asJson();
		if (response != null && response.getStatus() == 200 && response.getBody().getObject() != null) {
			return response.getBody().getObject();
		}
		return null;
	}
}
