package care.katalon

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
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

import internal.GlobalVariable

public class ErrorHandling {
	
	@Keyword
	def printErrorTest(String failedMessage){
		HashMap hashMapData = new HashMap()
		hashMapData.put("testData", failedMessage)

		KeywordUtil.markFailed("ERROR / FAILED pada : " + failedMessage)
	}
	
	@Keyword
	def printErrorTest(HashMap getHashMapHeader, String keyDocumentNumber, String failedMessage){
		String testDataNumber = ""

		if(getHashMapHeader != null) {
			testDataNumber = getHashMapHeader.get("testData")
		}

		KeywordUtil.markFailed("ERROR / FAILED pada : " + testDataNumber + " Saat : " + GlobalVariable.testCaseID + "\nFailed Message : " + failedMessage)
	}
	

}
