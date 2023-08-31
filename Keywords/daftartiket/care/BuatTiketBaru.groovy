package daftartiket.care

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
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import care.katalon.HandleWebDriver
import internal.GlobalVariable

public class BuatTiketBaru {

	GeneralAction GeneralAction = new GeneralAction()

	public void menuCreateTicket() {
		GeneralAction.refreshPage()
		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/DaftarTiket'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Others/Buat Tiket Baru'))
	}

	def setCreateNewTicket(HashMap hashMapDataTicket) {
		String customer_id = hashMapDataTicket.get("customer_id")
		String pic_name = hashMapDataTicket.get("pic_name")
		String phone = hashMapDataTicket.get("phone")
		String principal_id = hashMapDataTicket.get("principal_id")
		String ticket_from = hashMapDataTicket.get("ticket_from")
		String pic_email = hashMapDataTicket.get("pic_email")
		String ns_code = hashMapDataTicket.get("ns_code")
		String branch_id = hashMapDataTicket.get("branch_id")
		String issue_title = hashMapDataTicket.get("issue_title")
		String issue_desc = hashMapDataTicket.get("issue_desc")
		String complaint = hashMapDataTicket.get("complaint")
		String complaint_category = hashMapDataTicket.get("complaint_category")
		String complaint_sub_id = hashMapDataTicket.get("complaint_sub_id")
		String create_contact = hashMapDataTicket.get("create_contact")
		String note = hashMapDataTicket.get("note")
		String contact_id = hashMapDataTicket.get("contact_id")
		String pathAttachment = hashMapDataTicket.get("pathAttachment")


		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/Nama Pelanggan'))
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/searchNamaPelanggan'), customer_id)
		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/SelectPelanggan', [('customer_id') : customer_id]))

		if(create_contact.equalsIgnoreCase("Y")){
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/PIC'))
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/searchPIC'), pic_name)
			WebUI.delay(2)
			if(WebUI.verifyElementClickable(findTestObject('Object Repository/Buat Tiket/MasukanPIC'), FailureHandling.OPTIONAL)) {
				GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/MasukanPIC'))
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/Telepon'), phone)
				if(!pic_email.equalsIgnoreCase("")) {
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/Alamat Email'), pic_email)
				}
			}
		}else {
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/PIC'))
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/searchPIC'), pic_name)
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/selectPIC', [('pic_name') : pic_name]))
		}

		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/Principal'))
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/searchPrincipal'), principal_id)
		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/SelectPrincipal', [('principal_id') : principal_id]))

		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/Channel Tiket'))
		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/SelectChannel', [('ticket_from') : ticket_from]))

		if(!ns_code.equalsIgnoreCase("")) {
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/Kode NS'))
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/SelectNSCode', [('ns_code') : ns_code]))
		}

		if(!branch_id.equalsIgnoreCase("")) {
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/Kode Cabang'))
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/SelectBranchCode', [('branch_id') : branch_id]))
		}

		GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/Judul Keluhan'), issue_title)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/Deskripsi Keluhan'), issue_desc)

		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Buat Tiket/Produk'),
				findTestObject('Object Repository/Buat Tiket/SelectProduct'), complaint)
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Buat Tiket/Modul'),
				findTestObject('Object Repository/Buat Tiket/SelectModul'), complaint_category)
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Buat Tiket/Kategori Keluhan'),
				findTestObject('Object Repository/Buat Tiket/SelectKategoriKeluhan'), complaint_sub_id)

		if(!note.equalsIgnoreCase("")) {
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Buat Tiket/Note'), note)
		}

		if(!pathAttachment.equalsIgnoreCase("")) {
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/Lampiran'))
			pathAttachment = pathAttachment.replace(',', '\n')
			KeywordUtil.logInfo(pathAttachment)
			WebUI.uploadFile(findTestObject('Object Repository/Buat Tiket/AttachFile'), pathAttachment)
			GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/SaveAttachment'))
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/Buat Tiket'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/Buat Tiket/verifySuccess'), 120)
		String tiketnumber = GeneralAction.getTextFromElement(findTestObject('Object Repository/Buat Tiket/get Tiket Number'))
		String tiket = tiketnumber.split("Berhasil membuat tiket baru ")[1].replace('!', '')
		GeneralAction.clickElement(findTestObject('Object Repository/Buat Tiket/KonfrimasiBuatTiket'))

		return tiket
	}

	public void getTicketNumber() {
		String tiketnumber = GeneralAction.getTextFromElement(findTestObject('Object Repository/Buat Tiket/get Tiket Number'))
		List arrTiket = tiketnumber.split("Berhasil membuat tiket baru ")
		KeywordUtil.logInfo(arrTiket.size().toString())
		for(int i = 0; i < arrTiket.size(); i++) {
			KeywordUtil.logInfo(arrTiket[i])
			String finalTiket = arrTiket[i].toString().replace('!', '')
			KeywordUtil.logInfo(finalTiket)
		}
	}

	public void editOneSheet(String path, String sheetName, List value, int indexColumn) {
		File fileExcel = new File(GlobalVariable.pathDataFiles + path)
		FileInputStream file = new FileInputStream(fileExcel)
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)
		for(int i = 0; i < value.size(); i++) {
			try {
				sheet.getRow((i+1)).createCell(indexColumn).setCellValue(value[i])
			} catch(Exception e) {
				sheet.createRow((i+1))
				sheet.getRow((i+1)).createCell(indexColumn).setCellValue(value[i])
			}
			KeywordUtil.logInfo("ROW = " + (i+1) + " VALUE = " + value)
		}
		file.close()
		FileOutputStream outFile = new FileOutputStream(fileExcel)
		workbook.write(outFile)
		outFile.close()
		workbook.close()
	}
}
