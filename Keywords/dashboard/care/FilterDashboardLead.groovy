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

public class FilterDashboardLead {
	GeneralAction gAction = new GeneralAction()
	
	public void menuDashboardPrincipal() {
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Filter'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/button_Reset'))
	}
	
	def setFilterLead(HashMap hashMapDataFilter) {
		String searchField = hashMapDataFilter.get("search")
		String cs_list = hashMapDataFilter.get("cs_list")
		String bulan = hashMapDataFilter.get("bulan")
		String tahun = hashMapDataFilter.get("tahun")
		
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Filter'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/button_Reset'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Nama CS'))
		
		if(!searchField.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Cari Nama CS'))
			List allCS = searchField.split(',')
			List allCSList = cs_list.split(',')
			for(int i=0; i<allCS.size(); i++) {
				WebUI.sendKeys(findTestObject('Object Repository/Dashboard/Lead/input_Nama CS'), allCS[i])
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/list_CS', [('cs_list') : allCSList[i]]))
			}
		} else {
			List allCSList = cs_list.split(',')
			for(int i=0; i<allCSList.size(); i++) {
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/list_CS', [('cs_list') : allCSList[i]]))
			}
		}
		
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Periode'))
		
		if(!bulan.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Bulan'))
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/list_Bulan', [('bulan') : bulan]))
		}
		
		if(!tahun.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/div_Tahun'))
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/list_Tahun', [('tahun') : tahun]))
		}
		
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Lead/button_Terapkan'))
		WebUI.delay(5)
	}
}
