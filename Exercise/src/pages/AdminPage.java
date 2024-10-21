package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import core.BasePage;

public class AdminPage extends BasePage{

	public AdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//button[text()=' Add ']")
	private WebElement addBtn;
	
	@FindBy(xpath = "//div[contains(text(),'Select')]")
	private WebElement userRole;
	
	@FindBy(xpath = "//div[contains(text(),'Select')]")
	private WebElement status;
	
	@FindBy(xpath = "//span[text()='ESS']")
	private WebElement ess;
	
	@FindBy(xpath = "//span[text()='Enabled']")
	private WebElement enabled;
	
	@FindBy(xpath = "//input[contains(@placeholder,'hints')]")
	private WebElement nameField;
	
	@FindBy(xpath = "(//div[@role='option'])[2]")
	private WebElement nameSelection;
	
	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement usernameField;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath = "(//input[@type='password'])[2]")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath = "//button[normalize-space()='Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//p[text()='Success']")
	private WebElement successMessage;
	
	@FindBy(xpath = "//button[normalize-space()='Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//div[@class='oxd-table-body']/div")
	private WebElement userSearched;
	
	@FindBy(xpath = "//a[contains(text(),'Corporate')]")
	private WebElement corporateBranding;
	
	@FindBy(xpath = "//label[text()='Primary Color']//following-sibling::div")
	private WebElement  primaryColor;
	
	@FindBy(xpath = "//label[text()='Primary Font Color']//following-sibling::div")
	private WebElement  fontColor;
	
	@FindBy(xpath = "//label[text()='Primary Gradient Color 1']//following-sibling::div")
	private WebElement  gradinetColor1;
	
	@FindBy(xpath = "//label[text()='Primary Gradient Color 2']//following-sibling::div")
	private WebElement  gradinetColor2;
	
	@FindBy(xpath = "//label[text()='Secondary Color']//following-sibling::div")
	private WebElement  secondaryColor;
	
	@FindBy(xpath = "//label[text()='Secondary Font Color']//following-sibling::div")
	private WebElement  secondaryFontColor;
	
	@FindBy(xpath = "//label//following-sibling::div//input[contains(@class,'input')]")
	private WebElement inputColor;
	
	@FindBy(xpath = "//label[text()='Client Logo']//parent::div//following-sibling::div/input")
	private WebElement uploadClientLogo;
	
	@FindBy(xpath = "//label[text()='Client Banner']//parent::div//following-sibling::div/input")
	private WebElement uploadClientBanner;
	
	@FindBy(xpath = "//label[text()='Login Banner']//parent::div//following-sibling::div/input")
	private WebElement uploadLoginBanner;
	
	@FindBy(xpath = "//span[text()='Incorrect Dimensions']")
	private WebElement incorrectUploadMessage;
	
	@FindBy(xpath = "//button[normalize-space()='Publish']")
	private WebElement publishBtn;
	
	private void addUser(String user, String pass, String employeeName) {
		getWaitUntilVisibleOf(addBtn);
		addBtn.click();
		getWaitUntilVisibleOf(userRole);
		userRole.click();
		ess.click();
		getWaitUntilVisibleOf(status);
		status.click();
		enabled.click();
		
		nameField.sendKeys(employeeName);
		getWaitUntilVisibleOf(nameSelection);
		nameSelection.click();
		
		usernameField.sendKeys(user);
		passwordField.sendKeys(pass);
		confirmPasswordField.sendKeys(pass);
		
		saveBtn.click();
	}
	
	private void searchUser(String user, String employeeName) {
		getWaitUntilVisibleOf(userRole);
		userRole.click();
		ess.click();
		getWaitUntilVisibleOf(status);
		status.click();
		enabled.click();
		
		nameField.sendKeys(employeeName);
		getWaitUntilVisibleOf(nameSelection);
		nameSelection.click();
		
		usernameField.sendKeys(user);
		searchBtn.click();
	}
	
	private void changeColor(String colorCode) {
		getWaitUntilVisibleOf(inputColor);
//		inputColor.clear();
		inputColor.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		getWaitUntilVisibleOf(inputColor);
		inputColor.sendKeys(colorCode);
		getWaitUntilVisibleOf(inputColor);
		inputColor.sendKeys(Keys.ENTER);
	}
	
	private void changeCorporateBrandingWithIncorrectImage(String colorCode, String filePath) {
		getWaitUntilVisibleOf(corporateBranding);
		corporateBranding.click();
		
		getWaitUntilVisibleOf(primaryColor);
		primaryColor.click();
		changeColor(colorCode);
		
		fontColor.click();
		changeColor(colorCode);
		
		gradinetColor1.click();
		changeColor(colorCode);
		
//		gradinetColor2.click();
//		changeColor(colorCode);
		
		secondaryColor.click();
		changeColor(colorCode);
		
		secondaryFontColor.click();
		changeColor(colorCode);
		
		uploadClientLogo.sendKeys(filePath);
	}
	
	private void changeCorporateBrandingWithCorrectImage(String colorCode, String clientLogoPath, 
			   											String loginBannerPath, String clientBannerPath) {
		getWaitUntilVisibleOf(corporateBranding);
		corporateBranding.click();
		
		getWaitUntilVisibleOf(primaryColor);
		primaryColor.click();
		changeColor(colorCode);
		
		fontColor.click();
		changeColor(colorCode);
		
		gradinetColor1.click();
		changeColor(colorCode);
		
		gradinetColor2.click();
		changeColor(colorCode);
		
		secondaryColor.click();
		changeColor(colorCode);
		
		secondaryFontColor.click();
		changeColor(colorCode);
		
		uploadClientLogo.sendKeys(clientLogoPath);
		uploadClientBanner.sendKeys(clientBannerPath);
		uploadLoginBanner.sendKeys(loginBannerPath);
		publishBtn.click();
	}
	
	public void verifyAddUserSuccessMessage(String user, String pass, String employeeName) {
		addUser(user, pass, employeeName);
		getWaitUntilVisibleOf(successMessage);
		Assert.assertTrue(successMessage.isDisplayed());
	}
	
	public void verifyDisplayedCorrectInfor(String user, String employeeName) {
		searchUser(user, employeeName);
		getWaitUntilVisibleOf(userSearched);
		Assert.assertTrue(userSearched.isDisplayed());
	}
	
	public void verifyChangeCorporateBrandingWithIncorrectImage(String colorCode, String filePath) {
		changeCorporateBrandingWithIncorrectImage(colorCode, filePath);
		getWaitUntilVisibleOf(incorrectUploadMessage);
		Assert.assertTrue(incorrectUploadMessage.isDisplayed());
	}
	
	public void verifyChangeCorporateBrandingWithCorrectImage(String colorCode, String clientLogoPath, 
														   String loginBannerPath, String clientBannerPath) {
		changeCorporateBrandingWithCorrectImage(colorCode, clientLogoPath, loginBannerPath, clientBannerPath);
		getWaitUntilVisibleOf(successMessage);
		Assert.assertTrue(successMessage.isDisplayed());
	}
}
