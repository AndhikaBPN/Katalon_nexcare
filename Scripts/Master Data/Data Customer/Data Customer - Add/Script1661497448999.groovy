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

import masterdata.care.DataCustomer
import care.katalon.GeneralAction
import care.katalon.HandleWebDriver
import care.katalon.HandleTestData

HandleWebDriver handleWebDriver = new HandleWebDriver()
handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

GeneralAction generalAction = new GeneralAction()
HandleTestData handleTestData = new HandleTestData()
DataCustomer dataCustomer = new DataCustomer()

generalAction.login("")

dataCustomer.menuDataCustomer()

String excelLocation = 'Master Data\\Data Customer\\dataCustomer - add.xlsx'
String sheetNameDataCustomer = 'DataCustomer'

List<HashMap> listHashMapDataCustomer = handleTestData.readTestData(excelLocation, sheetNameDataCustomer, true)

String namaCustomer = ''
	
	for (int i = 0; i < listHashMapDataCustomer.size(); i++) {
		try {
			HashMap getHashMapDataCustomer = listHashMapDataCustomer.get(i)
			String testDataNumber = getHashMapDataCustomer.get('TD')
			String customerName = getHashMapDataCustomer.get('customerName')
			String category = getHashMapDataCustomer.get('category')
			String msg = getHashMapDataCustomer.get('msg')
			
			if(category.equalsIgnoreCase('positive')) {
				if(namaCustomer.equals('')) {
					dataCustomer.cariPelanggan(customerName)
				}else if(!namaCustomer.equalsIgnoreCase(customerName)) {
					dataCustomer.saveDocument()
					dataCustomer.verifyMsg(msg,testDataNumber,category)
					WebUI.delay(3)
					dataCustomer.cariPelanggan(customerName)
				}
				namaCustomer = customerName
				dataCustomer.setDataCustomer(getHashMapDataCustomer)
				
			} else {
				if(!namaCustomer.equalsIgnoreCase(customerName)) {
					dataCustomer.saveDocument()
					WebUI.delay(3)
				}
				namaCustomer = customerName
				dataCustomer.refreshWeb()
				dataCustomer.cariPelanggan(customerName)
				dataCustomer.setDataCustomer(getHashMapDataCustomer)
				dataCustomer.saveDocument()
				dataCustomer.verifyMsg(msg,testDataNumber,category)
			}
		} catch (Exception e) {
			KeywordUtil.markFailed(e.printStackTrace())
			continue;
		}
	}

generalAction.logoutAndCloseBrowser()
