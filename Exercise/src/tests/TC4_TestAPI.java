package tests;

import org.json.JSONException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.BaseTest;
import core.ExcelUltils;
import listener.TestListeners;
import pages.SubunitApiPage;

@Listeners(TestListeners.class)
public class TC4_TestAPI extends BaseTest{
	
	  @Test(dataProvider = "cookiedata")
	  public void testApi(String cookie) throws JSONException {
		  SubunitApiPage apiPage = new SubunitApiPage();
		  
		  String response = apiPage.sendApiAndGetResponse(cookie);
		  apiPage.getValueOfMetaDataAndSubunitNameWithId(response);
	  }
	  
	  @DataProvider(name = "cookiedata")
		public Object[][] getSearchData(){
			return ExcelUltils.testReadCellsByColumnNames(filePath(), "accounts", new String[]{"cookie"}, 4);
		}
}
