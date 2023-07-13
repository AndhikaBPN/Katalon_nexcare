import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
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
import org.openqa.selenium.Keys as Keys
import daftartiket.care.BuatTiketBaru
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

import care.katalon.HandleWebDriver
import care.katalon.GeneralAction
import care.katalon.HandleWebDriver
import care.katalon.HandleTestData


HandleWebDriver handleWebDriver = new HandleWebDriver()
handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

GeneralAction generalAction = new GeneralAction()
HandleTestData handleTestData = new HandleTestData()
BuatTiketBaru buattiketbaru = new BuatTiketBaru()

generalAction.login("operator")

String excelLocation = 'Daftar Tiket\\Buat Tiket\\Buat Tiket - add.xlsx'
String sheetName = 'Sheet3'
List tiketnumber = new ArrayList<>()

List<HashMap> listHashMapCreateTicket = handleTestData.readTestData(excelLocation, sheetName, true)

for (int i = 0; i < listHashMapCreateTicket.size(); i++) {
	try {
		HashMap getHashMapCreateTicket = listHashMapCreateTicket.get(i)
		KeywordUtil.logInfo(getHashMapCreateTicket.toString())
		String testDataNumber = getHashMapCreateTicket.get('TD')
		
		buattiketbaru.menuCreateTicket()
		String newTiketNumber = buattiketbaru.setCreateNewTicket(getHashMapCreateTicket)
		
		tiketnumber.add(newTiketNumber)
//		generalAction.refreshPage()
	} catch (Exception e) {
		KeywordUtil.markFailed(e.printStackTrace())
		continue;
		generalAction.refreshPage()
	}
	generalAction.refreshPage()
}
		
KeywordUtil.logInfo(tiketnumber.toString())
buattiketbaru.editOneSheet(excelLocation, sheetName, tiketnumber, 2)

generalAction.logout()

