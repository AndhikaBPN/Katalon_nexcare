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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import care.katalon.HandleWebDriver
import care.katalon.GeneralAction
import care.katalon.HandleTestData
import care.katalon.VerifyElement
import dashboard.care.FilterDashboardPrincipal
import dashboard.care.OpenDashboard

HandleWebDriver handleWebDriver = new HandleWebDriver()
handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

GeneralAction gAction = new GeneralAction()
HandleTestData handleTestData = new HandleTestData()
OpenDashboard oDashboard =  new OpenDashboard()
FilterDashboardPrincipal principal = new FilterDashboardPrincipal()

gAction.login("")
oDashboard.open("principal")

String excelLocation = 'Dashboard\\Principal\\Filter Dashboard Principal.xlsx'
String sheetName = 'Sheet1'
List filter = new ArrayList<>()

List<HashMap> listHashMapDataFilter = handleTestData.readTestData(excelLocation, sheetName, true)

for(int i=0; i<listHashMapDataFilter.size(); i++) {
	try {
		HashMap getHashMapDataFilter = listHashMapDataFilter.get(i)
		KeywordUtil.logInfo(getHashMapDataFilter.toString())
		String tDNumber = getHashMapDataFilter.get('TD')
		
		String newFilter = principal.setFilterPrincipal(getHashMapDataFilter)
		filter.add(newFilter)
	} catch (Exception e) {
		KeywordUtil.markFailed(e.printStackTrace())
		continue;
		gAction.refreshPage()
	}
}
