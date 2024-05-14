package pages.common;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.locators.Locators;
import pages.base.BasePage;

public class CompaniesPage extends BasePage {
    public CompaniesPage(WebDriver driver) {
        super(driver);
    }

    public void moveToJobs() {
        WebElement companiesElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(Locators.NAVBAR_JOBS));
        companiesElement.click();
    }

    public String checkIfActive(){
        WebElement activeCompaniesLink = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(Locators.NAVBAR_COMPANIES));
        return activeCompaniesLink.getAttribute("class");
    }

    public void enableSortByFollowers(){
        WebElement dropdownElement = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.SORT_FOLLOWERS));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Sort by followers");
    }

    public boolean checkSortedByFollowers(){
        WebElement companyListings = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.COMPANY_LISTINGS));

        List<WebElement> followersElements = companyListings.findElements(Locators.FOLLOWERS_COUNT);

        List<Integer> followers = new ArrayList<>();
        for (WebElement element : followersElements) {
            followers.add(Integer.parseInt(element.getText()));
        }

        for (int i = 0; i < followers.size() - 1; i++) {
            if (followers.get(i) < followers.get(i + 1)) {
                return false; // Not sorted in descending order
            }
        }
        
        return true;
    }

    public void nowHiring(){
        WebElement nowHiring = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.NOW_HIRING));
        nowHiring.click();
        WebElement waitUntilLoaded = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.TYPE_ID));
    }

    public boolean isEnabledNowHiring(){
        WebElement nowHiring = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.NOW_HIRING));
        String className = nowHiring.getAttribute("class");

        if (className.contains("active")) {
            return true;
        }

        return false;
    }

    public void selectNumberOfEmployees(String count){
        WebElement numberOfEmployees = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable((Locators.SELECT_NUMBER_OF_EMPLOYEES)));

        WebElement labelElement = numberOfEmployees.findElement(By.xpath("//label[contains(text(),'"+count+"')]"));
        WebElement checkbox = labelElement.findElement(Locators.CHECKBOX);
        checkbox.click();
    }

    public boolean isSelectNumberOfEmployees(String count){
        WebElement numberOfEmployees = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable((Locators.SELECT_NUMBER_OF_EMPLOYEES)));

        WebElement labelElement = numberOfEmployees.findElement(By.xpath("//label[contains(text(),'"+count+"')]"));
        WebElement checkbox = labelElement.findElement(Locators.CHECKBOX);
        boolean isSelected = checkbox.isSelected();

        return isSelected;
    }

    public boolean checkNumberOfEmpoyees(String count){
        WebElement companyListings = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.COMPANY_LISTINGS));

        List<WebElement> companies = companyListings.findElements(Locators.COMPANIES);
        
        //clicks on first company
        companies.get(0).click();

        WebElement employeeNumberElement =  new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='"+count+"']")));

        String employeeCount = employeeNumberElement.getText();

        if(employeeCount.equals(count)){
            return true;
        }

        // for (WebElement link : companies) {
        //     link.click();

        //     WebElement employeeNumberElement =  new WebDriverWait(driver, Duration.ofSeconds(10))
        //     .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='"+count+"']")));

        //     String employeeCount = employeeNumberElement.getText();

        //     if(!employeeCount.equals(count)){
        //         return false;
        //     }
        // }
        return false;
    }



    public String currentURL(){
        return driver.getCurrentUrl();
    }
}
