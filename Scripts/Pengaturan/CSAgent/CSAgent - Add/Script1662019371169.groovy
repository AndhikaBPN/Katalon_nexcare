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

import parameter.care.CSAgent
import care.katalon.GeneralAction
import care.katalon.HandleWebDriver
import groovy.inspect.swingui.GeneratedBytecodeAwareGroovyClassLoader
import care.katalon.HandleTestData

HandleWebDriver handleWebDriver = new HandleWebDriver()
handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

GeneralAction generalAction = new GeneralAction()
HandleTestData handleTestData = new HandleTestData()
CSAgent csagent = new CSAgent()

try {
	generalAction.login("")
	csagent.menuCSAgent()
	
	String excelLocation = 'Pengaturan\\CS Agent\\CS Agent - add.xlsx'
	String sheetNameCSAgent = 'CSAgent-add'
	
	List<HashMap> listHashMapCSAgent = handleTestData.readTestData(excelLocation, sheetNameCSAgent, true)
	for (int i = 0; i < listHashMapCSAgent.size(); i++) {
		try {
			HashMap getHashMapCSAgent = listHashMapCSAgent.get(i)
					KeywordUtil.logInfo(getHashMapCSAgent.toString())
					String testDataNumber = getHashMapCSAgent.get('TD')
					csagent.setCSAgent(getHashMapCSAgent)
					
		}catch (Exception e) {
			KeywordUtil.markFailed(e.printStackTrace())
			continue;
		}
	}
	generalAction.logoutAndCloseBrowser()
} catch (Exception e) {
	e.printStackTrace()
	generalAction.closeBrowser()
}
