package parameter.care

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

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import internal.GlobalVariable
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation
import care.katalon.GeneralAction
import care.katalon.VerifyElement

public class Parameter {
	GeneralAction GeneralAction = new GeneralAction()
	VerifyElement VerifyElement = new VerifyElement()

	public void MenuParameter() {
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Pengaturan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Home Page(General)/Menu Level 2 Pengaturan/Parameter'))
	}

	public void saveParameter() {
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Simpan'))
		GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/konfrimasiSave'))
		WebUI.delay(3)
	}

	public void verifyParameter(HashMap hashMapVerifyParameter) {
		String ActionParam = hashMapVerifyParameter.get("ActionParam")
		String priority = hashMapVerifyParameter.get("priority")
		String targetResolution = hashMapVerifyParameter.get("targetResolution")
		String floatingTicket = hashMapVerifyParameter.get("floatingTicket")
		String toleranAssign = hashMapVerifyParameter.get("toleranAssign")
		String forceLogout = hashMapVerifyParameter.get("forceLogout")
		String channel = hashMapVerifyParameter.get("channel")
		String targetResponse = hashMapVerifyParameter.get("targetResponse")
		String maxReassignPhoneAndEmail = hashMapVerifyParameter.get("maxReassignPhoneAndEmail")
		String MaxReassignLiveChat = hashMapVerifyParameter.get("MaxReassignLiveChat")
		String MaxQueueReassign = hashMapVerifyParameter.get("MaxQueueReassign")

		if(!ActionParam.equalsIgnoreCase("")) {
			List allParam = ActionParam.split(',')
			for(int i = 0; i < allParam.size(); i++) {
				KeywordUtil.logInfo(allParam.toString())
				if(allParam[i].toString().equalsIgnoreCase("Resolution")) {
					List allPriority = priority.split(',')
					List allResolution = targetResolution.split(',')
					for(int j = 0; j < allPriority.size(); j++) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Skala Prioritas'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectPrioritas', [('priority') : allPriority[j]]))
						String ActualResolution = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Target Resolusi'), 'value')
						KeywordUtil.logInfo("Actual " + ActualResolution)
						KeywordUtil.logInfo("Expected " + allResolution.toString())
						VerifyElement.verifyMatch(ActualResolution, allResolution[j], false)
					}
				}

