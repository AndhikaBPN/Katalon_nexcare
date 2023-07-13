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
import com.kms.katalon.core.webservice.keyword.builtin.GetHarFileGenerationKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

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

String excelLocation = 'Master Data\\Data Customer\\dataCustomer - edit.xlsx'
String sheetNameEditDataCustomer = 'DataCustomer-Edit'

List<HashMap> listHashMapEditDataCustomer = handleTestData.readTestData(excelLocation, sheetNameEditDataCustomer, true)

String namaCustomer = ''
HashMap<String, List> dataEdit = new HashMap<String, List>()
List dataWithSameCustomerName = new ArrayList<>()

for (int i = 0; i < listHashMapEditDataCustomer.size(); i++) {
	HashMap getHashMapEditDataCustomer = listHashMapEditDataCustomer.get(i)
	String customerName = getHashMapEditDataCustomer.get('customerName')
	
	if(namaCustomer.equals('') || namaCustomer.equals(customerName)) {
		namaCustomer = customerName
		dataWithSameCustomerName.add(getHashMapEditDataCustomer)
		if(i == listHashMapEditDataCustomer.size() - 1) {
			dataEdit.put(namaCustomer, dataWithSameCustomerName)
		}
	} else {
		dataEdit.put(namaCustomer, dataWithSameCustomerName)
		dataWithSameCustomerName = new ArrayList<>()
		namaCustomer = customerName
		i = i - 1
	}
}

for(String i : dataEdit.keySet()) {

	try {
		String customerName = i
		List hashMapEditDetail = dataEdit.get(customerName)
		
		dataCustomer.viewData(customerName)
		dataCustomer.editDocumentDetail(hashMapEditDetail)
		dataCustomer.saveDocument()
		dataCustomer.viewData(customerName)
		dataCustomer.verifyViewDataCustomer(hashMapEditDetail)
	}catch (Exception e) {
			KeywordUtil.markFailed(e.printStackTrace())
			continue;
		}
	}
generalAction.logoutAndCloseBrowser()