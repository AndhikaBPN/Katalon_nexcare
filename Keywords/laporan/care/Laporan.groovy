package laporan.care

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

public class Laporan {
	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void menuLaporan() {
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Laporan'))
	}

	public void refreshPage() {
		WebUI.delay(3)
		WebUI.refresh()
	}

	public void setLaporan(HashMap hashMapSetLaporan) {
		String principal = hashMapSetLaporan.get("principal")
		String distibutor = hashMapSetLaporan.get("distibutor")
		String status = hashMapSetLaporan.get("status")
		String monthStart = hashMapSetLaporan.get("monthStart")
		String periodeStart = hashMapSetLaporan.get("periodeStart")
		String monthEnd = hashMapSetLaporan.get("monthEnd")
		String periodeEnd = hashMapSetLaporan.get("periodeEnd")
		String distributorCode = hashMapSetLaporan.get("distributorCode")

		if(!principal.equalsIgnoreCase("")) {
			GeneralAction.clickElement(findTestObject('Object Repository/Laporan/Principal'))
			GeneralAction.clickElementAndSearch(findTestObject('Object Repository/Laporan/SearchPrincipal'), principal)
			GeneralAction.clickElement(findTestObject('Object Repository/Laporan/SelectPrincipal', [('principal') : principal]))
		}

		if(!distibutor.equalsIgnoreCase("")) {
			List allCustomerName = distibutor.split(',')
			for(int i = 0; i < allCustomerName.size(); i++) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Laporan/Pelanggan'),
						findTestObject('Object Repository/Laporan/SelectPelanggan'), allCustomerName[i])
			}
		}

		GeneralAction.clickElement(findTestObject('Object Repository/Laporan/closeSelection'))

		if(!status.equalsIgnoreCase("")) {
			GeneralAction.clickElement(findTestObject('Object Repository/Laporan/Status'))
			List allStatus = status.split(',')
			for(int i = 0; i < allStatus.size(); i++) {
				String ActualStatus = ""
				String ExpectedStatus = allStatus[i]

				if(ExpectedStatus.equalsIgnoreCase("Unassigned")) {
					GeneralAction.clickElement(findTestObject('Object Repository/Laporan/unassigned'))
				}

				if(ExpectedStatus.equalsIgnoreCase("Assigned")) {
					GeneralAction.clickElement(findTestObject('Object Repository/Laporan/assigned'))
				}

				if(ExpectedStatus.equalsIgnoreCase("on hold")) {
					GeneralAction.clickElement(findTestObject('Object Repository/Laporan/onhold'))
				}

				if(ExpectedStatus.equalsIgnoreCase("closed")) {
					GeneralAction.clickElement(findTestObject('Object Repository/Laporan/closed'))
				}

				if(ExpectedStatus.equalsIgnoreCase("In Progress")) {
					GeneralAction.clickElement(findTestObject('Object Repository/Laporan/inprogress'))
				}

				if(ExpectedStatus.equalsIgnoreCase("Resolved")) {
					GeneralAction.clickElement(findTestObject('Object Repository/Laporan/resolved'))
				}

				if(ExpectedStatus.equalsIgnoreCase("Under Observation")) {
					GeneralAction.clickElement(findTestObject('Object Repository/Laporan/underobservation'))
				}
			}
		}

		GeneralAction.clickElement(findTestObject('Object Repository/Laporan/closeSelection'))

		GeneralAction.clickElement(findTestObject('Object Repository/Laporan/Periode'))
		String ActualMonth = ''
		while (!monthStart.equalsIgnoreCase(ActualMonth)==true) {
			ActualMonth = GeneralAction.getTextFromElement(findTestObject('Object Repository/Laporan/VerifyMonth'))
			if(!monthStart.equalsIgnoreCase(ActualMonth)) {
				GeneralAction.clickElement(findTestObject('Object Repository/Laporan/PreviousMonth'))
				KeywordUtil.logInfo("Actual aaa " + ActualMonth)
				KeywordUtil.logInfo("Expected " + monthStart)
			}else {
				break;
			}
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/PeriodeStart', [('periodeStart') : periodeStart]))

		while (!monthEnd.equalsIgnoreCase(ActualMonth)==true) {
			ActualMonth = GeneralAction.getTextFromElement(findTestObject('Object Repository/Laporan/VerifyMonth'))
			if(!monthEnd.equalsIgnoreCase(ActualMonth)) {
				GeneralAction.clickElement(findTestObject('Object Repository/Laporan/NextMonth'))
				KeywordUtil.logInfo("Actual aaa " + ActualMonth)
				KeywordUtil.logInfo("Expected " + monthEnd)
			}else {
				break;
			}
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/CS Agent/PeriodeEnd', [('periodeEnd') : periodeEnd]))
		GeneralAction.clickElement(findTestObject('Object Repository/Laporan/closeSelection'))

		if(distributorCode.equalsIgnoreCase("")) {
			if(distributorCode.equalsIgnoreCase("Y")) {
				GeneralAction.clickElement(findTestObject('Object Repository/Laporan/Kode Distributor'))
			}
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Laporan/Export File'))
		GeneralAction.clickElement(findTestObject('Object Repository/Laporan/konfrimSave'))
	}
}
