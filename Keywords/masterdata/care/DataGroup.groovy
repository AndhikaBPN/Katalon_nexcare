package masterdata.care

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

public class DataGroup {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void menuDataGroup() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Master Data'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Master Data/btnDataGroup'))
	}

	public void addDataGroup() {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Master Data/Data Group/Tambah'), 3)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/Tambah'))
		refreshPage()
	}

	public void addDataScope() {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/DataCangkupan'), 3)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/DataCangkupan'))
	}

	public void DetailViewDataScope() {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/DataScopeView'), 3)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/DataScopeView'))
	}

	public void findListDataGroup(String groupID) {
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/ListDataGroup' , [('groupID') : groupID]))
	}

	public void searchDataGroup(String groupID) {
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/SearchDataGroup'), groupID)
		WebUI.sendKeys(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/SearchDataGroup'), Keys.chord(Keys.ENTER))
	}

	public void refreshPage() {
		WebUI.refresh()
	}

	public void setDataGroup(HashMap hashMapsetDataGroup) {
		String groupID = hashMapsetDataGroup.get("groupID") // katalonlima
		String deskripsi = hashMapsetDataGroup.get("deskripsi") // Penambahan Data Group Oleh Katalon 05.
		String customerName = hashMapsetDataGroup.get("customerName") // PT CAHAYA AGUNG CEMERLANG GROBOGAN
		String principalName = hashMapsetDataGroup.get("principalName") // Nestle

		WebUI.waitForElementClickable(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/GrupID'), 3)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/GrupID'), groupID)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/Deskripsi'), deskripsi)

		addDataScope()

		if(!customerName.equalsIgnoreCase("")) {
			if(customerName.equalsIgnoreCase("All")) {
				WebUI.verifyElementNotChecked(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPelanggan'), 3, FailureHandling.STOP_ON_FAILURE)
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPelanggan'))
			}else {
				List allCustomerName = customerName.split(',')
				for(int i = 0; i < allCustomerName.size(); i++) {
					GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/NamaPelanggan'),
							findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/SelectPelanggan'), allCustomerName[i])
				}
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/CloseDropDown'))
			}
		}

		if(!principalName.equalsIgnoreCase("")) {
			if(principalName.equalsIgnoreCase("ALL")) {
				WebUI.verifyElementNotChecked(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPrincipal'), 3, FailureHandling.STOP_ON_FAILURE)
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPrincipal'))
			}else {
				List allPrincipal = principalName.split(',')
				for(int i = 0; i < allPrincipal.size(); i++) {
					GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/NamaPrincipal'),
							findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/SelectPrincipal'), allPrincipal[i])
				}
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/CloseDropDown'))
			}
		}
	}

	public void listEditDataGroup (String groupID) {
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/View' , [('groupID') : groupID]))
	}

	public void closeViewDetail () {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/Tutup'))
		WebUI.delay(2)
	}

	public void viewDetail (String groupID) {
		searchDataGroup(groupID)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/pathdetailDataGroup'))
	}

	public void viewEdit(String groupID) {
		searchDataGroup(groupID)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/btnEditDataGroup'))
		refreshPage()
	}

	public void editDataScope(String groupID) {
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/Edit', , [('groupID') : groupID]))
	}

	public void saveDataGroup(String td) {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/Simpan'))
		verifySaveDataGroup(td)
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
		WebUI.delay(3)
	}

	public void deleteDataGroup(String groupID) {
		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/Delete' , [('groupID') : groupID]))
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/KonfrimDeleteYes'))
		WebUI.delay(2)
	}

	public void editDetail(HashMap hashMapEditDataGroup) {
		String deskripsi = hashMapEditDataGroup.get("deskripsi")
		String customerName = hashMapEditDataGroup.get("customerName")
		String principalName = hashMapEditDataGroup.get("principalName")

		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/Description'), deskripsi)

		addDataScope()

		if(!customerName.equalsIgnoreCase("")) {
			if(customerName.equalsIgnoreCase("All")) {
				try {
					WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPelanggan'), 3)
					if(!webElement.isSelected()) {
						KeywordUtil.logInfo('Message: TERAPKAN SEMUA TIDAK TERCENTANG')
						GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPelanggan'))
					}
				}catch(Exception e) {
					KeywordUtil.markFailedAndStop(e.printStackTrace())
				}
			}else {
				try {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/navbar'))
					WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPelanggan'), 3)
					if(webElement.isSelected()) {
						KeywordUtil.logInfo('Message: TERAPKAN SEMUA TERCENTANG')
						GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPelanggan'))
					}
				}catch(Exception e) {
					KeywordUtil.markFailedAndStop(e.printStackTrace())
				}
				GeneralAction.deleteValue(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/ClearPelanggan'))
				List allCustomerName = customerName.split(',')
				for(int i = 0; i < allCustomerName.size(); i++) {
					KeywordUtil.logInfo('DISTRIBUTOR NAME: ' + allCustomerName[i])
					GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/NamaPelanggan'),
							findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/SelectPelanggan'), allCustomerName[i])
				}
				//				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/navbar'))
			}
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/navbar'))
			GeneralAction.deleteValue(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/ClearPelanggan'))
		}

		if(!principalName.equalsIgnoreCase("")) {
			if(principalName.equalsIgnoreCase("ALL")) {
				try {
					WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPrincipal'), 3)
					if(!webElement.isSelected()) {
						GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPrincipal'))
					}
				}catch(Exception e) {
					KeywordUtil.markFailedAndStop(e.printStackTrace())
				}
			}else if(principalName.equalsIgnoreCase("")) {
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/CloseSelection'))
			}else {
				try {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/navbar'))
					WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPrincipal'), 3)
					if(webElement.isSelected()) {
						GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPrincipal'))
					}
				}catch(Exception e) {
					KeywordUtil.markFailedAndStop(e.printStackTrace())
				}
				GeneralAction.deleteValue(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/ClearPrincipal'))
				List allPrincipal = principalName.split(',')
				for(int i = 0; i < allPrincipal.size(); i++) {
					KeywordUtil.logInfo('DISTRIBUTOR NAME: ' + allPrincipal[i])
					GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/NamaPrincipal'),
							findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/SelectPrincipal'), allPrincipal[i])
				}
				//				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/navbar'))
			}
		} else {
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/navbar'))
			GeneralAction.deleteValue(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/ClearPrincipal'))
		}
	}

	public void verifyDataGroup(HashMap hashMapVerifyDataGroup) {
		KeywordUtil.logInfo("Grup ID : "+hashMapVerifyDataGroup.get("groupID"))
		KeywordUtil.logInfo("Deskripsi : "+hashMapVerifyDataGroup.get("deskripsi"))
		KeywordUtil.logInfo("Customer Name : "+hashMapVerifyDataGroup.get("customerName"))
		KeywordUtil.logInfo("Principal Name : "+hashMapVerifyDataGroup.get("principalName"))

		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond)

		String groupID = hashMapVerifyDataGroup.get("groupID")
		String ActualgroupID = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/VerifyDataGroupID'), 'value')
		VerifyElement.verifyMatch(ActualgroupID, groupID, false)

		String deskripsi = hashMapVerifyDataGroup.get("deskripsi")
		String Actualdeskripsi = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/VerifyDeskripsi'))
		VerifyElement.verifyMatch(Actualdeskripsi, deskripsi, false)

		DetailViewDataScope()

		String customerName = hashMapVerifyDataGroup.get("customerName")
		List allCustomerName = customerName.split(',')
		for(int i = 0; i < allCustomerName.size(); i++) {
			if (customerName.equalsIgnoreCase("All")) {
				boolean chkAll = WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPelanggan'), 3)
				KeywordUtil.logInfo("ELEMENT CHECKED = " + chkAll)
			}else {
				String ActualcustomerName = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/VerifyNamaPelanggan', [('indexRow') : (i+1).toString()]))
				KeywordUtil.logInfo('NAMA DISTRIBUTOR = ' + ActualcustomerName.toString())
				VerifyElement.verifyMatch(ActualcustomerName, allCustomerName[i], false)
			}
		}

		String principalName = hashMapVerifyDataGroup.get("principalName")
		List allprincipalName = principalName.split(',')
		for(int i = 0; i < allprincipalName.size(); i++) {
			if (principalName.equalsIgnoreCase("All")) {
				boolean chkAll = WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/TerapkanDiSemuaNamaPrincipal'), 3)
				KeywordUtil.logInfo("ELEMENT CHECKED = " + chkAll)
			}else {
				String ActualprincipalName = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/VerifyNamaPrincipal', [('indexRow') : (i+1).toString()]))
				KeywordUtil.logInfo('NAMA PRINCIPAL = ' + ActualprincipalName.toString())
				VerifyElement.verifyMatch(ActualprincipalName, allprincipalName[i], false)
			}
		}
	}

	public void verifySaveDataGroup(String tD) {
		String notif = WebUI.getText(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/VerifySaveNotif'))
		if (notif.equalsIgnoreCase("Data dengan group_id ini sudah pernah disimpan") || notif.equalsIgnoreCase("Data Scope Distributor dan Principal tidak boleh kosong!") || notif.equalsIgnoreCase("Kolom description belum diisi. Silakan cek kembali")) {
			KeywordUtil.logInfo('Message: ' + tD + ' ' + notif)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Data Group/TambahEditDetail/button_OK'))
			GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Master Data/btnDataGroup'))
		}
	}
}
