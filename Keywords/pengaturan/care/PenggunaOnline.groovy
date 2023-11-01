package pengaturan.care

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import org.junit.internal.runners.statements.FailOnTimeout
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import internal.GlobalVariable
import care.katalon.GeneralAction
import care.katalon.VerifyElement
import groovy.json.StringEscapeUtils

public class PenggunaOnline {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void refreshPage() {
		WebUI.refresh()
	}

	public void openPenggunaOnline() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Pengaturan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Pengaturan/btnPenggunaOnline'))
	}

	public void filterPenggunaOnline() {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Pengguna Online/chkbx_OnlineOnly'), 3)
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Pengguna Online/chkbx_OnlineOnly'))
	}

	public void setLoginUser(HashMap hashMapSetPenggunaOnline) {
		String username = hashMapSetPenggunaOnline.get("Username")
		String password = hashMapSetPenggunaOnline.get("Password")
		KeywordUtil.logInfo("username: " + username)
		KeywordUtil.logInfo("password: " + password)

		GeneralAction.clickElementAndType(findTestObject('Object Repository/Login/username'), username)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Login/password'), password)
		GeneralAction.clickElement(findTestObject('Object Repository/Login/masuk'))

		try {
			boolean popup = WebUI.verifyElementVisible(findTestObject('Object Repository/Login/button_Tidak'))
			KeywordUtil.logInfo("POPUP: " + popup)
			if (popup) {
				GeneralAction.clickElement(findTestObject('Object Repository/Login/button_Tidak'))
			}
		} catch (Exception e) {
			e.printStackTrace()
		}


		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.OPTIONAL)
	}

	public void updateSession(HashMap hashMapSetPenggunaOnline) {
		int i=1;
		boolean match=false;

		while(!match) {
			String actualName = hashMapSetPenggunaOnline.get("Name")
			KeywordUtil.logInfo("Actual Name: " + actualName)
			String targetName = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Pengguna Online/checkName', [('indeks') : (i).toString()]))
			KeywordUtil.logInfo("Target Name: " + targetName)

			if(actualName.equalsIgnoreCase(targetName)) {
				match=true;
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Pengguna Online/btnEndSession', [('indeks') : (i).toString()]))
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Pengguna Online/button_Ya'))
			}

			i++;
		}
	}
	
	public void checkOnlineUser(HashMap hashMapSetPenggunaOnline) {
		int i = 1
		boolean match = false
		
		while(!match) {
			String actualName = hashMapSetPenggunaOnline.get("Name")
			KeywordUtil.logInfo("Actual Name: " + actualName)
			String targetName = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Pengguna Online/checkName', [('indeks') : (i).toString()]))
			KeywordUtil.logInfo("Target Name: " + targetName)
			
			boolean verifyName = actualName.equalsIgnoreCase(targetName)
			KeywordUtil.logInfo("Name match: " + verifyName)
			
			if(verifyName) {
				match = true
				String expectedStatus = "Online"
				KeywordUtil.logInfo("Expected Result: " + expectedStatus)
				String actualStatus = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Pengguna Online/checkOnlineStatus', [('indeks') : (i).toString()]))
				KeywordUtil.logInfo("Actual Result: " + actualName)
				boolean verifyStatus = VerifyElement.verifyMatch(actualStatus, expectedStatus, false)
				KeywordUtil.logInfo("Status match: " + verifyStatus)
			}
			
			i++
		}
	}
}
