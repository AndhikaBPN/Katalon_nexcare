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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import internal.GlobalVariable
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation
import care.katalon.GeneralAction
import care.katalon.VerifyElement

public class Pengguna {

	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void menuPengguna() {
		GeneralAction.clickElement(findTestObject('Object Repository/Sidebar/Sidebar Master Data'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/btnUser'))
	}

	public void addUser() {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Master Data/Pengguna/Tambah'), 3)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Tambah'))
		refreshPage()
	}

	public void refreshPage() {
		WebUI.delay(3)
		WebUI.refresh()
	}

	public void findUser(HashMap hashMapFindUser) {
		String username = hashMapFindUser.get("username")
		KeywordUtil.logInfo('Username: ' + username)
		GeneralAction.clickElementAndSearch(findTestObject('Object Repository/Master Data/Pengguna/searchUser'), username)
	}

	public void viewUser() {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/View'))
	}

	public void editUser() {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Edit'))
		refreshPage()
	}

	public void deleteUser() {
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Delete'))
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/KonfrimasiDelete'))
	}

	public void verifyData(HashMap hashMapVerifyData) {
		String Action = hashMapVerifyData.get("Action")
		String Kategori = hashMapVerifyData.get("Kategori")
		String email = hashMapVerifyData.get("email")
		String leader = hashMapVerifyData.get("leader")
		String isLeader = hashMapVerifyData.get("isLeader")
		String noTelp = hashMapVerifyData.get("noTelp")
		String isCSO = hashMapVerifyData.get("isCSO")
		String csLevel = hashMapVerifyData.get("csLevel")
		String csSpecialisasi = hashMapVerifyData.get("csSpecialisasi")
		String NIK = hashMapVerifyData.get("NIK")
		String title = hashMapVerifyData.get("title")
		String firstName = hashMapVerifyData.get("firstName")
		String lastName = hashMapVerifyData.get("lastName")
		String gender = hashMapVerifyData.get("gender")
		String username = hashMapVerifyData.get("username")
		String password = hashMapVerifyData.get("password")
		String listDist = hashMapVerifyData.get("listDist")
		String confrimPassword = hashMapVerifyData.get("confrimPassword")
		String role = hashMapVerifyData.get("role")
		String dataGroup = hashMapVerifyData.get("dataGroup")
		String status = hashMapVerifyData.get("status")
		String verifystatus = hashMapVerifyData.get("verifstatus")

		WebUI.waitForElementPresent(findTestObject('Object Repository/Master Data/Pengguna/idPengguna'), 3, FailureHandling.STOP_ON_FAILURE)

		if(Action.equalsIgnoreCase("Add")) {
			String ActualKategori = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyKategori'))
			KeywordUtil.logInfo('Actual Kategori: ' + ActualKategori)
			KeywordUtil.logInfo('Expected Kategori: ' + Kategori)
			VerifyElement.verifyMatch(ActualKategori, Kategori, false)

			if(isCSO.equalsIgnoreCase("Y")) {
				WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'), 3)
			}else {
				WebUI.verifyElementNotChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'), 3)
			}

			String Actualemail = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyEmail'), 'value')
			KeywordUtil.logInfo('Actual Email: ' + Actualemail)
			KeywordUtil.logInfo('Expected Email: ' + email)
			VerifyElement.verifyMatch(Actualemail, email, false)

			if(leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")) {
				WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
			}else if(!leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")){
				WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
				String Actualleader = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyLead'))
				KeywordUtil.logInfo('Actual Leader: ' + Actualleader)
				KeywordUtil.logInfo('Expected Leader: ' + leader)
				VerifyElement.verifyMatch(Actualleader, leader, false)
			}else if(isLeader.equalsIgnoreCase("N")) {
				WebUI.verifyElementNotChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
				String Actualleader = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyLead'))
				KeywordUtil.logInfo('Actual Leader: ' + Actualleader)
				KeywordUtil.logInfo('Expected Leader: ' + leader)
				VerifyElement.verifyMatch(Actualleader, leader, false)
			}else{
				String Actualleader = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyLead'))
				KeywordUtil.logInfo('Actual Leader: ' + Actualleader)
				KeywordUtil.logInfo('Expected Leader: ' + leader)
				VerifyElement.verifyMatch(Actualleader, leader, false)
			}

			if(!csLevel.equalsIgnoreCase("")) {
				String ActualcsLevel = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/LevelCS'))
				KeywordUtil.logInfo('Actual Level CS: ' + ActualcsLevel)
				KeywordUtil.logInfo('Expected Level CS: ' + csLevel)
				VerifyElement.verifyMatch(ActualcsLevel, csLevel, false)
			}

			if(!csSpecialisasi.equalsIgnoreCase("")) {
				List allcsSpecialisasi = csSpecialisasi.split(',')
				for(int i = 0; i < allcsSpecialisasi.size(); i++) {
					String ActualcsSpecialisasi = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifySpecialisasi', [('indexRow') : (i+1).toString()]))
					KeywordUtil.logInfo('Actual Specialisasi: ' + ActualcsSpecialisasi.toString())
					KeywordUtil.logInfo('Expected Specialisasi: ' + allcsSpecialisasi[i])
					VerifyElement.verifyMatch(ActualcsSpecialisasi, allcsSpecialisasi[i], false)
				}
			}

			if(!NIK.equalsIgnoreCase("")) {
				String ActualNIK = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), 'value')
				KeywordUtil.logInfo('Expected NIK: ' + ActualNIK)
				KeywordUtil.logInfo('Expected NIK: ' + NIK)
				VerifyElement.verifyMatch(ActualNIK, NIK, false)
			}

