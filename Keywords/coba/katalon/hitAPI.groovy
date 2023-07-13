package coba.katalon

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testdata.TestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.net.Authenticator.RequestorType

import org.json.JSONObject
import org.xbill.DNS.Rcode

import com.github.kklisura.cdt.protocol.types.webauthn.Credential
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testobject.ResponseObject

import groovy.json.JsonSlurper
import internal.GlobalVariable
import jdk.nashorn.api.scripting.JSObject
import okhttp3.Credentials
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.MediaType

public class hitAPI {
	def loginQA() {
		String url = "https://staging-testingqa.nexcloud.id/testingqa/login"
		String username = "nexcare"
		String password = "nexcare@2023"
		String credential = Credentials.basic(username, password)

		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.header("Authorization", credential)
				.post(RequestBody.create(null, new byte[0]))
				.build();

		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body.string();
		String rCode = response.code()
		println("response code: " + rCode)
		def JsonSlurper = new JsonSlurper()
		def jsonObject = JsonSlurper.parseText(responseBodyString)
		def token = jsonObject.token
		println("token: " + token);
		GlobalVariable.tokenQA = token;
	}

	def addQuery() {
		String url = "https://staging-testingqa.nexcloud.id/testingqa/automation/cleanup/nexcare/queries"
		String token = GlobalVariable.tokenQA
		String query = "delete from nexcare.kms where id = 91"

		Request req = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "Bearer " + token)
				.post(RequestBody.create(MediaType.parse("application/json"), "{\"query\": \"${query}\"}"))
				.build();

		OkHttpClient client = new OkHttpClient();
		Response res = client.newCall(req).execute()
		println("response code: " + res.code() + " -> Add Query")
		String resBody = res.body().string()
		println("response body: " + resBody)
		JSONObject json = new JSONObject(resBody);
	}

	def getQuery() {
		String url = "https://staging-testingqa.nexcloud.id/testingqa/automation/cleanup/nexcare/listqueries"
		String token = GlobalVariable.tokenQA

		Request request = new Request.Builder()
				.url(url)
				.addHeader("Authorization", "Bearer " + token)
				.get()
				.build();

		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		String responseBodyString = response.body().string();
		String rCode = response.code()
		println("response code: " + rCode + " -> Get List Query")
		String[] lines = responseBodyString.split("\n")
		String modifiedResponseBody = lines[3..-1].join("\n")
		String[] resQuery = modifiedResponseBody.split("\n")
		println("list Query: \n" + modifiedResponseBody)
		for(String part : resQuery) {
			println("get query:" + part)
			GlobalVariable.query = part
			deleteQuery();
		}
	}

	def deleteQuery() {
		String url = "https://staging-testingqa.nexcloud.id/testingqa/automation/cleanup/nexcare/delete"
		String token = GlobalVariable.tokenQA
		String query = GlobalVariable.query
		println("get query:"+query)


		Request req = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "Bearer " + token)
				.delete(RequestBody.create(MediaType.parse("application/json"), "{\"query\": \"${query}\"}"))
				.build();

		OkHttpClient client = new OkHttpClient();
		Response res = client.newCall(req).execute()
		println("response code: " + res.code() + " -> Delete Query")
		String resBody = res.body().string()
		println("response body: " + resBody)
		JSONObject json = new JSONObject(resBody);
		def code = json.code
		String code1 = code.toString()
		if(code1 == "404") {
			println("Query sudah dihapus")
		}
	}

	def cleanUpApi() {
		String url = "https://staging-testingqa.nexcloud.id/testingqa/automation/cleanup/nexcare"
		String token = GlobalVariable.tokenQA
		String pToken = "):-](Z&TE}\"9ZX&^#hk*N)h-_V6t&h"

		Request req = new Request.Builder()
				.url(url)
				.addHeader("Authorization", "Bearer " + token)
				.addHeader("PrivilegedToken", pToken)
				.delete()
				.build()

		OkHttpClient client = new OkHttpClient()
		Response res = client.newCall(req).execute()
		println("response code: " + res.code() + " -> CleanUp Query")
	}
}
