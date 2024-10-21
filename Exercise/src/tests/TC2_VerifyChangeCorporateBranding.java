package tests;

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
public class TC2_VerifyChangeCorporateBranding extends BaseTest{
	String[] logindata_column = new String[]{"username", "password"};
	String[] incorrect_image_data_column = new String[]{"colorCode","clientBanner"};
	String[] correct_image_data_column = new String[]{"colorCode", "clientLogo", "loginBanner","clientBanner"};
	String sheetname = "accounts";
	
	@Test(dataProvider = "logindata", priority = 1)
	public void login(String user, String pass) {
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.navigateToLoginPage();
		loginPage.login(user, pass);
	}
	
	@Test(priority = 2)
	public void navigateToAdmin() {
		DashboardPage dashboardPage = new DashboardPage(getDriver());
		
		dashboardPage.navigateToAdminPage();
	}
	
	@Test(dataProvider = "incorrect_image_data", priority = 3)
	public void verifyChangeCoporateBrandingWithIncorrectFile(String colorCode, String filePath) {
		AdminPage adminPage = new AdminPage(getDriver());
		
		adminPage.verifyChangeCorporateBrandingWithIncorrectImage(colorCode, filePath);
	}
	
	@Test(dataProvider = "correct_image_data", priority = 4)
	public void verifyChangeCoporateBrandingWithCorrectFile(String colorCode,  String clientLogoPath, 
															String loginBannerPath, String clientBannerPath) {
		AdminPage adminPage = new AdminPage(getDriver());
		
		adminPage.verifyChangeCorporateBrandingWithCorrectImage(colorCode,clientLogoPath, 
																loginBannerPath, clientBannerPath);
	}
	
	
	
	@DataProvider(name = "logindata")
	public Object[][] getLoginData(){
		return ExcelUltils.testReadCellsByColumnNames(filePath(), sheetname, logindata_column, 2);
	}
	
	@DataProvider(name = "incorrect_image_data")
	public Object[][] getIncorrectImageData(){
		return ExcelUltils.testReadCellsByColumnNames(filePath(), sheetname, incorrect_image_data_column, 2);
	}
	
	@DataProvider(name = "correct_image_data")
	public Object[][] getCorrectImageData(){
		return ExcelUltils.testReadCellsByColumnNames(filePath(), sheetname, correct_image_data_column, 2);
	}
}
