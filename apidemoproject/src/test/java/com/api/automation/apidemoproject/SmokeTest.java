package com.api.automation.apidemoproject;

import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class SmokeTest {
	
	private ReadEnvironment element = ReadEnvironment.getInstance();
	public ArrayList<String> tokendata;
	public String accesstoken;
	public String isAccesstoken;
	public String userId;
	public Users req;
	
	
	

	public SmokeTest() {
		req=new Users();
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		
	}

	@Test(testName = "Sample Get call", description = "Sample Get call", timeOut = 190000, enabled = true, groups = {
			"smoke", "1" })
	public void sampleGETCALL() {
		
		ArrayList<String> response=req.listUsers();
		String httpcode=response.get(0);
		Reporter.log("Http code is   "+httpcode,true);
		Assert.assertEquals(httpcode,"200");
		Reporter.log("response payload is   "+response.get(1),true);
		JSONObject jObject = new JSONObject(response.get(1));
		Assert.assertEquals(jObject.getInt("page"),2);
		Assert.assertEquals(jObject.getInt("per_page"),3);
		Assert.assertEquals(jObject.getInt("total"),12);
	}
	
	@Test(testName = "Sample PUT call", description = "Sample PUT call", timeOut = 190000, enabled = true, groups = {
			"smoke", "2" })
	public void samplePUTCALL() {
		String text = RandomStringUtils.randomAlphabetic(10).toUpperCase();
		ArrayList<String> response=req.updateUsers(text);
		String httpcode=response.get(0);
		Reporter.log("Http code is   "+httpcode,true);
		Assert.assertEquals(httpcode,"200");
		Reporter.log("response payload is   "+response.get(1),true);
		JSONObject jObject = new JSONObject(response.get(1));
		Assert.assertEquals(jObject.getString("name"),"first"+text);
		
	}
	
	@Test(testName = "Sample POST call", description = "Sample PUT call", timeOut = 190000, enabled = true, groups = {
			"smoke", "3" })
	public void samplePOSTCALL() {
		String text = RandomStringUtils.randomAlphabetic(10).toUpperCase();
		ArrayList<String> response=req.createUsers(text);
		String httpcode=response.get(0);
		Reporter.log("Http code is   "+httpcode,true);
		Assert.assertEquals(httpcode,"201");
		Reporter.log("response payload is   "+response.get(1),true);
		JSONObject jObject = new JSONObject(response.get(1));
		Assert.assertEquals(jObject.getString("name"),"first"+text);
		
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterTestCases() {
		
	}

	}
