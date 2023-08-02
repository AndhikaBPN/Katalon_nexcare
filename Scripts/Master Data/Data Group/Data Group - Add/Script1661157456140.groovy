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

try {
	generalAction.login("")
	dataGroup.menuDataGroup()
	
	String excelLocation = 'Master Data\\Data Group\\dataGroup - add.xlsx'
	String sheetNameDataGroup = 'DataGroup-add'
	
	List<HashMap> listHashMapDataGroup = handleTestData.readTestData(excelLocation, sheetNameDataGroup, true)
	for (int i = 0; i < listHashMapDataGroup.size(); i++) {
		try {
			HashMap getHashMapDataGroup = listHashMapDataGroup.get(i)
			String groupID = getHashMapDataGroup.get('groupID')
			String category = getHashMapDataGroup.get('category')
			KeywordUtil.logInfo(getHashMapDataGroup.toString())
			String testDataNumber = getHashMapDataGroup.get('TD')
			KeywordUtil.logInfo("TD: " + testDataNumber)
			dataGroup.addDataGroup()
			dataGroup.setDataGroup(getHashMapDataGroup)
			dataGroup.saveDataGroup(testDataNumber)
			if (category.equalsIgnoreCase("Positive")) {
				dataGroup.viewDetail(groupID)
				dataGroup.verifyDataGroup(getHashMapDataGroup)
				dataGroup.closeViewDetail()
			}
		} catch (Exception e) {
			KeywordUtil.markFailed(e.printStackTrace())
			continue;
		}
	}
	generalAction.logoutAndCloseBrowser()
} catch (Exception e) {
	e.printStackTrace()
	generalAction.closeBrowser()
}
