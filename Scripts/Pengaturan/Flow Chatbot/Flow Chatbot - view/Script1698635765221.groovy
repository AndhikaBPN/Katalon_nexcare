import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys

import pengaturan.care.FlowChatbot
import care.katalon.GeneralAction
import care.katalon.HandleWebDriver
import care.katalon.HandleTestData

HandleWebDriver handleWebDriver = new HandleWebDriver()
handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

GeneralAction generalAction = new GeneralAction()
HandleTestData handleTestData = new HandleTestData()
FlowChatbot flowChatbot = new FlowChatbot()

generalAction.login("")
flowChatbot.menuFlowChatbot()

String excelLocation = 'Pengaturan\\Flow Chatbot\\Flow Chatbot - view.xlsx'
String sheetName = 'flowchatbot -view'
String sheet = 'Sheet1'

List<HashMap> listHashMapFlowChatbot = handleTestData.readTestData(excelLocation, sheet, true)

for(int i = 0; i<listHashMapFlowChatbot.size(); i++) {
	HashMap getHashMapFlowCHatbot = listHashMapFlowChatbot.get(i)
	String TD = getHashMapFlowCHatbot.get('TD')
	String defMessage = getHashMapFlowCHatbot.get('default-message')
	KeywordUtil.logInfo("TD: " + TD)
	KeywordUtil.logInfo("DEFAULT MESAGE: " + defMessage)
	
	try {
//		if(defMessage.equalsIgnoreCase('pesanpembuka')) {
//			flowChatbot.openPesanPembuka()
//			flowChatbot.viewPesanPembuka(getHashMapFlowCHatbot)
//		}
//		
//		if(defMessage.equalsIgnoreCase('pesansolusi')) {
//			flowChatbot.openPesanSolusi()
//			flowChatbot.viewPesanSolusi(getHashMapFlowCHatbot)
//		}
//		
//		if(defMessage.equalsIgnoreCase('pesanpenutup')) {
//			flowChatbot.openPesanPenutup()
//			flowChatbot.viewPesanPenutup(getHashMapFlowCHatbot)
//		}
//		
//		if(defMessage.equalsIgnoreCase('pesanerror')) {
//			flowChatbot.openPesanError()
//			flowChatbot.viewPesanError(getHashMapFlowCHatbot)
//		}
//		
//		if(defMessage.equalsIgnoreCase('pesanbuattiket')) {
//			flowChatbot.openPesanBuatTiket()
//			flowChatbot.viewPesanBuatTiket(getHashMapFlowCHatbot)
//		}
//		
		if(defMessage.equalsIgnoreCase('pesantimeout')) {
			flowChatbot.openPesanTimeout()
			flowChatbot.viewPesanTimeout(getHashMapFlowCHatbot)
		}
//		
//		if(defMessage.equalsIgnoreCase('qna')) {
//			flowChatbot.searchQNA(getHashMapFlowCHatbot)
//			flowChatbot.editQna(getHashMapFlowCHatbot)
//		}
		
	} catch (Exception e) {
		
	}
}

generalAction.logoutAndCloseBrowser()