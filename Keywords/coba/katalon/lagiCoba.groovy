package coba.katalon

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

public class hitQueryNexcare {
	def getListQuery() {
	}
	def addQuery() {
		String url = "https://staging-testingqa.nexcloud.id/testingqa/automation/cleanup/nexcare/queries"
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODc3NzExODcsIlVzZXJuYW1lIjoibmV4Y2FyZSIsIkVtYWlsIjoibmV4Y2FyZUBuZXhzb2Z0LmNvLmlkIiwiR3JvdXAiOiJhdXRvbWF0aW9uIn0.MmfUOOY3cYenW7hj10sK30onnt_MLm4lE-KT58DV_PE"

		RequestBody body = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("query", "SELECT * FROM nexcare.user_nexcare")
				.build();

		Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", "Bearer " + token)
				.addHeader("Content-Type", "application/json")
				.post(body)
				.build();

		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body.string();
		def JsonSlurper = new JsonSlurper()
		def jsonObject = JsonSlurper.parseText(responseBodyString)
		def message = jsonObject.message
		println("response message :"+message)
	}
}