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
import java.lang.Integer as Integer

//import internal.GlobalVariable
import care.katalon.GeneralAction
import care.katalon.VerifyElement
import groovy.json.StringEscapeUtils

public class Kms {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void refreshPage() {
		WebUI.refresh()
	}

	public void saveKMSCategory(HashMap hashMapSetKMS) {
		String category = hashMapSetKMS.get("Category")

		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_Simpan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_Ya'))

		if(category.equalsIgnoreCase('negative')) {
			try {
				String checkKategori = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/check_KMS'))
				if(checkKategori.equalsIgnoreCase('Kategori KMS tidak boleh kosong!') || checkKategori.equalsIgnoreCase('Kategori KMS tidak boleh mengandung spesial karakter ?')) {
					KeywordUtil.logInfo("NEGATIVE CASE | Kategori tidak sesuai")
				}

				String checkType = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/check_Type'))
				if(checkType.equalsIgnoreCase('Tipe tidak boleh kosong!')) {
					KeywordUtil.logInfo("NEGATIVE CASE | Type tidak sesuai")
				}

				String checkLevel = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/check_Level'))
				if(checkLevel.equalsIgnoreCase('Level tidak boleh kosong!')) {
					KeywordUtil.logInfo("NEGATIVE CASE | Level tidak sesuai")
				}

				String checkParent = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/check_Parent'))
				if(checkParent.equalsIgnoreCase('Parent tidak boleh kosong!')) {
					KeywordUtil.logInfo("NEGATIVE CASE | Parent tidak sesuai")
				}
			} catch (Exception e) {
				e.printStackTrace()
			}
		}

		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_OK'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Pengaturan/btn_KMS'))
	}
	
	public void saveKMS(HashMap hashMapSetKMS) {
		String category = hashMapSetKMS.get("Category")
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/button_SimpanKMS'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_Ya'))
		
		if(category.equalsIgnoreCase('negative')) {
			try {
				String checkJudul = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/div_checkJudul'))
				if(checkJudul.equalsIgnoreCase('Judul tidak boleh kosong!') || checkJudul.equalsIgnoreCase('Judul hanya boleh mengandung spesial karakter ?') || checkJudul.equalsIgnoreCase('Jumlah karakter harus lebih besar dari 5')) {
					KeywordUtil.logInfo("NEGATIVE CASE | Judul tidak sesuai")
				}

				String checkType = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/div_checkTipe'))
				if(checkType.equalsIgnoreCase('Tipe tidak boleh kosong!')) {
					KeywordUtil.logInfo("NEGATIVE CASE | Type tidak sesuai")
				}

				String checkLink = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/check_Level'))
				if(checkLink.equalsIgnoreCase('Url harus mengandung format http:// atau https://')) {
					KeywordUtil.logInfo("NEGATIVE CASE | Link URL tidak sesuai")
				}
			} catch (Exception e) {
				e.printStackTrace()
			}
		}
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_OK'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_Kategori KMS'))
	}

	public void openKMS() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Pengaturan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Pengaturan/btn_KMS'))

		WebUI.waitForElementVisible(findTestObject('Object Repository/Pengaturan/KMS Kategori/input_SearchKMSKategori'), 3, FailureHandling.OPTIONAL)
	}

	public void searchKMS(HashMap hashMapSetKMS) {
		String kmsCategory = hashMapSetKMS.get("Parent")

		GeneralAction.clickElementAndSearch(findTestObject('Object Repository/Pengaturan/KMS Kategori/input_SearchKMSKategori'), kmsCategory)
	}

	public void viewDetail(HashMap hashMapSetKMS) {
		String kmsCategory = hashMapSetKMS.get("Kategori KMS")
		String level = hashMapSetKMS.get("Level")
		int intLevel = Integer.parseInt(level)
		boolean isMoreThanOne = false
		
		
		KeywordUtil.logInfo("Level: " + level)

		if(intLevel>1) {
			isMoreThanOne = true
			KeywordUtil.logInfo("Lebih dari 1: " + isMoreThanOne)
			try {
				while(WebUI.verifyElementNotPresent(findTestObject('Object Repository/Pengaturan/KMS Kategori/check_LevelDiv', [('level') : level]), 3, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/btn_openKms'))
				}
				if(WebUI.verifyElementPresent(findTestObject('Object Repository/Pengaturan/KMS Kategori/check_LevelDiv', [('level') : level]), 3, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/btn_ViewDetail', [('level') : level]))
				}
			} catch (Exception e) {
				e.printStackTrace()
			}
		} else {
			KeywordUtil.logInfo("Lebih dari 1: " + isMoreThanOne)
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/btn_ViewDetailLv1'))
		}
	}

	public void addKMSCategory(HashMap hashMapSetKMS) {
		String kmsCategory = hashMapSetKMS.get("Kategori KMS")
		String type = hashMapSetKMS.get("Tipe")
		String level = hashMapSetKMS.get("Level")
		String parent = hashMapSetKMS.get("Parent")
		String icon = hashMapSetKMS.get("Icon")
		String lowestCategory = hashMapSetKMS.get("Kategori Terkecil")
		String status = hashMapSetKMS.get("Status")
		String category = hashMapSetKMS.get("Category")

		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/btn_AddCategory'))
		refreshPage()

		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/KMS Kategori/input_Category'), kmsCategory)

		if(!type.equalsIgnoreCase('')) {
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_Pilih Tipe'),
					findTestObject('Object Repository/Pengaturan/KMS Kategori/input_Type'), type)
		}

		if(!level.equalsIgnoreCase('')) {
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_Pilih Level'),
					findTestObject('Object Repository/Pengaturan/KMS Kategori/input_Level'), level)
		}

		if(!parent.equalsIgnoreCase('')) {
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_Pilih Parent'),
					findTestObject('Object Repository/Pengaturan/KMS Kategori/input_Parent'), parent)
		}

		if(lowestCategory.equalsIgnoreCase('n')) {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_LastLevelTidak'))
		}

		if(status.equalsIgnoreCase('n')) {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_StatusTidakAktif'))
		}

		saveKMSCategory(hashMapSetKMS)
	}

	public void addKMS(HashMap hashMapSetKMS) {
		String parentName = hashMapSetKMS.get("Parent")
		String title = hashMapSetKMS.get("Judul")
		String deskripsi = hashMapSetKMS.get("Deksripsi")
		String type = hashMapSetKMS.get("Tipe")
		String image = hashMapSetKMS.get("Gambar")
		String link = hashMapSetKMS.get("Link URL")
		String tag = hashMapSetKMS.get("Tag")
		String status = hashMapSetKMS.get("Status")
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_Daftar KMS'))
		
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/div_Tambah KMS'), 5, FailureHandling.OPTIONAL)
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/div_Tambah KMS'))
		
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/input__Title'), title)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/textarea__Description'), deskripsi)
		
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/div_PilihTipeKMS'), 
												findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/input_Type'), type)
		
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/input_Link'), link)
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/div_Tulis Tag'), 
												findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/input_Tag'), tag)
		
		if(status.equalsIgnoreCase('n')) {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/KMS/div_Tidak Aktif'))
		}
		
		
	}

	public void editKMSCategory(HashMap hashMapSetKMS) {
		String oldCategory = hashMapSetKMS.get("Kategori KMS")
		String newCategory = hashMapSetKMS.get("New Kategori KMS")
		String type = hashMapSetKMS.get("Tipe")
		String level = hashMapSetKMS.get("New Level")
		String parent = hashMapSetKMS.get("Parent")
		String lastLevel = hashMapSetKMS.get("Kategori Terkecil")
		String prevLastLevel = hashMapSetKMS.get("Prev Kategori Terkecil")
		String status = hashMapSetKMS.get("Status")
		
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/button_Ubah'))
		WebUI.delay(3)
		
		WebUI.scrollToElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_BacktoTop'), 2, FailureHandling.STOP_ON_FAILURE)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/KMS Kategori/input_Category'), newCategory)
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/KMS Kategori/click_editType'),
			findTestObject('Object Repository/Pengaturan/KMS Kategori/input_Type'), type)
		
		if(!lastLevel.equalsIgnoreCase(prevLastLevel)) {
			if(lastLevel.equalsIgnoreCase('n')) {
				KeywordUtil.logInfo("Last Category: " + lastLevel)
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_editLastLevelTidak'))
			} else {
				KeywordUtil.logInfo("Last Category: " + lastLevel)
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_editLastLevelIya'))
			}
		}
		
		
		if(status.equalsIgnoreCase('n')) {
			KeywordUtil.logInfo("Status: " + status)
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_editStatusTidakAktif'))
		} else {
			KeywordUtil.logInfo("Status: " + status)
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/KMS Kategori/div_editStatusAktif'))
		}
		
		saveKMSCategory(hashMapSetKMS)
		
	}
}
