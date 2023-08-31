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

import care.katalon.HandleWebDriver
import internal.GlobalVariable
import care.katalon.VerifyElement

public class DetailTiket {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void cariTiket (String ticket_number) {
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Home Page(General)/Others/Search'),
				findTestObject('Object Repository/Home Page(General)/Others/Search'), ticket_number)
	}

	public void detailTiket(String ticket_number) {
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Lihat Detail', [('ticket_number') : ticket_number]))
	}

	public void backToList() {
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Others/Daftar tiket'))
	}

	public void tambahNote(String note) {
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Tambah Note'))
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute Tambah Note/Masukan Note'), note)
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute Tambah Note/Simpan'))
	}

	public void editNoBug(HashMap hashMapEditNoBug) {
		String ticket_number = hashMapEditNoBug.get("ticket_number")
		String no_bug = hashMapEditNoBug.get("no_bug")
		String status = hashMapEditNoBug.get("status")

		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/NoBug'))
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute No Bug/NoBug'), no_bug)
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute No Bug/ubah nomor'))
	}

	public void editUrlLink(HashMap hashMapEditUrlLink) {
		String ticket_number = hashMapEditUrlLink.get("ticket_number")
		String urlLink = hashMapEditUrlLink.get("urlLink")

		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Url Link'))
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute URL Link/Masukkan URL Link'), urlLink)
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute URL Link/Ubah URL'))
	}

	public void editVersiRilis(HashMap hashMapEditVersiRilis) {
		String ticket_number = hashMapEditVersiRilis.get("ticket_number")
		String versiRilis = hashMapEditVersiRilis.get("versiRilis")

		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Versi Rilis'))
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute Versi Rilis/Masukan Versi Rilis'), versiRilis)
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute Versi Rilis/Ubah Versi'))
	}

	public void editKeluhan(HashMap hashMapEditKeluhan) {
		String judulKeluhan = hashMapEditKeluhan.get("judulKeluhan")
		String deskripsiKeluhan = hashMapEditKeluhan.get("deskripsiKeluhan")

		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Keluhan'))
		if(!judulKeluhan.equalsIgnoreCase("")) {
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute Keluhan/Judul Keluhan'), judulKeluhan)
		}
		if(!deskripsiKeluhan.equalsIgnoreCase("")) {
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute Keluhan/Deskripsi Keluhan'), deskripsiKeluhan)
		}
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute Keluhan/Simpan'))
	}

	public void tambahKontak(HashMap hashMapTambahKontak) {
		String pic_name = hashMapTambahKontak.get('pic_name')
		String phone_number = hashMapTambahKontak.get('phone_number')
		String email = hashMapTambahKontak.get('email')

		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Kontak Pelanggan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute Kontak Pelanggan/Tambah Kontak'))
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute Kontak Pelanggan/Nama Kontak'), pic_name)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute Kontak Pelanggan/Telepon'), phone_number)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Detail Tiket(General)/Attribute Kontak Pelanggan/Email'), email)
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute Kontak Pelanggan/Simpan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Detail Tiket(General)/Attribute Kontak Pelanggan/KonfrimasiSimpan'))
	}
}
