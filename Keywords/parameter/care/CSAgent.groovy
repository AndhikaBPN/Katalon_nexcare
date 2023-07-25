package parameter.care

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

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import internal.GlobalVariable
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation
import care.katalon.GeneralAction
import care.katalon.VerifyElement

public class CSAgent {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()
	
	public void refreshPage() {
		WebUI.refresh()
	}

	public void menuCSAgent() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Pengaturan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/btnCSAgent'))
		refreshPage()
	}

	public void setCSAgent(HashMap hashMapSetCSAgent) {
		String TD = hashMapSetCSAgent.get("TD")
		String NamaCS = hashMapSetCSAgent.get("NamaCS")
		String ShiftCS = hashMapSetCSAgent.get("ShiftCS")
		String monthStart = hashMapSetCSAgent.get("monthStart")
		String periodeStart = hashMapSetCSAgent.get("periodeStart")
		String monthEnd = hashMapSetCSAgent.get("monthEnd")
		String periodeEnd = hashMapSetCSAgent.get("periodeEnd")

		List allCSName = NamaCS.split(',')
		for(int i = 0; i < allCSName.size(); i++) {
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/CS Agent/Pilih Nama CS'),
					findTestObject('Object Repository/Pengaturan/CS Agent/SelectCSAgent'), allCSName[i])
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/CloseDropDown'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/Periode'))
		String ActualMonth = ''
		
		while (!monthStart.equalsIgnoreCase(ActualMonth)) {
			ActualMonth = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/CS Agent/VerifyMonth'))
			if(!monthStart.equalsIgnoreCase(ActualMonth)) {
				KeywordUtil.logInfo("Actual aaa " + ActualMonth)
				KeywordUtil.logInfo("Expected " + monthStart)
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/nextMonth'))
			}else {
				break;
			}
		}
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/PeriodeStart', [('periodeStart') : periodeStart]))

		while (!monthEnd.equalsIgnoreCase(ActualMonth)) {
			ActualMonth = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/CS Agent/VerifyMonth'))
			if(!monthEnd.equalsIgnoreCase(ActualMonth)) {
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/nextMonth'))
				KeywordUtil.logInfo("Actual aaa " + ActualMonth)
				KeywordUtil.logInfo("Expected " + monthEnd)
			}else {
				break;
			}
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/PeriodeEnd', [('periodeEnd') : periodeEnd]))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/CloseDropDown'))

		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/Shift CS'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/SelectShift', [('ShiftCS') : ShiftCS]))
		save()
	}
	
	public void save(String tD) {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/btnSimpan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/KonfrimasiSimpan'))
		String validasiShift = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/CS Agent/Validasi Shift'))
		if (validasiShift.contains('sudah berada dalam range tanggal berikut')) {
			KeywordUtil.logInfo(tD + ' ' + validasiShift)
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/btnYa'))
		}
		WebUI.delay(2)
	}
}
