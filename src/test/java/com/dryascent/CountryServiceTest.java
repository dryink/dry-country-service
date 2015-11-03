package com.dryascent;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

	CountryService service;

	@Mock
	UnirestUtil unirestUtil;

	@Before
	public void setup() throws Exception {
		service = new CountryService("fakeMashapeKey");
		service.unirestUtil = unirestUtil;
	}

	@Test
	public void testLookupByFullName() throws Exception {
		when(unirestUtil.getSingleCountryByName(anyString())).thenReturn(singleResult());
		JSONObject country = service.lookup("United States");
		assertNotNull(country);
		assertTrue(country.has("name"));
		assertEquals("United States", country.getString("name"));
	}

	@Test
	public void testLookupByCode() throws Exception {
		when(unirestUtil.getSingleCountryByName(anyString())).thenReturn(null);
		when(unirestUtil.getCountryByCode(anyString())).thenReturn(singleResult());
		JSONObject country = service.lookup("USA");
		assertNotNull(country);
		assertTrue(country.has("name"));
		assertEquals("United States", country.getString("name"));
	}

	@Test
	public void testLookupWithVagueName() throws Exception {
		when(unirestUtil.getSingleCountryByName(anyString())).thenReturn(null);
		when(unirestUtil.getCountryByCode(anyString())).thenReturn(null);
		assertNull(service.lookup("United"));
	}

	private JSONArray multipleResults() {
		return new JSONArray("[{\"name\":\"United States Minor Outlying Islands\",\"capital\":\"\"," +
				"\"altSpellings\":[\"UM\"],\"relevance\":\"0\",\"region\":\"Americas\"," +
				"\"subregion\":\"Northern America\",\"translations\":{\"de\":\"Kleinere Inselbesitzungen der " +
				"Vereinigten Staaten\",\"es\":\"Islas Ultramarinas Menores de Estados Unidos\"," +
				"\"fr\":\"Îles mineures éloignées des États-Unis\",\"ja\":\"合衆国領有小離島\"," +
				"\"it\":\"Isole minori esterne " +
				"degli Stati Uniti d'America\"},\"population\":300,\"latlng\":[],\"demonym\":\"American\"," +
				"\"area\":null,\"gini\":null,\"timezones\":null,\"borders\":[],\"nativeName\":\"United States Minor " +
				"Outlying Islands\",\"callingCodes\":[\"\"],\"topLevelDomain\":[\".us\"],\"alpha2Code\":\"UM\"," +
				"\"alpha3Code\":\"UMI\",\"currencies\":[\"USD\"],\"languages\":[\"en\"]}," +
				"{\"name\":\"United Arab Emirates\",\"capital\":\"Abu Dhabi\",\"altSpellings\":[\"AE\",\"UAE\"]," +
				"\"relevance\":\"0\",\"region\":\"Asia\",\"subregion\":\"Western Asia\"," +
				"\"translations\":{\"de\":\"Vereinigte Arabische Emirate\",\"es\":\"Emiratos Árabes Unidos\"," +
				"\"fr\":\"Émirats arabes unis\",\"ja\":\"アラブ首長国連邦\",\"it\":\"Emirati Arabi Uniti\"}," +
				"\"population\":9157000,\"latlng\":[24.0,54.0],\"demonym\":\"Emirati\",\"area\":83600.0," +
				"\"gini\":null," +
				"\"timezones\":[\"UTC+04\"],\"borders\":[\"OMN\",\"SAU\"],\"nativeName\":\"دولة الإمارات العربية " +
				"المتحدة\",\"callingCodes\":[\"971\"],\"topLevelDomain\":[\".ae\"],\"alpha2Code\":\"AE\"," +
				"\"alpha3Code\":\"ARE\",\"currencies\":[\"AED\"],\"languages\":[\"ar\"]}," +
				"{\"name\":\"United Kingdom\"," +
				"\"capital\":\"London\",\"altSpellings\":[\"GB\",\"UK\",\"Great Britain\"],\"relevance\":\"2.5\"," +
				"\"region\":\"Europe\",\"subregion\":\"Northern Europe\",\"translations\":{\"de\":\"Vereinigtes " +
				"Königreich\",\"es\":\"Reino Unido\",\"fr\":\"Royaume-Uni\",\"ja\":\"イギリス\",\"it\":\"Regno Unito\"}," +
				"\"population\":64800000,\"latlng\":[54.0,-2.0],\"demonym\":\"British\",\"area\":242900.0," +
				"\"gini\":34.0,\"timezones\":[\"UTC−08:00\",\"UTC−05:00\",\"UTC−04:00\",\"UTC−03:00\",\"UTC−02:00\"," +
				"\"UTC\",\"UTC+01:00\",\"UTC+02:00\",\"UTC+06:00\"],\"borders\":[\"IRL\"]," +
				"\"nativeName\":\"United Kingdom\",\"callingCodes\":[\"44\"],\"topLevelDomain\":[\".uk\"]," +
				"\"alpha2Code\":\"GB\",\"alpha3Code\":\"GBR\",\"currencies\":[\"GBP\"],\"languages\":[\"en\"]}," +
				"{\"name\":\"United States\",\"capital\":\"Washington D.C.\",\"altSpellings\":[\"US\",\"USA\"," +
				"\"United States of America\"],\"relevance\":\"3.5\",\"region\":\"Americas\"," +
				"\"subregion\":\"Northern America\",\"translations\":{\"de\":\"Vereinigte Staaten von Amerika\"," +
				"\"es\":\"Estados Unidos\",\"fr\":\"États-Unis\",\"ja\":\"アメリカ合衆国\"," +
				"\"it\":\"Stati Uniti D'America\"}," +
				"\"population\":321645000,\"latlng\":[38.0,-97.0],\"demonym\":\"American\",\"area\":9629091.0," +
				"\"gini\":48.0,\"timezones\":[\"UTC−12:00\",\"UTC−11:00\",\"UTC−10:00\",\"UTC−09:00\",\"UTC−08:00\"," +
				"\"UTC−07:00\",\"UTC−06:00\",\"UTC−05:00\",\"UTC−04:00\",\"UTC+10:00\",\"UTC+12:00\"]," +
				"\"borders\":[\"CAN\",\"MEX\"],\"nativeName\":\"United States\",\"callingCodes\":[\"1\"]," +
				"\"topLevelDomain\":[\".us\"],\"alpha2Code\":\"US\",\"alpha3Code\":\"USA\",\"currencies\":[\"USD\"," +
				"\"USN\",\"USS\"],\"languages\":[\"en\"]},{\"name\":\"Mexico\",\"capital\":\"Mexico City\"," +
				"\"altSpellings\":[\"MX\",\"Mexicanos\",\"United Mexican States\",\"Estados Unidos Mexicanos\"]," +
				"\"relevance\":\"1.5\",\"region\":\"Americas\",\"subregion\":\"Central America\"," +
				"\"translations\":{\"de\":\"Mexiko\",\"es\":\"México\",\"fr\":\"Mexique\",\"ja\":\"メキシコ\"," +
				"\"it\":\"Messico\"},\"population\":121740000,\"latlng\":[23.0,-102.0],\"demonym\":\"Mexican\"," +
				"\"area\":1964375.0,\"gini\":47.0,\"timezones\":[\"UTC−08:00\",\"UTC−07:00\",\"UTC−06:00\"]," +
				"\"borders\":[\"BLZ\",\"GTM\",\"USA\"],\"nativeName\":\"México\",\"callingCodes\":[\"52\"]," +
				"\"topLevelDomain\":[\".mx\"],\"alpha2Code\":\"MX\",\"alpha3Code\":\"MEX\",\"currencies\":[\"MXN\"]," +
				"\"languages\":[\"es\"]},{\"name\":\"Tanzania\",\"capital\":\"Dodoma\",\"altSpellings\":[\"TZ\"," +
				"\"United Republic of Tanzania\",\"Jamhuri ya Muungano wa Tanzania\"],\"relevance\":\"0\"," +
				"\"region\":\"Africa\",\"subregion\":\"Eastern Africa\",\"translations\":{\"de\":\"Tansania\"," +
				"\"es\":\"Tanzania\",\"fr\":\"Tanzanie\",\"ja\":\"タンザニア\",\"it\":\"Tanzania\"}," +
				"\"population\":53470000,\"latlng\":[-6.0,35.0],\"demonym\":\"Tanzanian\",\"area\":945087.0," +
				"\"gini\":37.6,\"timezones\":[\"UTC+03:00\"],\"borders\":[\"BDI\",\"COD\",\"KEN\",\"MWI\",\"MOZ\"," +
				"\"RWA\",\"UGA\",\"ZMB\"],\"nativeName\":\"Tanzania\",\"callingCodes\":[\"255\"]," +
				"\"topLevelDomain\":[\".tz\"],\"alpha2Code\":\"TZ\",\"alpha3Code\":\"TZA\",\"currencies\":[\"TZS\"]," +
				"\"languages\":[\"sw\",\"en\"]}]");
	}

	private JSONObject singleResult() {
		return new JSONObject("{\"name\":\"United States\",\"capital\":\"Washington D.C.\",\"altSpellings\":[\"US\"," +
				"\"USA\",\"United States of America\"],\"relevance\":\"3.5\",\"region\":\"Americas\"," +
				"\"subregion\":\"Northern America\",\"translations\":{\"de\":\"Vereinigte Staaten von Amerika\"," +
				"\"es\":\"Estados Unidos\",\"fr\":\"États-Unis\",\"ja\":\"アメリカ合衆国\"," +
				"\"it\":\"Stati Uniti D'America\"}," +
				"\"population\":321645000,\"latlng\":[38.0,-97.0],\"demonym\":\"American\",\"area\":9629091.0," +
				"\"gini\":48.0,\"timezones\":[\"UTC−12:00\",\"UTC−11:00\",\"UTC−10:00\",\"UTC−09:00\",\"UTC−08:00\"," +
				"\"UTC−07:00\",\"UTC−06:00\",\"UTC−05:00\",\"UTC−04:00\",\"UTC+10:00\",\"UTC+12:00\"]," +
				"\"borders\":[\"CAN\",\"MEX\"],\"nativeName\":\"United States\",\"callingCodes\":[\"1\"]," +
				"\"topLevelDomain\":[\".us\"],\"alpha2Code\":\"US\",\"alpha3Code\":\"USA\",\"currencies\":[\"USD\"," +
				"\"USN\",\"USS\"],\"languages\":[\"en\"]}");
	}
}
