package care.katalon

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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import com.kms.katalon.core.testdata.reader.ExcelFactory
import com.kms.katalon.core.util.KeywordUtil

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.FileWriter
import java.util.HashMap

public class HandleTestData {

	public List<HashMap> readTestData(String pathFile, String sheetName, boolean isUsingFirstRowAsHeader){

		int i,j;

		//		need to change path test data
		String pathFileTestData = GlobalVariable.pathTestData + pathFile

		List<HashMap> listHashMap = new ArrayList<HashMap>()

		try {

			TestData data = ExcelFactory.getExcelDataWithDefaultSheet(pathFileTestData, sheetName, isUsingFirstRowAsHeader)

			List<List<Object>> listAllData = data.getAllData()

			String[] getHeaderColumnName = data.getColumnNames()

			//KeywordUtil.logInfo("listAllData : " + listAllData.size().toString())

			//KeywordUtil.logInfo("getHeaderColumn : " + getHeaderColumnName )

			for(i = 0; i < listAllData.size(); i++) {

				List<Object> getListData = listAllData.get(i)

				HashMap<Object, Object> hashMapSetKeyAndValueFromTestData = new HashMap<Object, Object>()

				for(j = 0; j < getHeaderColumnName.length; j++) {
					String keyName = getHeaderColumnName[j]
					String valueData = getListData.get(j)

					if(!keyName.equals(null) || !keyName.equals("")) {

						if(valueData.equals(null) && !keyName.equals(null)) {
							hashMapSetKeyAndValueFromTestData.put(keyName, "")
						}else {
							hashMapSetKeyAndValueFromTestData.put(keyName, valueData)
						}

					}
				}

				listHashMap.add(hashMapSetKeyAndValueFromTestData)
			}

			KeywordUtil.logInfo("listHashMap di Method readTestData class care.katalon.HandleTestData : "+listHashMap)
		}catch(Exception e) {
			KeywordUtil.logInfo(e.printStackTrace())
		}

		return listHashMap;
	}

	public List<HashMap> filterDataHashMap(List<HashMap> listHashMap, String keyNameData, String valueDocument) {

		List<HashMap> hasil = new ArrayList<HashMap>()

		for (int k = 0; k < listHashMap.size(); k++) {

			//			KeywordUtil.logInfo("data " + listHashMap.get(k))

			HashMap hashTC = listHashMap.get(k)

			if(hashTC.get(keyNameData).equals(valueDocument)) {
				hasil.add(hashTC)
			}
			else {
				continue;
			}
		}

		return hasil;
	}
}
