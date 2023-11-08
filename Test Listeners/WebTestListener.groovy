import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

/** mark & log */
import com.kms.katalon.core.util.KeywordUtil

/** additional import */
import java.util.Date
import java.text.SimpleDateFormat

import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonSlurper

import com.kms.katalon.core.testcase.TestCaseFactory

//import com.group.util.QMARequestResponseObject as RRO
import com.group.utils.QMARequestResponseObject as RRO

import com.kms.katalon.core.configuration.RunConfiguration as RC

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.webui.driver.DriverFactory

class WebTestListener {
	boolean isSkipReport = false
	String startTimeTC
	String endTimeTC
	String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'" //date format (ISO 8601)
	String token = ""
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)
	JsonSlurper js = new JsonSlurper()
	String tempTestCaseId = ""
	
	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		if(!GlobalVariable.isSkipTestListener) {
			/** send login request and get the response */
			try{
				ResponseObject resp = RRO.loginQMA(GlobalVariable.usernameQMA, GlobalVariable.passwordQMA)
			
				if(RRO.getStatusCode(resp) != 200 || RRO.getWaitingTime(resp) > 5000) {
					KeywordUtil.logInfo("Status Code: " + RRO.getStatusCode(resp).toString())
					KeywordUtil.logInfo("Waiting Time: " + RRO.getWaitingTime(resp).toString() + " ms")
					
					/** skip report if response status is not 200 or waiting time is more than 5 seconds */
					KeywordUtil.markWarning("Response status is not 200 or waiting time is more than 5 seconds")
					isSkipReport = true
				}else {
					/** get response text */
					String resText = RRO.getResponseText(resp)
					
					/** convert response text to object */
					def object = js.parseText(resText)
					
					/** get token value from object */
					token = object.token
				}
			}catch (Exception e) {
				KeywordUtil.logInfo(e.printStackTrace().toString())
				KeywordUtil.markWarning(e.message)
				if(token.equals("") || token.equals(null)) {
					isSkipReport = true					
				}
			}
		}
	}
	
	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		if(!GlobalVariable.isSkipTestListener) {
			if(!isSkipReport) {
				/** set up start time of test case */
				startTimeTC = simpleDateFormat.format(new Date())
				
				/** set up current test case id */
				GlobalVariable.currentTestCaseId = testCaseContext.getTestCaseId()
				
				/** set up test case x-request-id header */
				if(!tempTestCaseId.equals(GlobalVariable.currentTestCaseId)) {
					tempTestCaseId = GlobalVariable.currentTestCaseId
					
					UUID uuid = UUID.randomUUID()
					GlobalVariable.xRequestID = uuid.toString()
				}
			}
		}
	}
	
	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		if(!GlobalVariable.isSkipTestListener) {
			if(!isSkipReport) {
				try {
					ResponseObject respQMAHealth = RRO.checkHealthQMA(token)
					
					if(RRO.getStatusCode(respQMAHealth) != 200 || RRO.getWaitingTime(respQMAHealth) > 5000) {
						KeywordUtil.logInfo("Status Code: " + RRO.getStatusCode(respQMAHealth).toString())
						KeywordUtil.logInfo("Waiting Time: " + RRO.getWaitingTime(respQMAHealth).toString() + " ms")
						
						/** skip report if response status is not 200 or waiting time is more than 5 seconds */
						KeywordUtil.markWarning("Response status is not 200 or waiting time is more than 5 seconds")
					}else {
						WebDriver driver = DriverFactory.getWebDriver()
						
						/** set up end time of test case */
						endTimeTC = simpleDateFormat.format(new Date())
		
						/** set up body of automation log request */
						int projectId = GlobalVariable.projectID
						String testCaseId = GlobalVariable.currentTestCaseId
		
						TestCase tc = TestCaseFactory.findTestCase(testCaseId)
		
						String subject = tc.name
						String tag = tc.getTag()
						String desc = tc.getDescription()
						String status = testCaseContext.getTestCaseStatus()
						
						Capabilities caps = ((RemoteWebDriver) driver).getCapabilities()
						String platform = caps.getBrowserName().capitalize()
						String platformVersion = caps.getVersion()
		
						String appVersion = GlobalVariable.appVersion
						String profile = RC.getExecutionProfile()
						
						String startExecutionTime = startTimeTC
						String endExecutionTime = endTimeTC
						String addProperties = testCaseContext.getMessage()
						
						Map<String, Object> testData =  testCaseContext.getTestCaseVariables()
						Object[] addValues = [testData] //change here (optional)
						
						String testDataId = ""
						if(testData != null) {
							String tdid = testData.get("testDataId") //change here (optional)
							if(!tdid.equals(null)) { 				
								testDataId = tdid												 
							}							
						}
		
						String body = RRO.convertToJSON(projectId, testCaseId, testDataId, subject, tag, desc, status, platform, platformVersion, appVersion, profile, startExecutionTime, endExecutionTime, addProperties, addValues)
						
						KeywordUtil.logInfo("xRequestID: " + GlobalVariable.xRequestID)
						KeywordUtil.logInfo("Body: " + body)
		
						/** send automation log request and get the response */
						ResponseObject resp = RRO.automationLogRequest(token, body, GlobalVariable.xRequestID)
		
						if(RRO.getStatusCode(resp) != 201 || RRO.getWaitingTime(resp) > 5000) {
							KeywordUtil.logInfo("Status Code: " + RRO.getStatusCode(resp).toString())
							KeywordUtil.logInfo("Waiting Time: " + RRO.getWaitingTime(resp).toString() + " ms")
							
							/** skip report if response status is not 201 or waiting time is more than 5 seconds */
							KeywordUtil.markWarning("Response status is not 201 or waiting time is more than 5 seconds")
						}else {
							/** get response text */
							String resText = RRO.getResponseText(resp)
							KeywordUtil.logInfo("Text: " + resText)
		
							/** convert response text to object */
							def object = js.parseText(resText)
		
							/** get token value from object */
							String code = object.code
							String message = object.message
							KeywordUtil.logInfo("code: " + code)
							KeywordUtil.logInfo("message: " + message)
						}
					}
				}catch(Exception e) {
					KeywordUtil.logInfo(e.printStackTrace().toString())
					KeywordUtil.markWarning(e.message)
				}
			}
		}
	}

	@AfterTestSuite
	def afterTestSuite(TestSuiteContext testSuiteContext) {
		if(!GlobalVariable.isSkipTestListener) {
			// Put your code here.
		}
	}
}