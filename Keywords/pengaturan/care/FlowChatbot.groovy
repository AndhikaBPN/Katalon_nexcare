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

import com.kms.katalon.core.util.KeywordUtil

import org.junit.internal.runners.statements.FailOnTimeout
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
			GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Master Data/btnCSAgent'))
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnTinggalkanHalaman'))
			GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Pengaturan/btnFlowChatbot'))
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
			KeywordUtil.logInfo("List Jawaban: " + listJawaban)
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddAnswer'))
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_Answer', [('indeks') : indeksAnswer]), listJawaban)
		}

		if(!linkURL.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("List URL: " + linkURL)
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddLink', [('indeks') : indeksAnswer]))
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURL', [('indeks') : indeksAnswer]), linkURL)
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
				KeywordUtil.logInfo("List Jawaban: " + answer[i])
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddAnswer'))
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_Answer', [('indeks') : indeksAnswer]), answer[i])

				if(!linkURL.equalsIgnoreCase('')) {
					List url = linkURL.split(',')
					for(int k = 0; k < url.size(); k++) {
						KeywordUtil.logInfo("List URL: " + url[i])
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/btnAddLinkPesanBuatTiket', [('indeks') : indeksAnswer]))
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURLPesanBuatTiket', [('indeks') : indeksAnswer]), url[i])
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
		String templateName = hashMapsetFlowChatbot.get('Judul Template')
		String pesanOtomatis = hashMapsetFlowChatbot.get('Pesan otomatis')
		String linkedAnswer = hashMapsetFlowChatbot.get('Terhubung dengan Jawaban')
		String tgl = hashMapsetFlowChatbot.get('Toggle')
		String listAnswer = hashMapsetFlowChatbot.get('List Jawaban')
		String linkURL = hashMapsetFlowChatbot.get('URL Link')
		String listKMS = hashMapsetFlowChatbot.get('KMS List')
		String indeks = hashMapsetFlowChatbot.get('IndeksAnswer')

		refreshPage()

		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/btnTambah'))

		if(!templateName.equalsIgnoreCase('')) {
			KeywordUtil.logInfo('Judul Template: ' + templateName)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/input_JudulTemplateQnA'), templateName)
		}

		if(!linkedAnswer.equalsIgnoreCase('')) {
			List lAnswer = linkedAnswer.split(',')
			for(int i = 0; i<lAnswer.size(); i++) {
				KeywordUtil.logInfo('Terhubungan dengan jawaban: ' + lAnswer[i])
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/div_PilihJawaban'),
						findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/dds_LinkedAnswerQnA'), lAnswer[i])
			}
		}

		if(!tgl.equalsIgnoreCase('')) {
			if(tgl.equalsIgnoreCase('f')) {
				if(WebUI.verifyElementNotChecked(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglFirstMessage'), 5, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglFirstMessage'))
				}
			}

			if(tgl.equalsIgnoreCase('b')) {
				if(WebUI.verifyElementNotChecked(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglBackOption'), 5, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglBackOption'))
				}
			}

			if(tgl.equalsIgnoreCase('s')) {
				if(WebUI.verifyElementNotChecked(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglSolution'), 5, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglSolution'))
				}

				if(!listKMS.equalsIgnoreCase('')) {
					List KMS = listKMS.split(',')
					for(int i = 0; i < KMS.size(); i++) {
						KeywordUtil.logInfo("KMS: " + KMS[i])
						GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/div_PilihDaftarKMS'),
								findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/dds_KMS'), KMS[i])
					}
				}
			}
		}

		if(!pesanOtomatis.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanOtomatis)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/textarea_PesanOtomatis'), pesanOtomatis)
		}

		if(!listAnswer.equalsIgnoreCase('')) {
			List answer = listAnswer.split(',')
			List index = indeks.split(',')
			for(int i = 0; i< answer.size(); i++) {
				KeywordUtil.logInfo("Jawaban: " + answer[i])
				GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/btnAddAnswer'))
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/input_AnswerQnA', [('indeks') : i+1]), answer[i])
				if(!linkURL.equalsIgnoreCase('')) {
					KeywordUtil.logInfo("URL: " + linkURL)
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/btn_AddLinkQnA', [('indeks') : i+1]))
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/input_Link', [('indeks') : i+1]), linkURL)
				}
			}
		}

		save(hashMapsetFlowChatbot)
	}

	public void searchQNA(HashMap hashMapsetFlowChatbot) {
		String qnaName = hashMapsetFlowChatbot.get('Judul Template')

		GeneralAction.clickElementAndSearch(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/CariQnA'), qnaName)
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/btnListQnA', [('nama_qna') : qnaName]))
	}

	public void updateSolusi(HashMap hashMapsetFlowChatbot) {
		String pesanOtomatis = hashMapsetFlowChatbot.get('Pesan otomatis')
		String listAnswer = hashMapsetFlowChatbot.get('List Jawaban')
		String linkURL = hashMapsetFlowChatbot.get('URL Link')
		String index = hashMapsetFlowChatbot.get('IndeksAnswer')

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!pesanOtomatis.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanOtomatis)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanOtomatis)
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), Keys.chord(Keys.BACK_SPACE))
		}

		if(!listAnswer.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("List Jawaban: " + listAnswer)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_Answer', [('indeks') : index]), listAnswer)
		}

		if(!linkURL.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("URL: " + linkURL)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURL', [('indeks') : index]), linkURL)
		}

		save(hashMapsetFlowChatbot)
	}

	public void updatePenutup(HashMap hashMapsetFlowChatbot) {
		String pesanOtomatis = hashMapsetFlowChatbot.get('Pesan otomatis')
		String linkedAnswer = hashMapsetFlowChatbot.get('Terhubung dengan Jawaban')

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		if(!pesanOtomatis.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanOtomatis)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), pesanOtomatis)
		}

		if(!linkedAnswer.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("List Jawaban: " + linkedAnswer)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/div_PilihJawaban'),
					findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/dds_LinkedAnswer'), linkedAnswer)
		}

		save(hashMapsetFlowChatbot)
	}

	public void updateBuatTiket(HashMap hashMapsetFlowChatbot) {
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
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_Answer', [('indeks') : indeks[i]]), answer[i])

					if(!linkURL.equalsIgnoreCase('')) {
						List url = linkURL.split(',')
						for(int k = 0; k < url.size(); k++) {
							KeywordUtil.logInfo("List URL: " + url[i])
							GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURLPesanBuatTiket', [('indeks') : indeks[i]]), url[i])
						}
					}
				}
			}
		}

		save(hashMapsetFlowChatbot)
	}

	public void editQna(HashMap hashMapsetFlowChatbot) {
		String templateName = hashMapsetFlowChatbot.get('Judul Template')
		String newTemplate = hashMapsetFlowChatbot.get('New Template')
		String pesanOtomatis = hashMapsetFlowChatbot.get('Pesan otomatis')
		String linkedAnswer = hashMapsetFlowChatbot.get('Terhubung dengan Jawaban')
		String tgl = hashMapsetFlowChatbot.get('Toggle')
		String listAnswer = hashMapsetFlowChatbot.get('List Jawaban')
		String linkURL = hashMapsetFlowChatbot.get('URL Link')
		String listKMS = hashMapsetFlowChatbot.get('KMS List')
		String indeks = hashMapsetFlowChatbot.get('IndeksAnswer')

		refreshPage()

		if(!newTemplate.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Template Pesan: " + newTemplate)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/input_JudulTemplateQnA'), newTemplate)
		}

		if(!linkedAnswer.equalsIgnoreCase('')) {
			List linked = linkedAnswer.split(',')
			for(int i = 0; i < linked.size(); i++) {
				KeywordUtil.logInfo("Terhubung dengan jawaban: " + linkedAnswer)
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/div_PilihJawaban'),
						findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/dds_LinkedAnswerQnA'), linked[i])
			}
		}

		if(!tgl.equalsIgnoreCase('')) {
			if(tgl.equalsIgnoreCase('f')) {
				if(WebUI.verifyElementNotChecked(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglFirstMessage'), 5, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglFirstMessage'))
				}
			}

			if(tgl.equalsIgnoreCase('b')) {
				if(WebUI.verifyElementNotChecked(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglBackOption'), 5, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglBackOption'))
				}
			}

			if(tgl.equalsIgnoreCase('s')) {
				if(WebUI.verifyElementNotChecked(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglSolution'), 5, FailureHandling.OPTIONAL)) {
					GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/tglSolution'))
				}

				if(!listKMS.equalsIgnoreCase('')) {
					List KMS = listKMS.split(',')
					for(int i = 0; i < KMS.size(); i++) {
						KeywordUtil.logInfo("KMS: " + KMS[i])
						GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/div_PilihDaftarKMS'),
								findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/dds_KMS'), KMS[i])
					}
				}
			}
		}

		if(!pesanOtomatis.equalsIgnoreCase('')) {
			KeywordUtil.logInfo("Pesan Otomatis: " + pesanOtomatis)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/textarea_PesanOtomatis'), pesanOtomatis)
		}

		if(!listAnswer.equalsIgnoreCase('')) {
			List answer = listAnswer.split(',')
			List index = indeks.split(',')
			for(int i = 0; i< answer.size(); i++) {
				for(int j = 0; j < index.size(); j++) {
					KeywordUtil.logInfo("Jawaban: " + answer[i])
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/input_AnswerQnA', [('indeks') : index[j]]), answer[j])
					if(!linkURL.equalsIgnoreCase('')) {
						KeywordUtil.logInfo("URL: " + linkURL)
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Flow Chatbot/QnA/input_Link', [('indeks') : index[j]]), linkURL)
					}
				}
			}
		}
		save(hashMapsetFlowChatbot)
	}

	public void viewPesanPembuka(HashMap hashMapsetFlowChatbot) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		String expectedPesanPembuka = hashMapsetFlowChatbot.get("Pesan otomatis")
		String actualPesanPembka = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
		KeywordUtil.logInfo("Expected Result: " + expectedPesanPembuka)
		KeywordUtil.logInfo("Actual Result: " + actualPesanPembka)

		boolean match = VerifyElement.verifyMatch(actualPesanPembka, expectedPesanPembuka, false)
		KeywordUtil.logInfo("Match?: " + match)
	}

	public void viewPesanSolusi(HashMap hashMapsetFlowChatbot) {
		String data = hashMapsetFlowChatbot.get("List Jawaban")
		List totalData = data.split(',')
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		String expectedPesanSolusi = hashMapsetFlowChatbot.get("Pesan otomatis")
		String actualPesanSolusi = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
		KeywordUtil.logInfo("Expected Result: " + expectedPesanSolusi)
		KeywordUtil.logInfo("Actual Result: " + actualPesanSolusi)
		boolean matchPesanOtomatis = VerifyElement.verifyMatch(actualPesanSolusi, expectedPesanSolusi, false)
		KeywordUtil.logInfo("Pesan Otomatis match: " + matchPesanOtomatis)

		for(int i=0; i<totalData.size(); i++) {
			String expectedListAnswer = totalData[i]
			String actualListAnswer = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_Answer', [('indeks') : (i+1).toString()]), 'value', FailureHandling.OPTIONAL)
			KeywordUtil.logInfo("Expected Result: " + expectedListAnswer)
			KeywordUtil.logInfo("Actual Result: " + actualListAnswer)
			boolean matchlistAnswer = VerifyElement.verifyMatch(actualListAnswer, expectedListAnswer, false)
			KeywordUtil.logInfo("List Jawaban match: " + matchlistAnswer)

			String link = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURL', [('indeks') : (i+1).toString()]), 'value')

			if(!link.equalsIgnoreCase('')) {
				String expectedLink = hashMapsetFlowChatbot.get("URL Link")
				String actualLink = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURL', [('indeks') : (i+1).toString()]), 'value')
				KeywordUtil.logInfo("Expected Result: " + expectedLink)
				KeywordUtil.logInfo("Actual Result: " + actualLink)
				boolean matchLink = VerifyElement.verifyMatch(actualLink, expectedLink, false)
				KeywordUtil.logInfo("Link match: " + matchLink)
			}
		}
	}

	public void viewPesanPenutup(HashMap hashMapsetFlowChatbot) {
		String linkedAnswer = hashMapsetFlowChatbot.get("Terhubung dengan Jawaban")

		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		List listLinkedAnswer = linkedAnswer.split(',')

		for(int i=0; i<listLinkedAnswer.size(); i++) {
			String expectedLinkedAnswer = listLinkedAnswer[i]
			String actualLinkedAnswer = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/listLinkedAnswer', [('indeks') : (i+1).toString()]))
			KeywordUtil.logInfo("Expected Result: " + expectedLinkedAnswer)
			KeywordUtil.logInfo("Actual Result: " + actualLinkedAnswer)
			boolean result = VerifyElement.verifyMatch(actualLinkedAnswer, expectedLinkedAnswer, false)
			KeywordUtil.logInfo("Linked Answer match: " + result)
		}

		String expectedPesanPenutup = hashMapsetFlowChatbot.get("Pesan otomatis")
		String actualPesanPenutup = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
		KeywordUtil.logInfo("Expected Result: " + expectedPesanPenutup)
		KeywordUtil.logInfo("Actual Result: " + actualPesanPenutup)
		boolean result1 = VerifyElement.verifyMatch(actualPesanPenutup, expectedPesanPenutup, false)
		KeywordUtil.logInfo("Pesan Penutup match: " + result1)
	}

	public void viewPesanError(HashMap hashMapsetFlowChatbot) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		String expectedPesanError = hashMapsetFlowChatbot.get("Pesan otomatis")
		String actualPesanError = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
		KeywordUtil.logInfo("Expected Result: " + expectedPesanError)
		KeywordUtil.logInfo("Actual Result: " + actualPesanError)
		boolean result = VerifyElement.verifyMatch(actualPesanError, expectedPesanError, false)
		KeywordUtil.logInfo("Pesan Error match: " + result)
	}

	public void viewPesanBuatTiket(HashMap hashMapsetFlowChatbot) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		String linkedAnswer = hashMapsetFlowChatbot.get("Terhubung dengan Jawaban")
		List listLinkedAnswer = linkedAnswer.split(',')

		for(int i=0; i<listLinkedAnswer.size(); i++) {
			String expectedLinkedAnswer = listLinkedAnswer[i]
			String actualLinkedAnswer = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/listLinkedAnswerBuatTiket', [('indeks') : (i+1).toString()]))
			KeywordUtil.logInfo("Expected Result: " + expectedLinkedAnswer)
			KeywordUtil.logInfo("Actual Result: " + actualLinkedAnswer)
			boolean result = VerifyElement.verifyMatch(actualLinkedAnswer, expectedLinkedAnswer, false)
			KeywordUtil.logInfo("Linked Answer match: " + result)
		}

		String expectedPesanBuatTiket = hashMapsetFlowChatbot.get("Pesan otomatis")
		String actualPesanBuatTiket = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
		KeywordUtil.logInfo("Expected Result: " + expectedPesanBuatTiket)
		KeywordUtil.logInfo("Actual Result: " + actualPesanBuatTiket)
		boolean result1 = VerifyElement.verifyMatch(actualPesanBuatTiket, expectedPesanBuatTiket, false)
		KeywordUtil.logInfo("Pesan Buat Tiket match: " + result1)

		String answer = hashMapsetFlowChatbot.get("List Jawaban")
		List listAnswer = answer.split(',')

		for(int i=0; i<listAnswer.size(); i++) {
			String expectedAnswer = listAnswer[i]
			String actualAnswer = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_AnswerBuatTiket', [('indeks') : (i+1).toString()]), 'value')
			KeywordUtil.logInfo("Expected Result: " + expectedAnswer)
			KeywordUtil.logInfo("Actual Result: " + actualAnswer)
			boolean result3 = VerifyElement.verifyMatch(actualAnswer, expectedAnswer, false)
			KeywordUtil.logInfo("List Answer match: " + result3)

			String link = hashMapsetFlowChatbot.get("URL Link")
			List listLink = link.split(',')
			String checkLink = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURLPesanBuatTiket', [('indeks') : (i+1).toString()]), 'value')

			if(!checkLink.equalsIgnoreCase('')) {
				for(int j=0; j<listLink.size(); j++) {
					String expectedLink = link[j]
					String actualLink = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_LinkURLPesanBuatTiket', [('indeks') : (i+1).toString()]), 'value')
					KeywordUtil.logInfo("Expected Result: " + expectedLink)
					KeywordUtil.logInfo("Actual Result: " + actualLink)
					boolean result4 = VerifyElement.verifyMatch(actualLink, expectedLink, false)
					KeywordUtil.logInfo("Link match: " + result4)
				}
			}
		}
	}

	public void viewPesanTimeout(HashMap hashMapsetFlowChatbot) {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'), 3)

		String expectedTimeoutTime = hashMapsetFlowChatbot.get("Batas Waktu Pesan")
		String actualTimoutTime = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_TimeoutParameter'), 'value')
		KeywordUtil.logInfo("Expected Result: " + expectedTimeoutTime)
		KeywordUtil.logInfo("Actual Resutl: " + actualTimoutTime)
		boolean result = VerifyElement.verifyMatch(actualTimoutTime, expectedTimeoutTime, false)
		KeywordUtil.logInfo("Timeout Time match: " + result)

		String expectedClosingTime = hashMapsetFlowChatbot.get("Durasi Penutupan")
		String actualClosingTime = WebUI.getAttribute(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_ClosingParameter'), 'value')
		KeywordUtil.logInfo("Expected Result: " + expectedClosingTime)
		KeywordUtil.logInfo("Actual Resutl: " + actualClosingTime)
		boolean result1 = VerifyElement.verifyMatch(actualClosingTime, expectedClosingTime, false)
		KeywordUtil.logInfo("Timeout Time match: " + result1)

		String expectedPesanTimeout = hashMapsetFlowChatbot.get("Pesan otomatis")
		String actualPesanTimeout = GeneralAction.getTextFromElement(findTestObject('Object Repository/Pengaturan/Flow Chatbot/Default Message/input_BotMessage'))
		KeywordUtil.logInfo("Expected Result: " + expectedPesanTimeout)
		KeywordUtil.logInfo("Actual Resutl: " + actualPesanTimeout)
		boolean result2 = VerifyElement.verifyMatch(actualPesanTimeout, expectedPesanTimeout, false)
		KeywordUtil.logInfo("Timeout Time match: " + result2)
	}
}
