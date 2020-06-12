package com.api.automation.apidemoproject;

import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.testng.Assert;
import org.testng.Reporter;

public class Users {

	private ReadEnvironment element = ReadEnvironment.getInstance();
	HttpClient client = HttpClientBuilder.create().build();
	
	public ST getPayLoadTemplate1(String payLoadFileName) {
		String s = System.getProperty("user.dir") + "\\src\\test\\resources\\payloads";
		System.out.println("path is  " + s);
		STGroup group = new STGroupDir(s, '$', '$');
		ST wsTemplate = group.getInstanceOf(payLoadFileName);
		System.out.println("wsTemplate.toString();" + wsTemplate.toString());
		return wsTemplate;

	}

	public ArrayList<String> updateUsers(String randomName) {
		
		ST payloadTemplate = getPayLoadTemplate1("UpdateUser");
		
		payloadTemplate.add("name", "first" + randomName);
		
		Reporter.log("  payloadTemplate.getAttribute;" + payloadTemplate.getAttribute("name").toString(), true);
		Reporter.log("payload" + payloadTemplate.render(), true);
		
		
		ArrayList<String> array = new ArrayList<String>();
		try {
			HttpPut request = new HttpPut(element.getElement("baseurl") + element.getElement("updateUser"));
			request.setHeader("Content-Type", element.getElement("Content-Type"));			
			request.setHeader("Accept",
					"application/json, application/xml, text/json, text/x-json, text/javascript, text/xml");

			HttpResponse response = null;
			request.setEntity(new StringEntity(payloadTemplate.render(), "utf-8"));
			response = client.execute(request);

			Reporter.log("Response Code : " + response.getStatusLine().getStatusCode(), true);
			array.add(Integer.toString(response.getStatusLine().getStatusCode()));
			String getresponse = null;

			getresponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			Reporter.log("recent services " + getresponse, true);
			array.add(getresponse);
			request.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public ArrayList<String> listUsers() {

		ArrayList<String> array = new ArrayList<String>();
		try {
			HttpGet request = new HttpGet(element.getElement("baseurl") + element.getElement("listUsers"));

			request.setHeader("Content-Type", element.getElement("Content-Type"));
			// request.setHeader("Authorization", "Bearer" + " " + token);

			request.setHeader("Accept",
					"application/json, application/xml, text/json, text/x-json, text/javascript, text/xml");

			HttpResponse response = null;

			response = client.execute(request);

			Reporter.log("Response Code : " + response.getStatusLine().getStatusCode(), true);
			array.add(Integer.toString(response.getStatusLine().getStatusCode()));
			String getresponse = null;

			getresponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			Reporter.log("recent services " + getresponse, true);
			array.add(getresponse);
			request.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
public ArrayList<String> createUsers(String randomName) {
		
		ST payloadTemplate = getPayLoadTemplate1("UpdateUser");
		
		payloadTemplate.add("name", "first" + randomName);
		
		Reporter.log("  payloadTemplate.getAttribute;" + payloadTemplate.getAttribute("name").toString(), true);
		Reporter.log("payload" + payloadTemplate.render(), true);
		
		
		ArrayList<String> array = new ArrayList<String>();
		try {
			HttpPost request = new HttpPost(element.getElement("baseurl") + element.getElement("listUsers"));
			request.setHeader("Content-Type", element.getElement("Content-Type"));			
			request.setHeader("Accept",
					"application/json, application/xml, text/json, text/x-json, text/javascript, text/xml");

			HttpResponse response = null;
			request.setEntity(new StringEntity(payloadTemplate.render(), "utf-8"));
			response = client.execute(request);

			Reporter.log("Response Code : " + response.getStatusLine().getStatusCode(), true);
			array.add(Integer.toString(response.getStatusLine().getStatusCode()));
			String getresponse = null;

			getresponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			Reporter.log("recent services " + getresponse, true);
			array.add(getresponse);
			request.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

}
