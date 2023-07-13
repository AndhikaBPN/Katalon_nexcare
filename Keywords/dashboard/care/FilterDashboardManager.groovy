package dashboard.care

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.geom.GeneralPath

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
import org.openqa.selenium.WebElement
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.Keys as Keys

import care.katalon.GeneralAction
import care.katalon.HandleWebDriver
import internal.GlobalVariable

public class FilterDashboardManager {
	GeneralAction gAction =  new GeneralAction()

	public void menuDashboardManager() {
	}

	def setFilterManager(HashMap hashMapDataFilter) {
		String searchField = hashMapDataFilter.get("search")
		String cs_list = hashMapDataFilter.get("cs_list")
		String bulan = hashMapDataFilter.get("bulan")
		String tahun = hashMapDataFilter.get("tahun")

		gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/div_Filter'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/button_Reset'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/div_Nama CS'))
		gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/div_Cari Nama CS'))


		if(!searchField.equalsIgnoreCase("")) {
			List allCS = searchField.split(',')
			List allCSList = cs_list.split(',')
			for(int i=0; i<allCS.size(); i++) {
				WebUI.sendKeys(findTestObject('Object Repository/Dashboard/Manager/input_Nama CS'), allCS[i])
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/list_CS', [('cs_list') : allCSList[i]]))
			}
		} else {
			List allCSList = cs_list.split(',')
			for(int i=0; i<allCSList.size(); i++) {
				gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/list_CSNonHL', [('cs_list') : allCSList[i]]))
			}
		}

		if(!bulan.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/div_Periode'))
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/div_Bulan'))
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/list_Bulan', [('bulan') : bulan]))
		}

		if(!tahun.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/div_Tahun'))
			gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/list_Tahun', [('tahun') : tahun]))
		}

		gAction.clickElement(findTestObject('Object Repository/Dashboard/Manager/button_Terapkan'))
		WebUI.delay(5)
	}
}
