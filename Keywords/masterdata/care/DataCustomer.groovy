package masterdata.care

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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

import internal.GlobalVariable
import care.katalon.GeneralAction
import care.katalon.VerifyElement
import groovy.json.StringEscapeUtils
import groovy.ui.Console

public class DataCustomer {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void refreshWeb() {
		WebUI.refresh()
		WebUI.delay(1)
	}

	public void menuDataCustomer() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Master Data'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/btnDataDistributor'))
		refreshWeb()
	}

	public void cariPelanggan(String customerName) {
		VerifyElement.verifyElementClickable(findTestObject('Object Repository/Master Data/Data Customer/CariPelanggan'))
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Customer/CariPelanggan'),
				findTestObject('Object Repository/Master Data/Data Customer/SelectPelanggan'), customerName)
	}

	public void setDataCustomer(HashMap hashMapsetDataCustomer) {
		String customerName = hashMapsetDataCustomer.get("customerName")
		String principalName = hashMapsetDataCustomer.get("principalName")
		String nsCode = hashMapsetDataCustomer.get("nsCode")
		String branchCode = hashMapsetDataCustomer.get("branchCode")
		String clientID = hashMapsetDataCustomer.get("clientID")
		String distributorCode = hashMapsetDataCustomer.get("distributorCode")
		String status = hashMapsetDataCustomer.get("status")
		String IndeksPrincipal = hashMapsetDataCustomer.get('IndeksPrincipal')
		String IndeksStatus = hashMapsetDataCustomer.get('IndeksStatus')

		WebUI.delay(2)

		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Customer/TambahKolom'))
		KeywordUtil.logInfo("PRINCIPAL NAME: " + principalName)

		if(!principalName.equalsIgnoreCase("")) {
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Customer/Pilih Principal'),
					findTestObject('Object Repository/Master Data/Data Customer/SelectPrincipal', [('IndeksPrincipal') : IndeksPrincipal]), principalName)
		}

		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/KodePelanggan'), nsCode)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/KodeCabang'), branchCode)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/ClientID'), clientID)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/KodeDistributor'), distributorCode)

		if(!status.equalsIgnoreCase("")) {
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Customer/Status'),
					findTestObject('Object Repository/Master Data/Data Customer/SelectStatus', [('IndeksStatus') : IndeksStatus]), status )
		}

		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Customer/Tambah'))
	}

	public void viewData(String customerName) {
		WebUI.delay(3)
		WebUI.waitForElementClickable(findTestObject('Object Repository/Master Data/Data Customer/CariPelanggan'), 3, FailureHandling.STOP_ON_FAILURE)
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Customer/CariPelanggan'),
				findTestObject('Object Repository/Master Data/Data Customer/SelectPelanggan'), customerName)
	}

	public void editCustomer(String principalName, String branchCode) {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Customer/editDataCustomer', [('principalName') : principalName, ('branchCode') : branchCode]))
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
	}

	public void FindListTable(String principalName, String branchCode) {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Customer/ListDataCustomer', [('principalName') : principalName, ('branchCode') : branchCode]))
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
	}

	public void editDocumentDetail(List listHashMapEditDetail) {
		for(int i = 0; i < listHashMapEditDetail.size(); i++) {
			HashMap hashMapEditDataCustomer = listHashMapEditDetail.get(i)
			String principalName = hashMapEditDataCustomer.get('principalName')
			String nsCode = hashMapEditDataCustomer.get("nsCode")
			String branchCode = hashMapEditDataCustomer.get("branchCode")
			String clientID = hashMapEditDataCustomer.get("clientID")
			String distributorCode = hashMapEditDataCustomer.get("distributorCode")
			String status = hashMapEditDataCustomer.get("status")

			editCustomer(principalName, branchCode)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/EditKodePelanggan', [('indexRow') : (i+1).toString()]), nsCode)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/EditKodeCabang', [('indexRow') : (i+1).toString()]), branchCode)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/EditClientID' , [('indexRow') : (i+1).toString()]), clientID)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Customer/EditKodeDistributor', [('indexRow') : (i+1).toString()]), distributorCode)
			GeneralAction.clickElementSelectByLabel(findTestObject('Object Repository/Master Data/Data Customer/EditStatusValue', [('indexRow') : (i+1).toString()]), status, false)
		}
	}

	public void deleteCustomer(List listHashMapDelete) {
		for(int i = 0; i < listHashMapDelete.size(); i++) {
			HashMap hashMapDeleteDataCustomer = listHashMapDelete.get(i)
			String principalName = hashMapDeleteDataCustomer.get("principalName")
			String branchCode = hashMapDeleteDataCustomer.get("branchCode")
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Customer/deleteDataCustomer', [('principalName') : principalName, ('branchCode') : branchCode]))
			WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
		}
	}

	public void verifyViewDataCustomer(List listHashMapVerify) {
		for(int i = 0; i < listHashMapVerify.size(); i++) {
			HashMap hashMapEditDataCustomer = listHashMapVerify.get(i)
			KeywordUtil.logInfo("Principal Name : "+hashMapEditDataCustomer.get("principalName"))
			KeywordUtil.logInfo("branchCode : "+hashMapEditDataCustomer.get("branchCode"))
			KeywordUtil.logInfo("clientID : "+hashMapEditDataCustomer.get("clientID"))
			KeywordUtil.logInfo("distributorCode : "+hashMapEditDataCustomer.get("distributorCode"))
			KeywordUtil.logInfo("status : "+hashMapEditDataCustomer.get("status"))

			String principalName = hashMapEditDataCustomer.get("principalName")
			String ActualprincipalName = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Customer/VerifyNamaPrincipal', [('indexRow') : (i+1).toString(), ('indeks') : '1']))
			VerifyElement.verifyMatch(ActualprincipalName, principalName, false)

			String nsCode = hashMapEditDataCustomer.get("nsCode")
			String ActualnsCode = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Customer/VerifyNamaPrincipal', [('indexRow') : (i+1).toString(), ('indeks') : '2']))
			VerifyElement.verifyMatch(ActualnsCode, nsCode, false)

			String branchCode = hashMapEditDataCustomer.get("branchCode")
			String ActualbranchCode = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Customer/VerifyNamaPrincipal', [('indexRow') : (i+1).toString(), ('indeks') : '3']))
			VerifyElement.verifyMatch(ActualbranchCode, branchCode, false)

			String clientID = hashMapEditDataCustomer.get("clientID")
			String ActualclientID = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Customer/VerifyNamaPrincipal', [('indexRow') : (i+1).toString(), ('indeks') : '4']))
			VerifyElement.verifyMatch(ActualclientID, clientID, false)

			String distributorCode = hashMapEditDataCustomer.get("distributorCode")
			String ActualdistributorCode = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Customer/VerifyNamaPrincipal', [('indexRow') : (i+1).toString(), ('indeks') : '5']))
			VerifyElement.verifyMatch(ActualdistributorCode, distributorCode, false)

			String status = hashMapEditDataCustomer.get("status")
			String Actualstatus = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Customer/VerifyNamaPrincipal', [('indexRow') : (i+1).toString(), ('indeks') : '6']))
			VerifyElement.verifyMatch(Actualstatus, status, false)
		}
	}

	public void saveDocument() {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Customer/Simpan'))
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
	}

	public void verifyMsg(String msgValidation, String TD, String category) {
		WebUI.delay(1)
		String msg = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Customer/msgValidation'))
		KeywordUtil.logInfo("MSG =  " + msg)

		if(msgValidation.equalsIgnoreCase(msg)) {
			KeywordUtil.logInfo("Message: " + TD + " - " + msg)
		}
		
		if(category.equalsIgnoreCase('negative')) {
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Customer/button_OK'))
		}

	}
}
