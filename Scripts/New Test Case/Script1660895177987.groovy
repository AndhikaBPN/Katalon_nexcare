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
import org.openqa.selenium.Keys as Keys
import care.katalon.HandleTestData as HandleTestData

WebUI.openBrowser('')

WebUI.navigateToUrl('https://sandbox-care.nexcloud.id/login')

WebUI.setText(findTestObject('Object Repository/spyLogin/input_Version2.1.0_username'), 'admin')

WebUI.setEncryptedText(findTestObject('Object Repository/spyLogin/input_Version2.1.0_password'), 'JWbpzl6QiPo691IgMcv2/IRT5e1GzOft')

WebUI.click(findTestObject('Object Repository/spyLogin/button_Masuk'))

WebUI.click(findTestObject('Object Repository/spyLogin/span_MASTER DATA'))

WebUI.click(findTestObject('Object Repository/spyLogin/span_DATA GROUP'))

WebUI.click(findTestObject('Object Repository/spyLogin/span_Tambah'))

WebUI.click(findTestObject('Object Repository/spyLogin/div_Data Scope'))

