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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

import internal.GlobalVariable
import org.openqa.selenium.support.ui.Select

import care.katalon.HandleWebDriver
import care.katalon.VerifyElement
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat as SimpleDateFormat

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

class GeneralAction {
	WebDriver driver = DriverFactory.getWebDriver();
	VerifyElement verifyElement = new VerifyElement()

	def clickElement(TestObject testObject) {

		try {
			if(WebUI.waitForElementClickable(testObject, GlobalVariable.timeoutElementInSeccond, FailureHandling.STOP_ON_FAILURE)) {
				KeywordUtil.logInfo("Click Element = " + testObject.getObjectId())
				WebUI.click(testObject, FailureHandling.STOP_ON_FAILURE)
			}
			else {
				KeywordUtil.logInfo("Failed to Click Element in Object Repository : " + testObject.getObjectId())
			}
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def refreshPage() {
		WebUI.refresh()
	}

	def setTextAndClick(TestObject testObject, String sendValue) {
	}

	def clickElementAndSearch(TestObject testObject, String sendValue) {
		clickElement(testObject)
		WebUI.setText(testObject, sendValue, FailureHandling.STOP_ON_FAILURE)
		sendKeysTextToElement(testObject, Keys.chord(Keys.ENTER))
	}

	def clickElementAndType(TestObject testObject, String sendValue) {
		try {
			clickElement(testObject)
			if(!getAttributeFromElement(testObject,"value").equals("")) {
				sendKeysTextToElement(testObject, Keys.chord(Keys.CONTROL, "a"))
				sendKeysTextToElement(testObject, Keys.chord(Keys.BACK_SPACE))
			}

			WebUI.setText(testObject, sendValue, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def clickElementTypeAndSelect(TestObject testObjectSelect, TestObject testObjectSearch, TestObject testObjectResult, String sendValue) {
		try {
			clickElement(testObjectSelect)

			WebUI.sendKeys(testObjectSearch, sendValue)

			waitResultSelect(testObjectResult,  sendValue)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def clickElementSearchAndSelect(TestObject testObject, TestObject testObjectResult, String sendValue) {
		try {
			clickElement(testObject)
			WebUI.sendKeys(testObjectResult, sendValue)
			WebUI.waitForElementPresent(testObjectResult, 5)
			WebUI.sendKeys(testObjectResult, Keys.chord(Keys.ENTER))
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def waitResultSelect(TestObject resultSelect, String expectedText) {
		try {
			WebUI.waitForElementPresent(resultSelect, 10, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
		}

		String getText = WebUI.getText(resultSelect)

		KeywordUtil.logInfo("getText = " + getText)

		for(int i = 0; i < 100; i++) {
			getText = WebUI.getText(resultSelect)
			KeywordUtil.logInfo("getText2 = " + getText)
			if(getText.equals(expectedText)) {
				clickElement(resultSelect)

				break;
			}else if(i == 99) {
				WebUI.verifyMatch(getText, expectedText, false)
			}
		}
	}

	def clickElementSelectCanSearchByVisibleText(TestObject testObjectContainer, TestObject testObjectSearchElement, TestObject testObjectResultElement, String visibleText) {
		try {

			if(visibleText.equals("")) {
				clickElement(testObjectContainer)
				clickElement(testObjectContainer)
			}
			else {
				if(verifyElement.verifyElementClickable(testObjectContainer)) {

					clickElement(testObjectContainer)

					clickElement(testObjectSearchElement)

					sendKeysTextToElement(testObjectSearchElement, visibleText)

					if(verifyElement.verifyElementHaveText(testObjectResultElement, visibleText)) {
						clickElement(testObjectResultElement)
					}

					else {
						clickElement(testObjectContainer)
					}
				}
			}
		}catch(Exception e) {
			KeywordUtil.logInfo(e.printStackTrace())
		}
	}

	def clickElementSelectByVisibleText(TestObject testObjectSelect, String visibleText, boolean isUsingRegex) {
		try {
			if(verifyElement.verifyOptionPresentByLabel(testObjectSelect, visibleText, isUsingRegex)) {
				clickElement(testObjectSelect)
				WebUI.selectOptionByLabel(testObjectSelect, visibleText, isUsingRegex, FailureHandling.STOP_ON_FAILURE)
			}else {
				KeywordUtil.markFailedAndStop("Label Select : " + visibleText + " in Object Repo : " + testObjectSelect.getObjectId() + " is Not Present")
			}
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def sendKeysTextToElement(TestObject testObject, Object valueText) {
		try {
			WebUI.sendKeys(testObject, valueText, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.logInfo(e.printStackTrace())
		}
	}

	def getAttributeFromElement(TestObject testObject, String attributeName) {

		Object getAttribute = ""

		try {
			getAttribute = WebUI.getAttribute(testObject, attributeName, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}

		return getAttribute;
	}

	def clickCheckboxButton(TestObject inputValueTypeCheckBox, String idInputCheckbox, String expectedValue) {

		String valueButton = ""

		if(WebUI.verifyElementNotChecked(inputValueTypeCheckBox, GlobalVariable.timeoutLoadingInSeccond, FailureHandling.OPTIONAL)) {
			valueButton = "NO"
		}
		else {
			valueButton = "YES"
		}

		if(expectedValue.equalsIgnoreCase("Y") || expectedValue.equalsIgnoreCase("YES")) {
			expectedValue = "YES"
		} else if(expectedValue.equalsIgnoreCase("N") || expectedValue.equalsIgnoreCase("NO")){
			expectedValue = "NO"
		}

		KeywordUtil.logInfo("value Sebelumnya : " + valueButton + ", value yang diinginkan : " + expectedValue)

		if(!valueButton.equals(expectedValue)){
			clickElement(findTestObject('Global/Button/btnSwitcher', [("id") : idInputCheckbox]))
		} else {
			KeywordUtil.logInfo("value button adalah " + expectedValue)
		}
	}

	def deleteValue(TestObject clearAll){
		try {
			clickElement(clearAll)
		}
		catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def login(String userLevel) {

		String username;
		String password;

		switch(userLevel) {
			case "Operator" :
				username = "operatornexcare"
				password = "Operator@nexsoft123"

				break;
			case "cslevel1" :
				username = "felixfebriano"
				password = "Felix@nexsoft123"

				break;
			case "cslevel2" :
				username = "cslevel2"
				password = "Cslevel2@nexsoft123"

				break;
			case "csmanager" :
				username = "csmanager"
				password = "Csmanager@nexsoft123"

				break;
			case "cscoordinator" :
				username = "cscoor"
				password = "Cscoor@nexsoft123"

				break;
			case "datavalidator" :
				username = "datavalidator"
				password = "Datavalid@nexsoft1234"

				break;
			case "cslead" :
				username = "cslead"
				password = "CSlead@nexsoft123"

				break;
			case "BUG_VALIDATOR" :
				username = "operatornexcare"
				password = "Operator@nexsoft123"

				break;
			case "principal" :
				username = "principaltestt"
				password = "Principal@nexsoft123"

				break;
			default :
				username = "admin"
				password = "Auth_server_2020"

				break;
		}

		clickElementAndType(findTestObject('Object Repository/Login/username'), username)
		clickElementAndType(findTestObject('Object Repository/Login/password'), password)
		clickElement(findTestObject('Object Repository/Login/masuk'))

		if (WebUI.verifyElementPresent(findTestObject('Object Repository/Login/button_Tidak'), 5)) {
			clickElement(findTestObject('Object Repository/Login/button_Tidak'))
		}


		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)
	}

	def clickElementSelectByValue(TestObject testObjectSelect, String valueSelect, boolean isUsingRegex) {
		try {
			clickElement(testObjectSelect)
			WebUI.selectOptionByValue(testObjectSelect, valueSelect, isUsingRegex, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def clickElementSelectByLabel(TestObject testObjectSelect, String labelSelect, boolean isUsingRegex) {
		try {
			clickElement(testObjectSelect)
			WebUI.selectOptionByLabel(testObjectSelect, labelSelect, isUsingRegex, FailureHandling.STOP_ON_FAILURE)
			clickElement(testObjectSelect)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def getTextFromElement(TestObject testObject) {

		String getText = ""

		try {
			getText =  WebUI.getText(testObject, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}

		return getText;
	}


	def getTextFromElementArray(TestObject testObject) {

		String[] getText

		try {
			getText =  WebUI.getText(testObject, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}

		return getText;
	}

	def navigateToUrl(String urlBrowser) {
		try {
			WebUI.navigateToUrl(urlBrowser)
			WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def logoutAndCloseBrowser() {
		try {
			WebUI.delay(3)
			WebUI.waitForElementClickable(findTestObject('Object Repository/Home Page(General)/Page_NexCARE/btnLogOut'), 5)
			clickElement(findTestObject('Object Repository/Home Page(General)/Page_NexCARE/btnLogOut'))
			clickElement(findTestObject('Object Repository/Home Page(General)/Konfrimasi Keluar'))
			WebUI.delay(3)
			def driver = DriverFactory.getWebDriver()
			driver.quit()
			WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def logout() {
		clickElement(findTestObject('Object Repository/Home Page(General)/Keluar'))
		clickElement(findTestObject('Object Repository/Home Page(General)/Konfrimasi Keluar'))
	}

	def closeBrowser() {
		WebUI.delay(2)
		def driver = DriverFactory.getWebDriver()
		driver.quit()
		WebUI.closeBrowser()
	}
}