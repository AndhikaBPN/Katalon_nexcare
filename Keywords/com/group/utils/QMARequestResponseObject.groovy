package com.group.utils

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

import groovy.json.JsonSlurper
import internal.GlobalVariable
import groovy.json.JsonOutput
import org.json.JSONObject

public class QMARequestResponseObject {
	/**
	 * Login QMA Request
	 */
	public static ResponseObject loginQMA(String user, String pass) {
		String endpoint = GlobalVariable.baseUrlQMA + "/testingqa/login"
		String token = ""

		/* encode user and pass */
		String auth = "${user}:${pass}".getBytes().encodeBase64().toString()

		/* set up header */
		String authHeaderValue = "Basic " + auth
		TestObjectProperty header1 = new TestObjectProperty("Authorization", ConditionType.EQUALS, authHeaderValue)
		TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		TestObjectProperty header3 = new TestObjectProperty("Accept", ConditionType.EQUALS, "*/*")
		ArrayList defaultHeaders = Arrays.asList(header1, header2, header3)

		/* request to api and get the response */
		ResponseObject respObj = postApiRequest(endpoint, defaultHeaders)

		return respObj
	}

	/**
	 * Convert to JSON
	 */

	public static String convertToJSON(int projectId, String testCaseId, String testDataId, String subject, String tag, String desc, String status, String platform, String platformVersion, String appVersion, String profile, String startExecutionTime, String endExecutionTime, String addProperties, String[][] addValues) {
		/* addValues data type : String[][] */
		return JsonOutput.toJson(["project_id": projectId, "test_case_id": testCaseId, "test_data_id": testDataId, "subject": subject, "tag": tag, "description": desc, "status": status, "platform": platform, "platform_version": platformVersion, "app_version": appVersion, "profile": profile, "start_execution_time": startExecutionTime, "end_execution_time": endExecutionTime, "additional_properties": addProperties, "additional_values": addValues])
	}

	public static String convertToJSON(int projectId, String testCaseId, String testDataId, String subject, String tag, String desc, String status, String platform, String platformVersion, String appVersion, String profile, String startExecutionTime, String endExecutionTime, String addProperties, Object[] addValues) {
		/* addValues data type : Object[] */
		return JsonOutput.toJson(["project_id": projectId, "test_case_id": testCaseId, "test_data_id": testDataId, "subject": subject, "tag": tag, "description": desc, "status": status, "platform": platform, "platform_version": platformVersion, "app_version": appVersion, "profile": profile, "start_execution_time": startExecutionTime, "end_execution_time": endExecutionTime, "additional_properties": addProperties, "additional_values": addValues])
	}

	/**
	 * QMA Check Health
	 */
	public static ResponseObject checkHealthQMA(String token) {
		String endpoint = GlobalVariable.baseUrlQMA + "/testingqa/health"

		/* set up header */
		String authHeaderValue = "Bearer " + token
		TestObjectProperty header1 = new TestObjectProperty("Authorization", ConditionType.EQUALS, authHeaderValue)
		TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		TestObjectProperty header3 = new TestObjectProperty("Accept", ConditionType.EQUALS, "*/*")
		ArrayList defaultHeaders = Arrays.asList(header1, header2, header3)

		/* request to api and get the response */
		ResponseObject respObj = getApiRequest(endpoint, defaultHeaders)

		return respObj
	}

	/**
	 * Automation Log Request
	 */

	public static ResponseObject automationLogRequest(String token, String body, String xRequestID) {
		String endpoint = GlobalVariable.baseUrlQMA + "/testingqa/automation/log"

		/* set up header */
		String authHeaderValue = "Bearer " + token
		TestObjectProperty header1 = new TestObjectProperty("Authorization", ConditionType.EQUALS, authHeaderValue)
		TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		TestObjectProperty header3 = new TestObjectProperty("Accept", ConditionType.EQUALS, "*/*")
		TestObjectProperty header4 = new TestObjectProperty("X-Request-ID", ConditionType.EQUALS, xRequestID)
		ArrayList defaultHeaders = Arrays.asList(header1, header2, header3, header4)

		/* request to api and get the response */
		ResponseObject respObj = postApiRequest(endpoint, defaultHeaders, body)

		return respObj
	}

	/**
	 * POST Request
	 */

	public static ResponseObject postApiRequest(String endpoint, ArrayList<TestObjectProperty> headers, String body) {
		/* post request with body */

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

	public static ResponseObject postApiRequest(String endpoint, ArrayList<TestObjectProperty> headers) {
		/* post request without body */

		/* build request */
		RequestObject ro = new RestRequestObjectBuilder()
				.withRestUrl(endpoint)
				.withHttpHeaders(headers)
				.withRestRequestMethod("POST")
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
