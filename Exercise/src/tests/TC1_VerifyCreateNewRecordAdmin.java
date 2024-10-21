package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.BaseTest;
import core.ExcelUltils;
import listener.TestListeners;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;

@Listeners(TestListeners.class)
public class TC1_VerifyCreateNewRecordAdmin extends BaseTest {
	String[] testdata_column = new String[]{"username", "password","employeeName"};
	String sheetname = "accounts";
	
	@Test(dataProvider = "testdata", priority = 1)
	public void login(String user, String pass, String text) {
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.navigateToLoginPage();
		loginPage.login(user, pass);
	}
	
	
	@Test(priority = 2)
	public void navigateToAdmin() {
		DashboardPage dashboardPage = new DashboardPage(getDriver());
		
		dashboardPage.navigateToAdminPage();
	}
	
	@Test(dataProvider = "testdata", priority = 3)
	public void verifyAddUserAndCorrectInfor(String user, String pass, String text) {
		AdminPage adminPage = new AdminPage(getDriver());
		
		String newUser = RandomStringUtils.randomAlphanumeric(6);
		
		adminPage.verifyAddUserSuccessMessage(newUser, pass, text);
		adminPage.verifyDisplayedCorrectInfor(newUser, text);
	}
	
	@DataProvider(name="testdata")
	public Object[][] getTestData(){
		return ExcelUltils.testReadCellsByColumnNames(filePath(), sheetname, testdata_column, 1);
	}
}
