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

String excelLocation = 'Master Data\\Data Customer\\dataCustomer - delete.xlsx'
String sheetNameDataCustomerDelete = 'DataCustomer - Delete'

List<HashMap> listHashMapDataCustomerDelete = handleTestData.readTestData(excelLocation, sheetNameDataCustomerDelete, true)

String namaCustomer = ''
HashMap<String, List> dataDelete = new HashMap<String, List>()
List dataWithSameCustomerName = new ArrayList<>()

for (int i = 0; i < listHashMapDataCustomerDelete.size(); i++) {
	HashMap getHashMapDeleteDataCustomer = listHashMapDataCustomerDelete.get(i)
	String customerName = getHashMapDeleteDataCustomer.get('customerName')
	
	if(namaCustomer.equals('') || namaCustomer.equals(customerName)) {
		namaCustomer = customerName
		dataWithSameCustomerName.add(getHashMapDeleteDataCustomer)
		if(i == listHashMapDataCustomerDelete.size() - 1) {
			dataDelete.put(namaCustomer, dataWithSameCustomerName)
		}
	} else {
		dataDelete.put(namaCustomer, dataWithSameCustomerName)
		dataWithSameCustomerName = new ArrayList<>()
		namaCustomer = customerName
		i = i - 1
	}
}

for(String i : dataDelete.keySet()) {

	try {
		String customerName = i
		List hashMapDelete = dataDelete.get(customerName)
		
		dataCustomer.viewData(customerName)
		dataCustomer.deleteCustomer(hashMapDelete)
		dataCustomer.saveDocument()
	}catch (Exception e) {
			KeywordUtil.markFailed(e.printStackTrace())
			continue;
		}
	}
generalAction.logoutAndCloseBrowser()