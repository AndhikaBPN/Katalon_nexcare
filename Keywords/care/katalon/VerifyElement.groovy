package care.katalon

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

import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

public class VerifyElement {

	boolean result;


	def verifyAlertPresent() {

		if(WebUI.verifyAlertPresent(GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyAlertNotPresent() {

		if(WebUI.verifyAlertNotPresent(GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementChecked(TestObject testObject) {

		if(WebUI.verifyElementChecked(testObject, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementNotChecked(TestObject testObject) {

		if(WebUI.verifyElementNotChecked(testObject, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementClickable(TestObject testObject) {

		if(WebUI.verifyElementClickable(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementNotClickable(TestObject testObject) {

		if(WebUI.verifyElementNotClickable(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementInViewport(TestObject testObject) {

		if(WebUI.verifyElementInViewport(testObject, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementNotInViewport(TestObject testObject) {

		if(WebUI.verifyElementNotInViewport(testObject, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementPresent(TestObject testObject) {

		if(WebUI.verifyElementPresent(testObject, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementNotPresent(TestObject testObject) {

		if(WebUI.verifyElementNotPresent(testObject, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementVisible(TestObject testObject) {

		if(WebUI.verifyElementVisible(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementNotVisible(TestObject testObject) {

		if(WebUI.verifyElementNotVisible(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyElementHaveText(TestObject testObject, String expectedText) {

		if(WebUI.verifyElementText(testObject, expectedText, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyEqual(Object object1, Object object2) {

		if(WebUI.verifyEqual(object1, object2, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("NOT EQUALS")
		}

		return result;
	}

	def verifyNotEqual(Object object1, Object object2) {

		if(WebUI.verifyNotEqual(object1, object2, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("EQUALS")
		}

		return result;
	}

	def verifyGreaterThan(Object object1, Object object2) {

		if(WebUI.verifyGreaterThan(object1, object2, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("NOT GREATER THAN")
		}

		return result;
	}

	def verifyLessThan(Object object1, Object object2) {

		if(WebUI.verifyLessThan(object1, object2, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("NOT LESS THAN")
		}

		return result;
	}

	def verifyGreaterThanOrEqual(Object object1, Object object2) {

		if(WebUI.verifyGreaterThanOrEqual(object1, object2, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("NOT GREATER THAN OR EQUAL")
		}

		return result;
	}

	def verifyLessThanOrEqual(Object object1, Object object2) {

		if(WebUI.verifyLessThanOrEqual(object1, object2, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("NOT LESS THAN OR EQUAL")
		}

		return result;
	}

	def verifyImagePresent(TestObject testObject) {

		if(WebUI.verifyImagePresent(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyMatch(Object actual, Object expected, boolean isUsingRegex) {

		if(WebUI.verifyMatch(actual.toString().trim(), expected.toString().trim(), isUsingRegex, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("NOT MATCH")
		}

		return result;
	}

	def verifyNotMatch(Object actual, Object expected, boolean isUsingRegex) {

		if(WebUI.verifyNotMatch(actual.toString().trim(), expected.toString().trim(), isUsingRegex, FailureHandling.OPTIONAL)) {
			result = true
		}
		else {
			result = false;
			KeywordUtil.markFailed("MATCH")
		}

		return result;
	}

	def verifyTextPresent(String actualtext, boolean isUsingRegex) {

		if(WebUI.verifyTextPresent(actualtext, isUsingRegex, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyTextNotPresent(String actualtext, boolean isUsingRegex) {

		if(WebUI.verifyTextNotPresent(actualtext, isUsingRegex, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionPresentByLabel(TestObject testObject, String visibleText, boolean isUsingRegex) {

		if(WebUI.verifyOptionPresentByLabel(testObject, visibleText, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionNotPresentByLabel(TestObject testObject, String visibleText, boolean isUsingRegex) {

		if(WebUI.verifyOptionNotPresentByLabel(testObject, visibleText, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionPresentByValue(TestObject testObject, String expectedValue, boolean isUsingRegex) {

		if(WebUI.verifyOptionPresentByValue(testObject, expectedValue, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionNotPresentByValue(TestObject testObject, String expectedValue, boolean isUsingRegex) {

		if(WebUI.verifyOptionNotPresentByValue(testObject, expectedValue, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionSelectedByLabel(TestObject testObject, String expectedValue, boolean isUsingRegex) {

		if(WebUI.verifyOptionSelectedByLabel(testObject, expectedValue, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionNotSelectedByLabel(TestObject testObject, String expectedValue, boolean isUsingRegex) {

		if(WebUI.verifyOptionNotSelectedByLabel(testObject, expectedValue, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionSelectedByValue(TestObject testObject, String expectedValue, boolean isUsingRegex) {

		if(WebUI.verifyOptionSelectedByValue(testObject, expectedValue, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}

		return result;
	}

	def verifyOptionNotSelectedByValue(TestObject testObject, String expectedValue, boolean isUsingRegex) {

		if(WebUI.verifyOptionNotSelectedByValue(testObject, expectedValue, isUsingRegex, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false;
		}


		return result;
	}

	def verifyCheckboxButton(TestObject testobjectValueButton, String expectedValue) {
		String valueButton = WebUI.getAttribute(testobjectValueButton, 'value')

		if(valueButton.equals("on")) {
			valueButton = "YES"
		} else if(valueButton.equals("off")){
			valueButton = "NO"
		} else {
			valueButton = "INVALID ACTUAL VALUE BUTTON SWITCHER"
			KeywordUtil.logInfo("value Button " + valueButton)
		}

		if(expectedValue.equalsIgnoreCase("YES") || expectedValue.equalsIgnoreCase("Y")) {
			expectedValue = "YES"
		}else if(expectedValue.equalsIgnoreCase("NO") || expectedValue.equalsIgnoreCase("N")) {
			expectedValue = "NO"
		}
		else {
			expectedValue= "INVALID TEST DATA VALUE"
		}

		verifyMatch(valueButton, expectedValue, false)
	}

	def verifyRadioButton(TestObject testobjectValueButton, String expectedValue) {
		String valueButton = WebUI.getAttribute(testobjectValueButton, 'value')

		if(expectedValue.equalsIgnoreCase("YES") || expectedValue.equalsIgnoreCase("Y")) {
			expectedValue = "Y"
		}else if(expectedValue.equalsIgnoreCase("NO") || expectedValue.equalsIgnoreCase("N")) {
			expectedValue = "N"
		}else {
			expectedValue= "INVALID TEST DATA VALUE"
		}

		verifyMatch(valueButton, expectedValue, false)
	}

	def verifyAmount(String getAmountDebited, String amountDebited) {
		KeywordUtil.logInfo(getAmountDebited + ', ' + amountDebited)
		List listActualAmountDebited = getAmountDebited.split('\\.')
		String actualAmountDebited = listActualAmountDebited.get(0)
		verifyMatch(actualAmountDebited.replace(',', ''), amountDebited, false)
	}

	def changeValueToYESOrNO(String text) {

		if(text.equalsIgnoreCase("Y") || text.equalsIgnoreCase("YES")) {
			text = "YES"
		}
		else if(text.equalsIgnoreCase("N") || text.equalsIgnoreCase("NO")) {
			text = "NO"
		}

		return text;
	}

	def verifyElementVisibleByParameter(String keyName, String getExpectedParameterValue, TestObject testObjectElement, boolean isNegativeWord) {

		boolean result = false;

		getExpectedParameterValue = changeValueToYESOrNO(getExpectedParameterValue)

		if(getExpectedParameterValue != null && !getExpectedParameterValue.equals("")) {

			if(isNegativeWord) {
				if(getExpectedParameterValue.equals("YES")) {
					result = verifyElementNotVisible(testObjectElement)
				}else {
					result = verifyElementVisible(testObjectElement)
				}
			}else {
				if(getExpectedParameterValue.equals("YES")) {
					result = verifyElementVisible(testObjectElement)
				}else {
					result = verifyElementNotVisible(testObjectElement)
				}
			}
		}else {
			result = true;

			KeywordUtil.logInfo("Parameter Value : "+ keyName +"is NULL")
		}

		return result;
	}

	def verifyElementNotVisibleByParameter(String keyName, String getExpectedParameterValue, TestObject testObjectElement, boolean isNegativeWord) {

		boolean result = false;

		getExpectedParameterValue = changeValueToYESOrNO(getExpectedParameterValue)

		if(getExpectedParameterValue != null && !getExpectedParameterValue.equals("")) {

			if(isNegativeWord) {
				if(getExpectedParameterValue.equals("NO")) {
					result = verifyElementVisible(testObjectElement)
				}else {
					result = verifyElementNotVisible(testObjectElement)
				}
			}else {
				if(getExpectedParameterValue.equals("NO")) {
					result = verifyElementNotVisible(testObjectElement)
				}else {
					result = verifyElementVisible(testObjectElement)
				}
			}
		}else {
			result = true;

			KeywordUtil.logInfo("Parameter Value : "+ keyName +"is NULL")
		}

		return result;
	}

	def verifyElementClickableByParameter(String keyName, String getExpectedParameterValue, TestObject testObjectElement, boolean isNegativeWord) {

		boolean result = false;

		getExpectedParameterValue = changeValueToYESOrNO(getExpectedParameterValue)

		if(getExpectedParameterValue != null && !getExpectedParameterValue.equals("")) {

			if(isNegativeWord) {
				if(getExpectedParameterValue.equals("YES")) {
					result = verifyElementNotClickable(testObjectElement)
				}else {
					result = verifyElementClickable(testObjectElement)
				}
			}else {
				if(getExpectedParameterValue.equals("YES")) {
					result = verifyElementClickable(testObjectElement)
				}else {
					result = verifyElementNotClickable(testObjectElement)
				}
			}
		}else {
			result = true;

			KeywordUtil.logInfo("Parameter Value : "+ keyName +"is NULL")
		}

		return result;
	}

	def verifyElementNotClickableByParameter(String keyName, String getExpectedParameterValue, TestObject testObjectElement, boolean isNegativeWord) {

		boolean result = false;

		getExpectedParameterValue = changeValueToYESOrNO(getExpectedParameterValue)

		if(getExpectedParameterValue != null && !getExpectedParameterValue.equals("")) {

			if(isNegativeWord) {
				if(getExpectedParameterValue.equals("NO")) {
					result = verifyElementClickable(testObjectElement)
				}else {
					result = verifyElementNotClickable(testObjectElement)
				}
			}else {
				if(getExpectedParameterValue.equals("NO")) {
					result = verifyElementNotClickable(testObjectElement)
				}else {
					result = verifyElementClickable(testObjectElement)
				}
			}
		}else {
			result = true;

			KeywordUtil.logInfo("Parameter Value : "+ keyName +"is NULL")
		}

		return result;
	}
}