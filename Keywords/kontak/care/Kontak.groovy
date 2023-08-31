package kontak.care

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import care.katalon.GeneralAction
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.WebElement

import care.katalon.HandleWebDriver
import internal.GlobalVariable
import care.katalon.VerifyElement

public class Kontak {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void menuKontak() {
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Others/Kontak'))
	}

	public void setEditKontak(HashMap hashMapEditKontak) {
		String customer_name = hashMapEditKontak.get("customer_name")
		String pic_name = hashMapEditKontak.get("pic_name")
		String pic_edit = hashMapEditKontak.get("pic_edit")
		String email_edit = hashMapEditKontak.get("email_edit")
		String phone_edit = hashMapEditKontak.get("phone_edit")

		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Kontak/Search'),
				findTestObject('Object Repository/Kontak/Search'), customer_name)
		GeneralAction.clickElement(findTestObject('Object Repository/Kontak/Nama Distributor', [('customer_name') : customer_name]))
		GeneralAction.clickElement(findTestObject('Object Repository/Kontak/Edit', [('pic_name') : pic_name]))
		if(!pic_edit.equalsIgnoreCase("")) {
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Kontak/EditPICName'), pic_edit)
		}
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Kontak/EditNoTelp'), phone_edit)
		if(!email_edit.equalsIgnoreCase("")) {
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Kontak/EditEmailPIC'), email_edit)
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Kontak/Simpan'))
		WebUI.delay(3)
	}
}
