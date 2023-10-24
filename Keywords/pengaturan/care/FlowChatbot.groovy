package pengaturan.care

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.ConcurrentHashMap.KeySetView

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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import internal.GlobalVariable
import care.katalon.GeneralAction
import care.katalon.VerifyElement
import groovy.json.StringEscapeUtils

public class FlowChatbot {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void refreshPage() {
		WebUI.refresh()
	}

	public void save(HashMap hashMapsetFlowChatbot) {
		String kategori = hashMapsetFlowChatbot.get('kategori')

		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Tombol/button_Simpan'))

		if(kategori.equalsIgnoreCase('negative')) {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Tombol/button_OK'))
		}

		WebUI.delay(3)	
	}

	public void menuFlowChatbot() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Pengaturan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Pengaturan/btnFlowChatbot'))
	}

	public void openPesanPembuka() {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnPesanPembuka'))
		refreshPage()
	}

	public void openPesanSolusi() {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnPesanSolusi'))
		refreshPage()
	}

	public void openPesanPenutup() {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnPesanPenutup'))
		refreshPage()
	}

	public void openPesanError() {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnPesanEror'))
		refreshPage()
	}

	public void openPesanBuatTiket() {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnPesanBuatTiket'))
		refreshPage()
	}

	public void openPesanTimeout() {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnPesanTimeout'))
		refreshPage()
	}

	public void setPesanPembuka(HashMap hashMapsetFlowChatbot) {
		String pesanPembuka = hashMapsetFlowChatbot.get("Pesan otomatis")

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!pesanPembuka.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanPembuka)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanPembuka)
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.BACK_SPACE))
		}

		save(hashMapsetFlowChatbot)
	}

	public void setPesanSolusi(HashMap hashMapsetFlowChatbot) {
		String pesanSolusi = hashMapsetFlowChatbot.get('Pesan otomatis')
		String listJawaban = hashMapsetFlowChatbot.get('List Jawaban')
		String linkURL = hashMapsetFlowChatbot.get('URL Link')
		String indeksAnswer = hashMapsetFlowChatbot.get('IndeksAnswer')

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!pesanSolusi.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanSolusi)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanSolusi)
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.BACK_SPACE))
		}

		if(!listJawaban.equalsIgnoreCase('')) {
//			for(int i = 0; i < listJawaban.size(); i++) {
//				for(int j = 0; j < indeksAnswer.size(); j++) {
					KeywordUtil.logInfo("List Jawaban: " + listJawaban)
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddAnswer'))
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_Answer', [('indeks') : indeksAnswer]), listJawaban)
//				}
//			}
		}
		
		if(!linkURL.equalsIgnoreCase('')) {
//			for(int i = 0; i < linkURL.size(); i++) {
//				for(int j = 0; j < indeksAnswer.size(); j++) {
					KeywordUtil.logInfo("List URL: " + linkURL)
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddLink', [('indeks') : indeksAnswer]))
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURL', [('indeks') : indeksAnswer]), linkURL)
//				}
//			}
		}
		save(hashMapsetFlowChatbot)
	}

	public void setPesanPenutup(HashMap hashMapsetFlowChatbot) {
		String pesanPenutup = hashMapsetFlowChatbot.get('Pesan otomatis')
		String linkedAnswer = hashMapsetFlowChatbot.get('Terhubung dengan Jawaban')

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!linkedAnswer.equalsIgnoreCase('')) {
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/div_PilihJawaban'), 
													findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/dds_LinkedAnswer'), linkedAnswer)
		}
		
		if(!pesanPenutup.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanPenutup)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanPenutup)
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.BACK_SPACE))
		}
		
		save(hashMapsetFlowChatbot)
	}

	public void setPesanError(HashMap hashMapsetFlowChatbot) {
		String pesanError = hashMapsetFlowChatbot.get('Pesan otomatis')

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!pesanError.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanError)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanError)
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.BACK_SPACE))
		}

		save(hashMapsetFlowChatbot)
	}

	public void setPesanBuatTiket(HashMap hashMapsetFlowChatbot) {
		String pesanBuatTiket = hashMapsetFlowChatbot.get('Pesan otomatis')
		String listJawaban = hashMapsetFlowChatbot.get('List Jawaban')
		String linkedAnswer = hashMapsetFlowChatbot.get('Terhubung dengan Jawaban')
		String linkURL = hashMapsetFlowChatbot.get('URL Link')
		String indeksAnswer = hashMapsetFlowChatbot.get('IndeksAnswer')

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!linkedAnswer.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Terhubung dengan jawaban: " + linkedAnswer)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/div_PilihJawaban'),
													findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/dds_LinkedAnswer'), linkedAnswer)
		}
		
		if(!pesanBuatTiket.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanBuatTiket)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanBuatTiket)
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.BACK_SPACE))
		}
		
		if(!listJawaban.equalsIgnoreCase('')) {
			List answer = listJawaban.split(',')
			for(int i = 0; i < answer.size(); i++) {
				List indeks = indeksAnswer.split(',')
				for(int j = 0; j < indeks.size(); j++) {
					KeywordUtil.logInfo("List Jawaban: " + answer[i])
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddAnswer'))
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_Answer', [('indeks') : indeks[i]]), answer[i])

					if(!linkURL.equalsIgnoreCase('')) {
						List url = linkURL.split(',')
						for(int k = 0; k < url.size(); k++) {
							KeywordUtil.logInfo("List URL: " + url[i])
							GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddLinkPesanBuatTiket', [('indeks') : indeks[i]]))
							GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURLPesanBuatTiket', [('indeks') : indeks[i]]), url[i])
						}
					}
				}
			}
		}
		
		save(hashMapsetFlowChatbot)
	}

	public void setPesanTimeout(HashMap hashMapsetFlowChatbot) {
		String pesanTimeout = hashMapsetFlowChatbot.get('Pesan otomatis')
		String timeout = hashMapsetFlowChatbot.get('Batas Waktu Pesan')
		String penutup = hashMapsetFlowChatbot.get('Durasi Penutupan')

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!pesanTimeout.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanTimeout)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanTimeout)
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.BACK_SPACE))
		}

		if(!timeout.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Durasi Timeout: " + timeout)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_TimeoutParameter'), timeout)
		}

		if(!penutup.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Durasi Pesan Penutup: " + penutup)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_ClosingParameter'), penutup)
		}

		save(hashMapsetFlowChatbot)
	}

	public void addQNA(HashMap hashMapsetFlowChatbot) {
	}

	public void searchQNA(HashMap hashMapsetFlowChatbot) {

		GeneralAction.clickElementAndSearch(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/CariQnA'), qnaName)
	}
}