			if(!title.equalsIgnoreCase("")) {
				String Actualtitle = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyTitle'))
				KeywordUtil.logInfo('Actual Title: ' + Actualtitle)
				KeywordUtil.logInfo('Expected Title: ' + title)
				VerifyElement.verifyMatch(Actualtitle, title, false)
			}

			String ActualfirstName = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), 'value')
			KeywordUtil.logInfo('Expected Firstname: ' + ActualfirstName)
			KeywordUtil.logInfo('Expected Firstname: ' + firstName)
			VerifyElement.verifyMatch(ActualfirstName, firstName, false)

			if(!lastName.equalsIgnoreCase("")) {
				String ActuallastName = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), 'value')
				KeywordUtil.logInfo('Expected Lastname: ' + ActuallastName)
				KeywordUtil.logInfo('Expected Lastname: ' + lastName)
				VerifyElement.verifyMatch(ActuallastName, lastName, false)
			}

			String Actualgender = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'))
			KeywordUtil.logInfo('Actual Gender: ' + Actualgender)
			KeywordUtil.logInfo('Expected Gender: ' + gender)
			VerifyElement.verifyMatch(Actualgender, gender, false)


			String ActualnoTelp = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyNomorTelepon'), 'value')
			KeywordUtil.logInfo('Actual No Telp: ' + ActualnoTelp)
			KeywordUtil.logInfo('Expected No Telp: ' + noTelp)
			VerifyElement.verifyMatch(ActualnoTelp, noTelp, false)
			
			String ActualStatus = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/VerifyStatus'), 'value')
			KeywordUtil.logInfo('Actual Status: ' + ActualStatus)
			KeywordUtil.logInfo('Expected Status: ' + verifystatus)
			VerifyElement.verifyMatch(ActualStatus, verifystatus, false)

			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))

			String Actualusername = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/NamaPengguna'), 'value')
			KeywordUtil.logInfo('Actual Username: ' + Actualusername)
			KeywordUtil.logInfo('Expected Username: ' + username)
			VerifyElement.verifyMatch(Actualusername, username, false)

			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			String Actualrole = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/VerifyPeran'))
			KeywordUtil.logInfo('Actual Role: ' + Actualrole)
			KeywordUtil.logInfo('Expected Role: ' + role)
			VerifyElement.verifyMatch(Actualrole, role, false)

			String ActualdataGroup = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/VerifyDataGroup'))
			KeywordUtil.logInfo('Actual Data Group: ' + ActualdataGroup)
			KeywordUtil.logInfo('Expected Data Group: ' + dataGroup)
			VerifyElement.verifyMatch(ActualdataGroup, dataGroup, false)
		}
		if(Action.equalsIgnoreCase("Edit")) {
			if(isCSO.equalsIgnoreCase("Y")) {
				WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'), 3)
			}else {
				WebUI.verifyElementNotChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'), 3)
			}

			if(leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")) {
				WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
			}else if(!leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")){
				WebUI.verifyElementChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
				String Actualleader = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyLead'))
				KeywordUtil.logInfo('Actual Leader: ' + Actualleader)
				KeywordUtil.logInfo('Expected Leader: ' + leader)
				VerifyElement.verifyMatch(Actualleader, leader, false)
			}else if(isLeader.equalsIgnoreCase("N")) {
				WebUI.verifyElementNotChecked(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
				String Actualleader = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyLead'))
				KeywordUtil.logInfo('Actual Leader: ' + Actualleader)
				KeywordUtil.logInfo('Expected Leader: ' + leader)
				VerifyElement.verifyMatch(Actualleader, leader, false)
			}else{
				String Actualleader = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyLead'))
				KeywordUtil.logInfo('Actual Leader: ' + Actualleader)
				KeywordUtil.logInfo('Expected Leader: ' + leader)
				VerifyElement.verifyMatch(Actualleader, leader, false)
			}

			if(!csLevel.equalsIgnoreCase("")) {
				String ActualcsLevel = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/LevelCS'))
				KeywordUtil.logInfo('Actual Level CS: ' + ActualcsLevel)
				KeywordUtil.logInfo('Expected Level CS: ' + csLevel)
				VerifyElement.verifyMatch(ActualcsLevel, csLevel, false)
			}

			if(!csSpecialisasi.equalsIgnoreCase("")) {
				List allcsSpecialisasi = csSpecialisasi.split(',')
				for(int i = 0; i < allcsSpecialisasi.size(); i++) {
					String ActualcsSpecialisasi = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifySpecialisasi', [('indexRow') : (i+1).toString()]))
					KeywordUtil.logInfo('Actual Specialisasi: ' + ActualcsSpecialisasi.toString())
					KeywordUtil.logInfo('Expected Specialisasi: ' + allcsSpecialisasi[i])
					VerifyElement.verifyMatch(ActualcsSpecialisasi, allcsSpecialisasi[i], false)
				}
			}

			if(!NIK.equalsIgnoreCase("")) {
				String ActualNIK = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), 'value')
				KeywordUtil.logInfo('Expected NIK: ' + ActualNIK)
				KeywordUtil.logInfo('Expected NIK: ' + NIK)
				VerifyElement.verifyMatch(ActualNIK, NIK, false)
			}

			if(!title.equalsIgnoreCase("")) {
				String Actualtitle = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/VerifyTitle'))
				KeywordUtil.logInfo('Actual Title: ' + Actualtitle)
				KeywordUtil.logInfo('Expected Title: ' + title)
				VerifyElement.verifyMatch(Actualtitle, title, false)
			}

			String ActualfirstName = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), 'value')
			KeywordUtil.logInfo('Expected Firstname: ' + ActualfirstName)
			KeywordUtil.logInfo('Expected Firstname: ' + firstName)
			VerifyElement.verifyMatch(ActualfirstName, firstName, false)

			if(!lastName.equalsIgnoreCase("")) {
				String ActuallastName = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), 'value')
				KeywordUtil.logInfo('Expected Lastname: ' + ActuallastName)
				KeywordUtil.logInfo('Expected Lastname: ' + lastName)
				VerifyElement.verifyMatch(ActuallastName, lastName, false)
			}

			String Actualgender = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'))
			KeywordUtil.logInfo('Actual Gender: ' + Actualgender)
			KeywordUtil.logInfo('Expected Gender: ' + gender)
			VerifyElement.verifyMatch(Actualgender, gender, false)

			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			String Actualrole = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/VerifyPeran'))
			KeywordUtil.logInfo('Actual Role: ' + Actualrole)
			KeywordUtil.logInfo('Expected Role: ' + role)
			VerifyElement.verifyMatch(Actualrole, role, false)

			String ActualdataGroup = GeneralAction.getTextFromElement(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/VerifyDataGroup'))
			KeywordUtil.logInfo('Actual Data Group: ' + ActualdataGroup)
			KeywordUtil.logInfo('Expected Data Group: ' + dataGroup)
			VerifyElement.verifyMatch(ActualdataGroup, dataGroup, false)
		}
	}

	public void setEditUser(HashMap hashMapEditUser) {
		String Kategori = hashMapEditUser.get("Kategori")
		String isCSO = hashMapEditUser.get("isCSO")
		String leader = hashMapEditUser.get("leader")
		String isLeader = hashMapEditUser.get("isLeader")
		String csLevel = hashMapEditUser.get("csLevel")
		String csSpecialisasi = hashMapEditUser.get("csSpecialisasi")
		String NIK = hashMapEditUser.get("NIK")
		String title = hashMapEditUser.get("title")
		String firstName = hashMapEditUser.get("firstName")
		String lastName = hashMapEditUser.get("lastName")
		String gender = hashMapEditUser.get("gender")
		String listDist = hashMapEditUser.get("listDist")
		String role = hashMapEditUser.get("role")
		String dataGroup = hashMapEditUser.get("dataGroup")
		String status = hashMapEditUser.get("status")

		WebUI.waitForElementPresent(findTestObject('Object Repository/Master Data/Pengguna/idPengguna'), 10, FailureHandling.STOP_ON_FAILURE)

		if(Kategori.equalsIgnoreCase("Distributor")) {
			List alldist = listDist.split(',')
			for(int i = 0; i < alldist.size(); i++) {
				KeywordUtil.logInfo(alldist.toString())
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Distributor'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectDistributor'), alldist[i])
			}
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/closeSelection'))

			if(!NIK.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), NIK)
			}

			if(!title.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Title'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectTitle'),  title)
			}

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), firstName)

			if(!lastName.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), lastName)
			}

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'),
					findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectJenisKelamin'), gender)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/TglStatus', [('status') : status]))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/Peran'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectRole'), role)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/DataGroup'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectDataGroup'), dataGroup)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnUbah'))
		}else if(Kategori.equalsIgnoreCase("Principal")) {
			if(!NIK.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), NIK)
			}

			if(!title.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Title'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectTitle'),  title)
			}

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), firstName)

			if(!lastName.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), lastName)
			}

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'),
					findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectJenisKelamin'), gender)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/TglStatus', [('status') : status]))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/Peran'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectRole'), role)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/DataGroup'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectDataGroup'), dataGroup)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnUbah'))
		}else {
			if(isCSO.equalsIgnoreCase("Y")) {
				WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'), 3)
				if(!webElement.isSelected()) {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'))
				}
			}else if(isCSO.equalsIgnoreCase("N")) {
				WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'), 3)
				if(webElement.isSelected()) {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'))
				}
			}
			if(leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")) {
				WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
				if(!webElement.isSelected()) {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'))
				}
			}else if(!leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")){
				WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
				if(!webElement.isSelected()) {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'))
				}
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Atasan'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectAtasan'), leader)
			}else if(isLeader.equalsIgnoreCase("N")) {
				WebElement webElement = WebUIAbstractKeyword.findWebElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'), 3)
				if(webElement.isSelected()) {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'))
				}
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Atasan'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectAtasan'), leader)
			}else{
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Atasan'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectAtasan'), leader)
			}

			if(!csLevel.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/LevelCS'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectCsLevel'), csLevel)
			}


			if(!csSpecialisasi.equalsIgnoreCase("")) {
				List allcsSpecialisasi = csSpecialisasi.split(',')
				for(int i = 0; i < allcsSpecialisasi.size(); i++) {
					KeywordUtil.logInfo(allcsSpecialisasi.toString())
					GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Specialisasi'),
							findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectSpecialisasi'), allcsSpecialisasi[i])
				}
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/closeSelection'))
			}

			if(!NIK.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), NIK)
			}

			if(!title.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Title'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectTitle'),  title)
			}

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), firstName)

			if(!lastName.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), lastName)
			}

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'),
					findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectJenisKelamin'), gender)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/TglStatus', [('status') : status]))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/Peran'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectRole'), role)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/DataGroup'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectDataGroup'), dataGroup)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnUbah'))
			
			try {
				if(WebUI.verifyElementNotVisible(findTestObject('Object Repository/Master Data/Pengguna/btnYes'), FailureHandling.OPTIONAL)) {
					KeywordUtil.logInfo('message: button yes tidak muncul')
				} else {
					GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnYes'))
					KeywordUtil.logInfo('message: button yes muncul')
				}
			} catch (Exception e) {
				KeywordUtil.logInfo('message: button yes muncul')
			}
			
		}
	}

	public void setDataNewUser(HashMap hashMapsetNewUser) {
		String Kategori = hashMapsetNewUser.get("Kategori")
		String email = hashMapsetNewUser.get("email")
		String noTelp = hashMapsetNewUser.get("noTelp")
		String isCSO = hashMapsetNewUser.get("isCSO")
		String leader = hashMapsetNewUser.get("leader")
		String isLeader = hashMapsetNewUser.get("isLeader")
		String csLevel = hashMapsetNewUser.get("csLevel")
		String csSpecialisasi = hashMapsetNewUser.get("csSpecialisasi")
		String NIK = hashMapsetNewUser.get("NIK")
		String title = hashMapsetNewUser.get("title")
		String firstName = hashMapsetNewUser.get("firstName")
		String lastName = hashMapsetNewUser.get("lastName")
		String gender = hashMapsetNewUser.get("gender")
		String username = hashMapsetNewUser.get("username")
		String password = hashMapsetNewUser.get("password")
		String listDist = hashMapsetNewUser.get("listDist")
		String confrimPassword = hashMapsetNewUser.get("confrimPassword")
		String role = hashMapsetNewUser.get("role")
		String dataGroup = hashMapsetNewUser.get("dataGroup")

		WebUI.waitForElementClickable(findTestObject('Object Repository/Master Data/Pengguna/TambahEditPengguna/KategoriPengguna'), 3)
		GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/TambahEditPengguna/KategoriPengguna'),
				findTestObject('Object Repository/Master Data/Pengguna/TambahEditPengguna/SelectKategori'), Kategori)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/TambahEditPengguna/Email'), email)
		GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/TambahEditPengguna/NomorTelepon'), noTelp)
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/TambahEditPengguna/Periksa'))
		GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/TambahEditPengguna/Periksa'))

		if(Kategori.equalsIgnoreCase("Distributor")) {
			List alldist = listDist.split(',')
			for(int i = 0; i < alldist.size(); i++) {
				KeywordUtil.logInfo(alldist.toString())
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Distributor'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectDistributor'), alldist[i])
			}
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/closeSelection'))

			if(!NIK.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), NIK)
			}

			if(!title.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Title'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectTitle'),  title)
			}

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), firstName)

			if(!lastName.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), lastName)
			}

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'),
					findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectJenisKelamin'), gender)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/NamaPengguna'), username)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/KataSandi'), password)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/icoViewPass'))
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/KonfrimasiPassword'), confrimPassword)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/icoViewConPass'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/Periksa'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/Peran'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectRole'), role)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/DataGroup'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectDataGroup'), dataGroup)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnTambahUser'))
		}else if(Kategori.equalsIgnoreCase("Principal")) {
			if(!NIK.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), NIK)
			}

			if(!title.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Title'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectTitle'),  title)
			}

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), firstName)

			if(!lastName.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), lastName)
			}

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'),
					findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectJenisKelamin'), gender)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/NamaPengguna'), username)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/KataSandi'), password)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/icoViewPass'))
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/KonfrimasiPassword'), confrimPassword)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/icoViewConPass'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/Periksa'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/Peran'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectRole'), role)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/DataGroup'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectDataGroup'), dataGroup)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnTambahUser'))
		}else {
			if(!isCSO.equalsIgnoreCase("N")) {
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/CSOfficer'))
			}

			if(leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")) {
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'))
			}else if(!leader.equalsIgnoreCase("") && isLeader.equalsIgnoreCase("Y")){
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/StatusAtasan'))
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Atasan'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectAtasan'), leader)
			}else{
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Atasan'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectAtasan'), leader)
			}

			if(!csLevel.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/LevelCS'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectCsLevel'), csLevel)
			}


			if(!csSpecialisasi.equalsIgnoreCase("")) {
				List allcsSpecialisasi = csSpecialisasi.split(',')
				for(int i = 0; i < allcsSpecialisasi.size(); i++) {
					KeywordUtil.logInfo(allcsSpecialisasi.toString())
					GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Specialisasi'),
							findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectSpecialisasi'), allcsSpecialisasi[i])
				}
				GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/closeSelection'))
			}

			if(!NIK.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NIK'), NIK)
			}

			if(!title.equalsIgnoreCase("")) {
				GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/Title'),
						findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectTitle'),  title)
			}

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaDepan'), firstName)

			if(!lastName.equalsIgnoreCase("")) {
				GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/NamaBelakang'), lastName)
			}

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/JenisKelamin'),
					findTestObject('Object Repository/Master Data/Pengguna/ProfilePengguna/SelectJenisKelamin'), gender)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnya'))

			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/NamaPengguna'), username)
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/KataSandi'), password)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/icoViewPass'))
			GeneralAction.clickElementAndType(findTestObject('Object Repository/Master Data/Pengguna/Akun/KonfrimasiPassword'), confrimPassword)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/icoViewConPass'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/Akun/Periksa'))
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnSelanjutnyaUsername'))

			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/Peran'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectRole'), role)
			GeneralAction.clickElementSearchAndSelect(findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/DataGroup'),
					findTestObject('Object Repository/Master Data/Pengguna/PeranPengguna/SelectDataGroup'), dataGroup)
			GeneralAction.clickElement(findTestObject('Object Repository/Master Data/Pengguna/btnTambahUser'))
		}
	}
}

