package dashboard.care

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

import care.katalon.GeneralAction
import internal.GlobalVariable

public class OpenDashboard {
	GeneralAction gAction =  new GeneralAction()

	def open(String role) {
		switch(role) {
			case "principal":
				WebUI.delay(3)
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Principal/div_Dashboard_bg-blue-100 sidebar-parent-icon'))
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Principal/a_Principal'))
				break;
			case "manager":
				WebUI.delay(3)
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/div_Dashboard_bg-blue-100 sidebar-parent-icon'))
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/a_Manager'))
				break;
			case "lead":
				WebUI.delay(3)
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Dashboard_bg-blue-100 sidebar-parent-icon'))
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/a_Lead'))
				break;
			case "officer":
				WebUI.delay(3)
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/div_Dashboard_bg-blue-100 sidebar-parent-icon'))
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/a_Officer'))
				break;
		}
	}
}
