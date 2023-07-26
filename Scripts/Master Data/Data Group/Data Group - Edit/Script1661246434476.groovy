import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

import masterdata.care.DataGroup
import care.katalon.GeneralAction
import care.katalon.HandleWebDriver
import care.katalon.HandleTestData

HandleWebDriver handleWebDriver = new HandleWebDriver()
handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

GeneralAction generalAction = new GeneralAction()
HandleTestData handleTestData = new HandleTestData()
DataGroup dataGroup = new DataGroup()

generalAction.login("")

dataGroup.menuDataGroup()

String excelLocation = 'Master Data\\Data Group\\dataGroup - edit.xlsx'
String sheetNameDataGroup = 'DataGroup-edit'

List<HashMap> listHashEditMapDataGroup = handleTestData.readTestData(excelLocation, sheetNameDataGroup, true)

for (int i = 0; i < listHashEditMapDataGroup.size(); i++) {
	try {
		HashMap getHashMapDataGroup = listHashEditMapDataGroup.get(i)
				String groupID = getHashMapDataGroup.get('groupID')
				String TD = getHashMapDataGroup.get('TD')
				String category = getHashMapDataGroup.get('category')
				
				dataGroup.viewEdit(groupID)
				dataGroup.editDetail(getHashMapDataGroup)
				dataGroup.saveDataGroup(TD)
				if(category.equalsIgnoreCase('positive')) {
					dataGroup.viewDetail(groupID)
					dataGroup.verifyDataGroup(getHashMapDataGroup)
					dataGroup.closeViewDetail()
				}
	} catch (Exception e) {
		e.printStackTrace()
	}
}
generalAction.logoutAndCloseBrowser()