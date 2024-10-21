package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }
    
    public WebElement getWaitUntilVisibleOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public List<WebElement> getWaitUntilVisibleOfAll(List<WebElement> element) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    
    public WebElement getWaitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Actions getActions() {
        return actions;
    }
}