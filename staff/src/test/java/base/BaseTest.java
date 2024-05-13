package base;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import constants.urls.URLS;

import java.net.MalformedURLException;
import java.net.URL;
import pages.common.HomePage;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    FirefoxOptions firefoxOptions;

    @SuppressWarnings("deprecation")
    @BeforeClass
    public void setUp() {
        boolean remoteWebDriverEnable = false;

        if(remoteWebDriverEnable){
            // Set up WebDriver
            firefoxOptions = new FirefoxOptions();
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
            driver = new ChromeDriver();
        }
        
        moveToHomePage();
    }

    @BeforeMethod
    public void moveToHomePage(){
        driver.get(URLS.HOME_URL);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot camera = (TakesScreenshot) driver;
                File screenshot = camera.getScreenshotAs(OutputType.FILE);
                Files.move(screenshot, new File("src/screenshots/" +  result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
