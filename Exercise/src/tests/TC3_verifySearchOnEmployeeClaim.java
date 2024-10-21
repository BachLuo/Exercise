package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.BaseTest;
import core.ExcelUltils;
import listener.TestListeners;
import pages.ClaimPage;
import pages.DashboardPage;
import pages.LoginPage;

@Listeners(TestListeners.class)
public class TC3_verifySearchOnEmployeeClaim extends BaseTest {
	String[] logindata_column = new String[]{"username", "password"};
	String[] searchdata_column = new String[]{"referenceId", "eventName", "Status", "Include"};
	String sheetname = "accounts";
	
	@Test(dataProvider = "logindata", priority = 1)
	public void login(String user, String pass) {
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.navigateToLoginPage();
		loginPage.login(user, pass);
	}
	
	@Test(priority = 2)
	public void navigateToClaimPage() {
		DashboardPage dashboardPage = new DashboardPage(getDriver());
		
		dashboardPage.navigateToClaimPage();
	}
	
	@Test(dataProvider = "searchdata", priority = 3)
	public void verifySearchOnEmployeeClaim(String refId, String eventName, String status, String include) {
		ClaimPage claimPage = new ClaimPage(getDriver());
		
		claimPage.verifySearchOnEmployeeClaims(refId, eventName, status, include);
	}
	
	@DataProvider(name = "logindata")
	public Object[][] getLoginData(){
		return ExcelUltils.testReadCellsByColumnNames(filePath(), sheetname, logindata_column, 3);
	}
	
	@DataProvider(name = "searchdata")
	public Object[][] getSearchData(){
		return ExcelUltils.testReadCellsByColumnNames(filePath(), sheetname, searchdata_column, 3);
	}
}