				if(allParam[i].toString().equalsIgnoreCase("Response")) {
					List allChannel = channel.split(',')
					List allResponse = targetResponse.split(',')
					for(int k = 0; k < allChannel.size(); k++) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Tiket Channel'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectChannel', [('channel') : allChannel[k]]))
						String ActualResponse = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Target Response'), 'value')
						KeywordUtil.logInfo(ActualResponse)
						VerifyElement.verifyMatch(ActualResponse, allResponse[k], false)
					}
				}

				if(allParam[i].toString().equalsIgnoreCase("Floating")) {
					String ActualFloating = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Durasi Tiket Mengambang'), 'value')
					KeywordUtil.logInfo(ActualFloating)
					VerifyElement.verifyMatch(ActualFloating, floatingTicket, false)
				}

				if(allParam[i].toString().equalsIgnoreCase("Toleransi")) {
					String ActualToleransi = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Toleransi Assign CS'), 'value')
					KeywordUtil.logInfo(ActualToleransi)
					VerifyElement.verifyMatch(ActualToleransi, toleranAssign, false)
				}

				if(allParam[i].toString().equalsIgnoreCase("ForceLogOut")) {
					String ActualForce = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Force Logout'), 'value')
					KeywordUtil.logInfo(ActualForce)
					VerifyElement.verifyMatch(ActualForce, forceLogout, false)
				}

				if(allParam[i].toString().equalsIgnoreCase("MaxPhoneEmail")) {
					String ActualReassignPhoneAndEmail = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Phone And Email'), 'value')
					KeywordUtil.logInfo(ActualReassignPhoneAndEmail)
					VerifyElement.verifyMatch(ActualReassignPhoneAndEmail, maxReassignPhoneAndEmail, false)
				}

				if(allParam[i].toString().equalsIgnoreCase("MaxLiveChat")) {
					String ActualMaxReassignLiveChat = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Live Chat'), 'value')
					KeywordUtil.logInfo(ActualMaxReassignLiveChat)
					VerifyElement.verifyMatch(ActualMaxReassignLiveChat, MaxReassignLiveChat, false)
				}

				if(allParam[i].toString().equalsIgnoreCase("MaxQueue")) {
					String ActualMaxQueueReassign = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Max Queue Reassign'), 'value')
					KeywordUtil.logInfo(ActualMaxQueueReassign)
					VerifyElement.verifyMatch(ActualMaxQueueReassign, MaxQueueReassign, false)
				}

				if(allParam[i].toString().equalsIgnoreCase("All")) {
					if(!priority.equalsIgnoreCase("")) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Skala Prioritas'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectPrioritas', [('priority') : priority]))
						String ActualResolution = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Target Resolusi'), 'value')
						KeywordUtil.logInfo(ActualResolution)
						VerifyElement.verifyMatch(ActualResolution, priority, false)
					}
					if(!channel.equalsIgnoreCase("")) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Tiket Channel'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectChannel', [('channel') : channel]))
						String ActualResponse = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Target Response'), 'value')
						KeywordUtil.logInfo(ActualResponse)
						VerifyElement.verifyMatch(ActualResponse, channel, false)
					}
					if(!floatingTicket.equalsIgnoreCase("")) {
						String ActualFloating = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Durasi Tiket Mengambang'), 'value')
						KeywordUtil.logInfo(ActualFloating)
						VerifyElement.verifyMatch(ActualFloating, floatingTicket, false)
					}
					if(!toleranAssign.equalsIgnoreCase("")) {
						String ActualToleransi = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Toleransi Assign CS'), 'value')
						KeywordUtil.logInfo(ActualToleransi)
						VerifyElement.verifyMatch(ActualToleransi, toleranAssign, false)
					}
					if(!forceLogout.equalsIgnoreCase("")) {
						String ActualForce = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Force Logout'), 'value')
						KeywordUtil.logInfo(ActualForce)
						VerifyElement.verifyMatch(ActualForce, forceLogout, false)
					}
					if(!maxReassignPhoneAndEmail.equalsIgnoreCase("")) {
						String ActualReassignPhoneAndEmail = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Phone And Email'), 'value')
						KeywordUtil.logInfo(ActualReassignPhoneAndEmail)
						VerifyElement.verifyMatch(ActualReassignPhoneAndEmail, maxReassignPhoneAndEmail, false)
					}
					if(!MaxReassignLiveChat.equalsIgnoreCase("")) {
						String ActualMaxReassignLiveChat = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Live Chat'), 'value')
						KeywordUtil.logInfo(ActualMaxReassignLiveChat)
						VerifyElement.verifyMatch(ActualMaxReassignLiveChat, MaxReassignLiveChat, false)
					}
					if(!MaxQueueReassign.equalsIgnoreCase("")) {
						String ActualMaxQueueReassign = GeneralAction.getAttributeFromElement(findTestObject('Object Repository/Pengaturan/Parameter/Max Queue Reassign'), 'value')
						KeywordUtil.logInfo(ActualMaxQueueReassign)
						VerifyElement.verifyMatch(ActualMaxQueueReassign, MaxQueueReassign, false)
					}
				}
			}
		}
	}

	public void editParameter(HashMap hashMapEditParameter) {
		String ActionParam = hashMapEditParameter.get("ActionParam")
		String priority = hashMapEditParameter.get("priority")
		String targetResolution = hashMapEditParameter.get("targetResolution")
		String floatingTicket = hashMapEditParameter.get("floatingTicket")
		String toleranAssign = hashMapEditParameter.get("toleranAssign")
		String forceLogout = hashMapEditParameter.get("forceLogout")
		String channel = hashMapEditParameter.get("channel")
		String targetResponse = hashMapEditParameter.get("targetResponse")
		String maxReassignPhoneAndEmail = hashMapEditParameter.get("maxReassignPhoneAndEmail")
		String MaxReassignLiveChat = hashMapEditParameter.get("MaxReassignLiveChat")
		String MaxQueueReassign = hashMapEditParameter.get("MaxQueueReassign")

		if(!ActionParam.equalsIgnoreCase("")) {
			List allParam = ActionParam.split(',')
			for(int i = 0; i < allParam.size(); i++) {
				KeywordUtil.logInfo(allParam.toString())
				if(allParam[i].toString().equalsIgnoreCase("Resolution")) {
					List allPriority = priority.split(',')
					List allResolution = targetResolution.split(',')
					for(int j = 0; j < allPriority.size(); j++) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Skala Prioritas'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectPrioritas', [('priority') : allPriority[j]]))
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Target Resolusi'), allResolution[j])
						saveParameter()
					}
				}

				if(allParam[i].toString().equalsIgnoreCase("Response")) {
					List allChannel = channel.split(',')
					List allResponse = targetResponse.split(',')
					for(int k = 0; k < allChannel.size(); k++) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Tiket Channel'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectChannel', [('channel') : allChannel[k]]))
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Target Response'), allResponse[k])
						saveParameter()
					}
				}

				if(allParam[i].toString().equalsIgnoreCase("Floating")) {
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Durasi Tiket Mengambang'), floatingTicket)
					saveParameter()
				}

				if(allParam[i].toString().equalsIgnoreCase("Toleransi")) {
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Toleransi Assign CS'), toleranAssign)
					saveParameter()
				}

				if(allParam[i].toString().equalsIgnoreCase("ForceLogOut")) {
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Force Logout'), forceLogout)
					saveParameter()
				}

				if(allParam[i].toString().equalsIgnoreCase("MaxPhoneEmail")) {
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Phone And Email'), maxReassignPhoneAndEmail)
					saveParameter()
				}

				if(allParam[i].toString().equalsIgnoreCase("MaxLiveChat")) {
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Live Chat'), MaxReassignLiveChat)
					saveParameter()
				}

				if(allParam[i].toString().equalsIgnoreCase("MaxQueue")) {
					GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Max Queue Reassign'), MaxQueueReassign)
					saveParameter()
				}

				if(allParam[i].toString().equalsIgnoreCase("All")) {
					if(!priority.equalsIgnoreCase("")) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Skala Prioritas'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectPrioritas', [('priority') : priority]))
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Target Resolusi'), targetResolution)
					}
					if(!channel.equalsIgnoreCase("")) {
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/Daftar Tiket Channel'))
						GeneralAction.clickElement(findTestObject('Object Repository/Pengaturan/Parameter/SelectChannel', [('channel') : channel]))
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Target Response'), targetResponse)
					}
					if(!floatingTicket.equalsIgnoreCase("")) {
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Durasi Tiket Mengambang'), floatingTicket)
					}
					if(!toleranAssign.equalsIgnoreCase("")) {
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Toleransi Assign CS'), toleranAssign)
					}
					if(!forceLogout.equalsIgnoreCase("")) {
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Force Logout'), forceLogout)
					}
					if(!maxReassignPhoneAndEmail.equalsIgnoreCase("")) {
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Phone And Email'), maxReassignPhoneAndEmail)
					}
					if(!MaxReassignLiveChat.equalsIgnoreCase("")) {
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Max Reassgin Live Chat'), MaxReassignLiveChat)
					}
					if(!MaxQueueReassign.equalsIgnoreCase("")) {
						GeneralAction.clickElementAndType(findTestObject('Object Repository/Pengaturan/Parameter/Max Queue Reassign'), MaxQueueReassign)
					}
					saveParameter()
				}
			}
		}
	}
}
