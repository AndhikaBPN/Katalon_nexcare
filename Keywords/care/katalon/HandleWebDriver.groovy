package care.katalon

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium as WebDriverBackedSelenium
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions as FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile as FirefoxProfile
import org.openqa.selenium.firefox.ProfilesIni as ProfilesIni
import com.kms.katalon.core.webui.driver.WebUIDriverType as WebUIDriverType
import org.openqa.selenium.Keys as Keys

class HandleWebDriver {

	def startAndOpenWebBrowser(String urlBrowser) {

		KeywordUtil.logInfo("URL ENV = " + urlBrowser)

		try {
			WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()

			switch (executedBrowser) {
				case WebUIDriverType.FIREFOX_DRIVER:

				/*
				 * Move To Project > Settings > Desired Capabilities > WebUI > Firefox 
				 String getPath = DriverFactory.getGeckoDriverPath()
				 KeywordUtil.logInfo("GET PATH = " + getPath)
				 System.setProperty('webdriver.gecko.driver', getPath)
				 FirefoxOptions options = new FirefoxOptions()
				 options.addPreference('marionette', true)
				 options.addPreference("layout.css.devPixelsPerPx", "0.8")
				 options.addPreference('browser.download.folderList', 2)
				 options.addPreference('browser.helperApps.alwaysAsk.force', false)
				 options.addPreference('browser.download.manager.showWhenStarting', false)
				 options.addPreference('browser.download.dir', GlobalVariable.downloadPath)
				 options.addPreference('browser.download.downloadDir', GlobalVariable.downloadPath)
				 options.addPreference('browser.download.defaultFolder', GlobalVariable.downloadPath)
				 options.addPreference('browser.helperApps.neverAsk.saveToDisk', 'application/download, application/octet-stream, text/csv, application/pdf')
				 options.addPreference('pdfjs.disabled', true)
				 WebDriver driver = new FirefoxDriver(options);
				 DriverFactory.changeWebDriver(driver)
				 WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)
				 WebUI.maximizeWindow(FailureHandling.STOP_ON_FAILURE)
				 WebUI.navigateToUrl(urlBrowser)
				 WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)
				 break;
				 */

					WebUI.openBrowser('', FailureHandling.STOP_ON_FAILURE)

					WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)

					WebUI.maximizeWindow(FailureHandling.STOP_ON_FAILURE)

					WebUI.navigateToUrl(urlBrowser)

					WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)

					break;

				default:
					WebUI.openBrowser('', FailureHandling.STOP_ON_FAILURE)

					WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)

					WebUI.maximizeWindow(FailureHandling.STOP_ON_FAILURE)

					WebUI.navigateToUrl(urlBrowser)

					WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSeccond, FailureHandling.STOP_ON_FAILURE)
			}
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def getCurrentWebDriver() {
		def driver = DriverFactory.getWebDriver()

		return driver;
	}
}