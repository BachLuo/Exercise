package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import core.BasePage;

public class ClaimPage extends BasePage{

	public ClaimPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//a[text()='Employee Claims']")
	private WebElement employeeClaims;
	
	@FindBy(xpath = "//label[text()='Reference Id']//parent::div//following-sibling::div//input")
	private WebElement referenceIdField;
	
	@FindBy(xpath = "//label[text()='Event Name']//parent::div//following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement eventNameSelect;
	
	@FindBy(xpath = "//span[contains(text(),'Travel Allowance')]")
	private WebElement eventSelection;
	
	@FindBy(xpath = "//label[text()='Status']//parent::div//following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement statusSelect;
	
	@FindBy(xpath = "//span[contains(text(),'Submitted')]")
	private WebElement statusSelection;
	
	@FindBy(xpath = "//label[text()='Include']//parent::div//following-sibling::div//div[@class='oxd-select-text-input']")
	private WebElement includeSelect;
	
	@FindBy(xpath = "//span[contains(text(),'Current and Past')]")
	private WebElement includeSelection;
	
	@FindBy(xpath = "//div[@role='listbox']//div")
	private List<WebElement> selectionElements;
	
	@FindBy(xpath = "//button[normalize-space()='Search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//div[@role='cell']")
	private List<WebElement> employeeSearchedValues;
	
	private void searchEmployeeClaim(String refId, String eventName, String status, String include) {
		getWaitUntilVisibleOf(employeeClaims);
		employeeClaims.click();
		
		getWaitUntilVisibleOf(referenceIdField);
		referenceIdField.sendKeys(refId);
		referenceIdField.sendKeys(Keys.ENTER);
		
		getWaitUntilVisibleOf(eventNameSelect);
		eventNameSelect.click();
		getWaitUntilVisibleOfAll(selectionElements);
		for(WebElement selection : selectionElements) {
			if (selection.getText().equals(eventName)) {
				getWaitUntilClickable(selection);
				selection.click();
				break;
			}
		}
		
		getWaitUntilClickable(statusSelect);
		statusSelect.click();
		getWaitUntilVisibleOfAll(selectionElements);
		for(WebElement selection : selectionElements) {
			if (selection.getText().equals(status)) {
				selection.click();
				break;
			}
		}
		
		getWaitUntilClickable(includeSelect);
		includeSelect.click();
		getWaitUntilVisibleOfAll(selectionElements);
		for(WebElement selection : selectionElements) {
			if (selection.getText().equals(include)) {
				selection.click();
				break;
			}
		}
		
		searchBtn.click();
		
		
		getWaitUntilVisibleOfAll(employeeSearchedValues);
	}
	
	public void verifySearchOnEmployeeClaims(String refId, String eventName, String status, String include) {
		searchEmployeeClaim(refId, eventName, status, include);
		
		String searchedEmployeeRefId = employeeSearchedValues.get(0).getText();
		String searchedEmployeeName = employeeSearchedValues.get(2).getText();
		String searchedEmployeeStatus = employeeSearchedValues.get(6).getText();
		
		Assert.assertEquals(searchedEmployeeRefId,refId);
		Assert.assertEquals(searchedEmployeeName,eventName);
		Assert.assertEquals(searchedEmployeeStatus,status);
	}
	
}
