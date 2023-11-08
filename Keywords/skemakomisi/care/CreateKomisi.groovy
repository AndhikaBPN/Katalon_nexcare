package skemakomisi.care

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.junit.Assert.format

import java.util.concurrent.ConcurrentHashMap.KeySetView

import org.openqa.selenium.Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import care.katalon.GeneralAction
import internal.GlobalVariable
//import jdk.nashorn.internal.runtime.FindProperty

public class CreateKomisi {
	GeneralAction gAction = new GeneralAction()

	def createDataKomisi(HashMap hashMapDataKomisi) {
		String searchField = hashMapDataKomisi.get("CS_Level")
		String list_cs = hashMapDataKomisi.get("List_CS")
		String poin_maks = hashMapDataKomisi.get("Poin_Maksimal")
		String komisi = hashMapDataKomisi.get("Komisi")
		String infinite = hashMapDataKomisi.get("Infinite")
		String list_comm = hashMapDataKomisi.get("list_comm")

		if(!searchField.equalsIgnoreCase("")) {
			gAction.clickElement(findTestObject('Object Repository/Skema Komisi/div_Pilih Level CS'))
			WebUI.setText(findTestObject('Object Repository/Skema Komisi/input_Skema Komisi_ddlcslevel'), list_cs)
			WebUI.sendKeys(findTestObject('Object Repository/Skema Komisi/input_Skema Komisi_ddlcslevel'), Keys.chord(Keys.ENTER))
		}

		boolean button = WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Skema Komisi/button_Poin  Komisi'), 'disabled', 10)
		//		boolean minPoint = WebUI.verifyElementHasAttribute(findTestObject('Object Repository/Skema Komisi/input_minPoint'), 'disabled', 10)
		boolean visibleMinPoint = WebUI.verifyElementVisible(findTestObject('Object Repository/Skema Komisi/input_minPoint'))

		if(button) {
			int i = 2
			while (visibleMinPoint) {
				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/btn_delete', [('num') : i]))
				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Ya'))
				i--
			}
		}



		//		if()

		//		if(searchField.equalsIgnoreCase("1")) {
		//			if(infinite.equalsIgnoreCase("Y")) {
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Poin  Komisi'))
		//
		//				if(!poin_maks.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input__maxPoint'), poin_maks)
		//				}
		//
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/input_Minimal 1_checkInfinity'))
		//
		//				if(!komisi.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input_IDR_tslcommision', [('list_comm') : list_comm]), komisi)
		//				}
		//
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Simpan'))
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Ya'))
		//				gAction.waitResultSelect(findTestObject('Object Repository/Skema Komisi/div_Berhasil'), "Berhasil")
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_OK'))
		//				WebUI.delay(5)
		//			} else {
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/div_Pilih Level CS'))
		////				gAction.clickElementSearchAndSelect(findTestObject('Object Repository/Skema Komisi/div_Pilih Level CS'), findTestObject('Object Repository/Skema Komisi/div_Pilih Level CS'), 1)
		////				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/div_CS Level 1'))
		//				WebUI.setText(findTestObject('Object Repository/Skema Komisi/input_Skema Komisi_ddlcslevel'), '1')
		//				WebUI.sendKeys(findTestObject('Object Repository/Skema Komisi/input_Skema Komisi_ddlcslevel'), Keys.chord(Keys.ENTER))
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Poin  Komisi'))
		//
		//				if(!poin_maks.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input__maxPoint'), poin_maks)
		//				}
		//
		//				if(!komisi.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input_IDR_tslcommision', [('list_comm') : list_comm]), komisi)
		//				}
		//
		//				WebUI.delay(5)
		//			}
		//		} else {
		//			if(infinite.equalsIgnoreCase("Y")) {
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Poin  Komisi'))
		//
		//				if(!poin_maks.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input__maxPoint'), poin_maks)
		//				}
		//
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/input_Minimal 1_checkInfinity'))
		//
		//				if(!komisi.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input_IDR_tslcommision', [('list_comm') : list_comm]), komisi)
		//				}
		//
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Simpan'))
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Ya'))
		//				gAction.waitResultSelect(findTestObject('Object Repository/Skema Komisi/div_Berhasil'), "Berhasil")
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_OK'))
		//				WebUI.delay(5)
		//			} else {
		//				gAction.clickElementSearchAndSelect(findTestObject('Object Repository/Skema Komisi/div_Pilih Level CS'), findTestObject('Object Repository/Skema Komisi/div_Pilih Level CS'), 2)
		////				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/div_CS Level 2'))
		//				gAction.clickElement(findTestObject('Object Repository/Skema Komisi/button_Poin  Komisi'))
		//
		//				if(!poin_maks.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input__maxPoint'), poin_maks)
		//				}
		//
		//				if(!komisi.equalsIgnoreCase("")) {
		//					gAction.clickElementAndType(findTestObject('Object Repository/Skema Komisi/input_IDR_tslcommision', [('list_comm') : list_comm]), komisi)
		//				}
		//
		//				WebUI.delay(5)
		//			}
		//		}
	}
}
