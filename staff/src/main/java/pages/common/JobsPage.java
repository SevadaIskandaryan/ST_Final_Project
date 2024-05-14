package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.locators.Locators;
import pages.base.BasePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

public class JobsPage extends BasePage {
    public JobsPage(WebDriver driver) {
        super(driver);
    }

    public String SearchJobKeyword() {
        WebElement inputField = driver.findElement(Locators.SEARCH_INPUT_KEYWORD);
        return inputField.getAttribute("value");
    }

    public void searchForJob(String keyword) {
        WebElement inputField = driver.findElement(Locators.SEARCH_INPUT_KEYWORD);
        //inputField.clear();
        inputField.sendKeys(keyword);
        clickSearchButton();
    }

    public void clickSearchButton() {
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_BUTTON_JOBS));
        button.click();
    }

    public void moveToCompanies() {
        WebElement companiesElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(Locators.NAVBAR_COMPANIES));
        companiesElement.click();
    }

    public void moveToHome() {
        WebElement homeElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(Locators.NAVBAR_HOME));
        homeElement.click();
    }

    public boolean checkIfActive(){
        boolean isCompaniesActive = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.attributeContains(Locators.NAVBAR_JOBS_LINK, "class", "active"));
        return isCompaniesActive;
    }


    public String currentURL(){
        return driver.getCurrentUrl();
    }

    public void enableSortByDeadline(){
        WebElement dropdownElement = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.SORT_DEADLINE));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Sort by deadline");
    }

    public static boolean isSorted(List<String> dates) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

        try {
            Date previousDate = null;
            for (String dateString : dates) {
                Date currentDate = dateFormat.parse(dateString);

                if (previousDate != null && currentDate.before(previousDate)) {
                    return false; // Dates are not sorted
                }

                previousDate = currentDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Error occurred during parsing
        }

        return true; // Dates are sorted
    }

    public boolean checkSortingByDeadline(){
        WebElement jobListings = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.JOB_LISTINGS));

        List<WebElement> deadlineElements = jobListings.findElements(Locators.DEADLINE_ITEMS);

        List<String> deadlines = new ArrayList<>();
        for (WebElement element : deadlineElements) {
            deadlines.add(element.getText());
        }

        boolean sorted = isSorted(deadlines);

        return sorted;
    }

    public boolean isCategorySelected(String category){
        WebElement categories = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_SELECT_CATEGORY));

        WebElement labelElement = categories.findElement(By.xpath("//en[normalize-space()='" + category + "']"));
        
        WebElement parent = labelElement.findElement(Locators.PARENT_ELEMENT);

        // Find the input element within the label
        WebElement checkbox = parent.findElement(Locators.CHECKBOX);
        boolean isSelected = checkbox.isSelected();
        return isSelected;
    }

    public boolean isCitySelected(String city){
        WebElement cities = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_SELECT_CITY));

        WebElement labelElement = cities.findElement(By.xpath("//label[contains(text(),'"+city+"')]"));
        
        WebElement parent = labelElement.findElement(Locators.PARENT_ELEMENT);

        // Find the input element within the label
        WebElement checkbox = parent.findElement(Locators.CHECKBOX);
        boolean isSelected = checkbox.isSelected();
        return isSelected;
    }


    public boolean isCityInResults(String city){
        WebElement jobListings = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(Locators.JOB_LISTINGS));

        List<WebElement> locationElements = jobListings.findElements(Locators.CITY_LOCATION);
        List<String> cities = new ArrayList<>();
        for (WebElement element : locationElements) {
            cities.add(element.getText());
        }

        for (int i = 0; i < cities.size(); i++) {
            if (!cities.get(i).contains(city)) {
                return false;
            }
        }

        return true;
    }
}
