package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
//import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.net.MalformedURLException;

public class BaseTest {
    WebDriver driver = new ChromeDriver();
    @BeforeTest
    public void setUp() throws MalformedURLException {
        driver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
    private String chromeDriverFilePath() {
    	String currentDir = System.getProperty("user.dir");
        return currentDir + File.separator + "target" + File.separator + "chromedriver.exe";
    }
    
    public String filePath() {
        String currentDir = System.getProperty("user.dir");
        return currentDir + File.separator + "target" + File.separator + "data.xlsx";
    }
}
