package com.api.automation.apidemoproject;

import static org.testng.Assert.fail;

import java.util.Properties;

import org.testng.Reporter;

public class ReadEnvironment {

	private static ReadEnvironment _instance;
	private static String mylocale = System.getProperty("EnvType");

	private static Properties baseElements = new Properties();

	public static ReadEnvironment getInstance() {
		Reporter.log("environment type  "+mylocale,true);
		if (_instance == null) {

			_instance = new ReadEnvironment();
		}
		return _instance;
	}

	private ReadEnvironment() {
		loadPropertyElements();
	}

	public String getElement(String key) {

		if ((baseElements != null) && (baseElements.containsKey(key))) {
			Reporter.log("property value for "+key +":" + baseElements.getProperty(key), true);
			return baseElements.getProperty(key);
		}

		fail("Could not find property element for key '" + key + "'");
		return null;
	}

	private void loadPropertyElements() {
		try {
			if (mylocale == null) {
				Reporter.log("in dev environment");
				baseElements.load(getClass().getResourceAsStream("/environments/staging.properties"));
			} else if (mylocale.equalsIgnoreCase("dev")) {
				Reporter.log("in dev environment");
				baseElements.load(getClass().getResourceAsStream("/environments/dev.properties"));
			} else if (mylocale.equalsIgnoreCase("staging")) {
				Reporter.log("in staging environment");
				baseElements.load(getClass().getResourceAsStream("/environments/staging.properties"));
			} else if (mylocale.equalsIgnoreCase("prod")) {
				Reporter.log("in prod environment");
				baseElements.load(getClass().getResourceAsStream("/environments/prod.properties"));
			}
		} catch (Exception e) {
			Reporter.log("Could not load base element file.", 3);
		}

	}
}