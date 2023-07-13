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

import care.katalon.GeneralAction
import care.katalon.HandleTestData
import care.katalon.HandleWebDriver
import internal.GlobalVariable
import skemakomisi.care.CreateKomisi
import skemakomisi.care.OpenKomisi

import org.openqa.selenium.Keys as Keys

HandleWebDriver handleWebDriver = new HandleWebDriver()
handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

GeneralAction gAction = new GeneralAction()
HandleTestData handleTestData = new HandleTestData()
OpenKomisi openKomisi = new OpenKomisi()
CreateKomisi createKomisi = new CreateKomisi()

gAction.login("")
openKomisi.openSkemaKomisi()

String excelLocation = 'Pengaturan\\Skema Komisi\\Skema Komisi -create.xlsx'
String sheetName = 'Sheet2'
List komisi = new ArrayList<>()

List<HashMap> listHashMapDataKomisi = handleTestData.readTestData(excelLocation, sheetName, true)

for(int i=0; i<listHashMapDataKomisi.size(); i++) {
	try {
		HashMap getHashMapDataKomisi = listHashMapDataKomisi.get(i)
		KeywordUtil.logInfo(getHashMapDataKomisi.toString())
		String tDNumber = getHashMapDataKomisi.get('TD')
		createKomisi.createDataKomisi(getHashMapDataKomisi)
//		komisi.add(newKomisi)
	} catch (Exception e) {
		KeywordUtil.markFailed(e.printStackTrace())
		continue;
		gAction.refreshPage()
	}
}

