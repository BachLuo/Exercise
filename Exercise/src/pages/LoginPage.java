package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.BasePage;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameField;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;
	
    public void navigateToLoginPage() {
        getDriver().get("https://opensource-demo.orangehrmlive.com");;
    }
    
    public void login(String username, String password) {
    	getWaitUntilVisibleOf(usernameField);
    	usernameField.sendKeys(username);
    	passwordField.sendKeys(password);
    	passwordField.sendKeys(Keys.ENTER);
    }
}
