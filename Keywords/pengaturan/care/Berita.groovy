package pengaturan.care

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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import org.junit.internal.runners.statements.FailOnTimeout
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

//import internal.GlobalVariable
import care.katalon.GeneralAction
import care.katalon.VerifyElement
import groovy.json.StringEscapeUtils

public class Berita {
	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void refreshPage() {
		WebUI.refresh()
	}
	
	public void openBerita() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Pengaturan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_Berita'))
	}
	
	public searchBerita(HashMap hashMapSetBerita) {
		String title = hashMapSetBerita.get("Judul")
		
		GeneralAction.clickElementAndSearch(findTestObject('Object Repository/Pengaturan/Berita/input_SearchBerita'), title)
	}
	
	public void save(HashMap hashMapSetBerita) {
		String title = hashMapSetBerita.get("Judul")
		String link = hashMapSetBerita.get("Link URL")
		String category = hashMapSetBerita.get("Category")
		String action = hashMapSetBerita.get("Action")
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/button_Simpan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/button_Ya'))
		
		if(category.equalsIgnoreCase('negative')) {
			try {
				String checkJudul = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Berita/div_CheckJudul'))
				if(checkJudul.contains('Judul tidak boleh kosong!') || checkJudul.contains('Jumlah karakter harus lebih besar dari 5') || checkJudul.contains('Judul hanya boleh mengandung spesial karakter ?')) {
					KeywordUtil.logInfo("Category: " + category)	
					KeywordUtil.logInfo("Message: Title tidak sesuai")
				}
				
				String checkUrl = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Berita/div_CheckURL'))
				if(checkUrl.contains('Url harus mengandung format http:// atau https://')) {
					KeywordUtil.logInfo("Category: " + category)
					KeywordUtil.logInfo("Message: URL tidak sesuai")
				}
			} catch (Exception e) {
				e.printStackTrace()
			}
			
			refreshPage()
			WebUI.delay(2)
			
			if(action.equalsIgnoreCase('add')) {
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_Back', [('action') : 'Tambah Berita']))
			} else if(action.equalsIgnoreCase('edit')){
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_Back', [('action') : 'Ubah Berita']))
			} else {
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_Back', [('action') : 'Detail Berita']))
			}
		}
		
		try {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/button_OK'))
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	
	public void addBerita(HashMap hashMapSetBerita) {
		String title = hashMapSetBerita.get("Judul")
		String desc = hashMapSetBerita.get("Deskripsi")
		String link = hashMapSetBerita.get("Link URL")
		String status = hashMapSetBerita.get("Status")
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_TambahBerita'))
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Berita/input_Title'), 3)
		
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Berita/input_Title'), title)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Berita/textarea_Description'), desc)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Berita/input_Link'), link)
		
		if(status.equalsIgnoreCase('tidak aktif')) {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/tgl_StatusTidakAktif'))
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/tgl_StatusAktif'))
		}
		
		save(hashMapSetBerita)
	}
	
	public void updateBerita(HashMap hashMapSetBerita) {
		String title = hashMapSetBerita.get("Judul")
		String newTitle = hashMapSetBerita.get("New Judul")
		String desc = hashMapSetBerita.get("Deskripsi")
		String link = hashMapSetBerita.get("Link URL")
		String status = hashMapSetBerita.get("Status")
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_Edit', [('nama') : title]))
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Berita/input_Title'), 3)
		
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Berita/input_Title'), newTitle)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Berita/textarea_Description'), desc)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Berita/input_Link'), link)
		
		if(status.equalsIgnoreCase('tidak aktif')) {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/tgl_StatusTidakAktif'))
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/tgl_StatusAktif'))
		}
		
		save(hashMapSetBerita)
	}
	
	public void viewBerita(HashMap hashMapSetBerita) {
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_View'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Pengaturan/Berita/txt_Judul'), 3, FailureHandling.OPTIONAL)
		
		String expectedTitle = hashMapSetBerita.get("Judul")
		String actualTitle = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Berita/txt_Judul'))
		KeywordUtil.logInfo("Expected Result: " + expectedTitle)
		KeywordUtil.logInfo("Actual Result: " + actualTitle)
		try {
			boolean verifyTitle = VerifyElement.verifyMatch(actualTitle, expectedTitle, false)
			KeywordUtil.logInfo("Title match: " + verifyTitle)
		} catch (Exception e) {
			e.printStackTrace()
		}
		
		String expectedDesc = hashMapSetBerita.get("Deskripsi")
		String actualDesc = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Berita/textarea_Deskripsi'))
		KeywordUtil.logInfo("Expected Result: " + expectedDesc)
		KeywordUtil.logInfo("Actual Result: " + actualDesc)
		try {
			boolean verifyDesc = VerifyElement.verifyMatch(actualDesc, expectedDesc, false)
			KeywordUtil.logInfo("Desc match: " + verifyDesc)
		} catch (Exception e) {
			e.printStackTrace()
		}
		String expectedLink = hashMapSetBerita.get("Link URL")
		String actualLink = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Berita/txt_Link'))
		KeywordUtil.logInfo("Expected Result: " + expectedLink)
		KeywordUtil.logInfo("Actual Result: " + actualLink)
		try {
			boolean verifyLink = VerifyElement.verifyMatch(actualLink, expectedLink, false)
			KeywordUtil.logInfo("Link match: " + verifyLink)
		} catch (Exception e) {
			e.printStackTrace()
		}
		
		String expectedStatus = hashMapSetBerita.get("Status")
		String actualStatus = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Berita/txt_Status'))
		KeywordUtil.logInfo("Expected Result: " + expectedStatus)
		KeywordUtil.logInfo("Actual Result: " + actualStatus)
		try {
			boolean verifyStatus = VerifyElement.verifyMatch(actualStatus, expectedStatus, false)
			KeywordUtil.logInfo("Status match: " + verifyStatus)
		} catch (Exception e) {
			e.printStackTrace()
		}
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Berita/btn_Back', [('action') : 'Detail Berita']))
	}
}
