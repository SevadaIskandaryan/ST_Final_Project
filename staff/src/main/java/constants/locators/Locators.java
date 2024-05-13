package constants.locators;

import org.openqa.selenium.By;

public class Locators {
    public static final By SEARCH_INPUT_KEYWORD = By.id("jobsfilter-key_word");
    public static final By SEARCH_SELECT_CATEGORY = By.id("jobsfilter-category");
    public static final By SEARCH_SELECT_CITY = By.id("jobsfilter-job_city");
    public static final By SELECT_NUMBER_OF_EMPLOYEES = By.id("companiesstruct-employees_number");
    public static final By SORT_FOLLOWERS = By.id("select_sort");
    public static final By COMPANY_LISTINGS = By.id("w0");
    public static final By FOLLOWERS_COUNT = By.className("followers_count");

    
    public static final By SEARCH_BUTTON = By.cssSelector("div.col-lg-3.col-sm-3.search-btn button");

    public static final By SEARCH_BUTTON_JOBS = By.id("btn_search_keyword");

    public static final By NAVBAR_COMPANIES = By.cssSelector("#w2 > li:nth-child(3)");
    public static final By NAVBAR_COMPANIES_LINK = By.cssSelector("#w2 > li:nth-child(3) > a");
    public static final By NAVBAR_JOBS = By.cssSelector("#w2 > li:nth-child(1)");
    public static final By NAVBAR_JOBS_LINK = By.cssSelector("#w2 > li.active");
    //public static final By NAVBAR_COMPANIES_FROM_HOME = By.cssSelector("#w1 > li:nth-child(3)");
    public static final By NAVBAR_COMPANIES_FROM_HOME = By.xpath("//a[@class='hs_nav_link'][normalize-space()='Companies']");
    public static final By NAVBAR_JOBS_FROM_HOME = By.cssSelector("#w1 > li:nth-child(1)");
    public static final By NAVBAR_HOME = By.cssSelector("#w1 > div.navbar-header > a.navbar-brand.hs_navbar_light_logo");

}
