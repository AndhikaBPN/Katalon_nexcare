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

public class FilterDashboardOfficer {
	GeneralAction gAction = new GeneralAction()

	def setFilterOfficer(HashMap hashMapDataFilter) {
		String bulan = hashMapDataFilter.get('bulan')
		String tahun = hashMapDataFilter.get('tahun')

		gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/div_Filter'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/button_Reset'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/div_Periode'))

		if(!bulan.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/div_Bulan'))
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/list_Bulan', [('bulan') : bulan]))
		}

		if(!tahun.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/div_Tahun'))
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/list_Tahun', [('tahun') : tahun]))
		}

		gAction.clickElement(findTestObject('Object Repository/Dashboard/Officer/button_Terapkan'))
		WebUI.delay(3)
	}
}
