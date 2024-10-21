package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.BasePage;

public class DashboardPage extends BasePage{

	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement admin;
	
	@FindBy(xpath = "//span[text()='Claim']")
	private WebElement claim;
	
	public void navigateToAdminPage() {
		getWaitUntilVisibleOf(admin);
		admin.click();
	}
	
	public void navigateToClaimPage() {
		getWaitUntilVisibleOf(claim);
		claim.click();
	}
}
