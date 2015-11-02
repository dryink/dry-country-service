Introduction
-------------
This Java library provides a wrapper around a Mashape country REST service.  Given a full country name, partial country name, or even country code - the ```lookup``` method will intelligently attempt to find details about the given country.  If no suitable result is found, a ```null``` object is returned.

Requirements
---------------------------
**A Mashape access token is required when instantiating the Java class ```CountryService```. Sign up for free at https://market.mashape.com/.**

Code Example
---------------------------
```
CountryService service = new CountryService("mashape_access_token");
JSONObject country = service.lookup("United States");
```