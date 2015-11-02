package com.dryascent;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONObject;

/**
 * Unit test for simple App.
 */
public class CountryServiceTest
		extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public CountryServiceTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CountryServiceTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() throws Exception {
		CountryService translation =
				new CountryService("5vHTrUyv32mshfNa6yEeWHiAj3txp1Dri8njsnhdtF6VvvfMvl");
		JSONObject country = translation.lookup("USA");
		assertNotNull(country);
		assertTrue(country.has("name"));
		assertEquals("United States", country.getString("name"));

		country = translation.lookup("United States");
		assertNotNull(country);
		assertTrue(country.has("name"));
		assertEquals("United States", country.getString("name"));

		country = translation.lookup("United");
		assertNull(country);
	}
}
