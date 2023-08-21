package com.group.utils

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.json.JSONObject

public class QMARequestResponseObject {
	/**
	 * Login QMA Request
	 */
	public static ResponseObject loginQMA(String user, String pass) {
		String baseUrl = "https://testingqa-office.nexcloud.id"
		String endpoint = baseUrl + "/testingqa/login"
		String token = ""

		/* encode user and pass */
		String auth = "${user}:${pass}".getBytes().encodeBase64().toString()

		/* set up header */
		String authHeaderValue = "Basic " + auth
		TestObjectProperty header1 = new TestObjectProperty("Authorization", ConditionType.EQUALS, authHeaderValue)
		TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		TestObjectProperty header3 = new TestObjectProperty("Accept", ConditionType.EQUALS, "*/*")
		ArrayList defaultHeaders = Arrays.asList(header1, header2, header3)

		/* build request */
		RequestObject ro = new RestRequestObjectBuilder()
				.withRestUrl(endpoint)
				.withHttpHeaders(defaultHeaders)
				.withRestRequestMethod("POST")
				.build()

		/* request to api and get the response */
		ResponseObject respObj = WS.sendRequest(ro)

		return respObj
	}

	/**
	 * Convert to JSON
	 */

	public static String convertToJSON(int projectId, String testCase, String subject, String tag, String desc, String status, String platform, String platformVersion, String appVersion, String profile, String startExecutionTime, String endExecutionTime, String addProperties, String[][] addValues) {
		return JsonOutput.toJson(["project_id": projectId, "test_case": testCase, "subject": subject, "tag": tag, "description": desc, "status": status, "platform": platform, "platform_version": platformVersion, "app_version": appVersion, "profile": profile, "start_execution_time": startExecutionTime, "end_execution_time": endExecutionTime, "additional_properties": addProperties, "additional_values": addValues])
	}

	/**
	 * Automation Log Request
	 */

	public static ResponseObject automationLogRequest(String token, String body) {
		String baseUrl = "https://testingqa-office.nexcloud.id"
		String endpoint = baseUrl + "/testingqa/automation/log"

		/* set up header */
		String authHeaderValue = "Bearer " + token
		TestObjectProperty header1 = new TestObjectProperty("Authorization", ConditionType.EQUALS, authHeaderValue)
		TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		TestObjectProperty header3 = new TestObjectProperty("Accept", ConditionType.EQUALS, "*/*")
		ArrayList defaultHeaders = Arrays.asList(header1, header2, header3)

		/* build request */
		RequestObject ro = new RestRequestObjectBuilder()
				.withRestUrl(endpoint)
				.withHttpHeaders(defaultHeaders)
				.withRestRequestMethod("POST")
				.withTextBodyContent(body)
				.build()

		/* request to api and get the response */
		ResponseObject respObj = WS.sendRequest(ro)

		return respObj
	}

	/**
	 * POST Request
	 */

	public static ResponseObject postApiRequest(String endpoint, ArrayList<TestObjectProperty> headers, String body) {

		/* build request */
		RequestObject ro = new RestRequestObjectBuilder()
				.withRestUrl(endpoint)
				.withHttpHeaders(headers)
				.withRestRequestMethod("POST")
				.withTextBodyContent(body)
				.build()

		/* request to api and get the response */
		ResponseObject respObj = WS.sendRequest(ro)

		return respObj
	}

	/**
	 * GET Request
	 */

	public static ResponseObject getApiRequest(String endpoint, ArrayList<TestObjectProperty> headers) {

		/* build request */
		RequestObject ro = new RestRequestObjectBuilder()
				.withRestUrl(endpoint)
				.withHttpHeaders(headers)
				.withRestRequestMethod("GET")
				.build()

		/* request to api and get the response */
		ResponseObject respObj = WS.sendRequest(ro)

		return respObj
	}

	/**
	 * Get Response Status Code
	 */

	public static int getStatusCode(ResponseObject resp) {
		return resp.getStatusCode()
	}

	/**
	 * Get Response Text
	 */

	public static String getResponseText(ResponseObject resp) {
		return resp.getResponseText()
	}

	/**
	 * Get Response Body Size
	 */

	public static long getResponseBodySize(ResponseObject resp) {
		return resp.getResponseBodySize()
	}

	/**
	 * Get Response Header Size
	 */

	public static long getResponseHeaderSize(ResponseObject resp) {
		return resp.getResponseHeaderSize()
	}

	/**
	 * Get Response Waiting Time
	 */

	public static long getWaitingTime(ResponseObject resp) {
		return resp.getWaitingTime()
	}
}
