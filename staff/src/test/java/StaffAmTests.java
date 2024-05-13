import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.messages.AssertionMessages;
import constants.urls.URLS;
import pages.common.CompaniesPage;
import pages.common.HomePage;
import pages.common.JobsPage;

public class StaffAmTests extends BaseTest {

    // searches for job and enables sort by deadline functionality and check if it is ascending order
    @Test
    public void testJobSearchDeadlineSort() {
        HomePage homePage = new HomePage(driver);
        homePage.searchForJob("Software Developer");

        JobsPage searchResultPage = new JobsPage(driver);
        searchResultPage.checkIfActive();
        searchResultPage.enableSortByDeadline();
        boolean condition = searchResultPage.checkSortingByDeadline();
        assertTrue(AssertionMessages.DEADLINE_SORT, condition);
    }


    // searches for job with category and city filter 
    @Test
    public void testJobSearchCategoryAndCity() {
        HomePage homePage = new HomePage(driver);
        homePage.inputKeyword("Software Developer");
        homePage.selectCategory("Software development");
        homePage.selectCity("Yerevan");
        homePage.clickSearchButton();

        JobsPage searchResultPage = new JobsPage(driver);
        searchResultPage.checkIfActive();
        boolean condition1 = searchResultPage.isCategorySelected("Software development");
        boolean condition2 = searchResultPage.isCitySelected("Yerevan");
        assertTrue(AssertionMessages.CATEGORY_CITY, condition1&&condition2);
    }


    // In Companies page sorts it by followers and checks if it is sorted descending
    @Test
    public void testCompaniesSortByFollowers() {
        driver.get(URLS.COMPANIES_URL);
        CompaniesPage companiesPage = new CompaniesPage(driver);

        companiesPage.checkIfActive();
        companiesPage.enableSortByFollowers();
        boolean condition = companiesPage.checkSortedByFollowers();

        assertTrue(AssertionMessages.COMPANY_FOLLOWERS_SORT, condition);
    }


    // In Companies page clicks "Now Hiring" and filters with number of employees and checks it
    @Test
    public void testCompaniesFilterByNumberOfEmployees() {
        driver.get(URLS.COMPANIES_URL);
        CompaniesPage companiesPage = new CompaniesPage(driver);

        companiesPage.checkIfActive();
        companiesPage.nowHiring();
        companiesPage.selectNumberOfEmployees("more than 10000");
        boolean condition = companiesPage.checkNumberOfEmpoyees("more than 10000");

        assertTrue(AssertionMessages.COMPANY_EMPLOYEE_NUMBER, condition);
    }




    // HW3.2 tests IGNORE
    // @Test
    // public void testJobSearch() {
    //     HomePage homePage = new HomePage(driver);
    //     homePage.searchForJob("Software Developer");

    //     JobsPage searchResultPage = new JobsPage(driver);
    //     searchResultPage.checkIfActive();
    //     Assert.assertEquals(searchResultPage.SearchJobKeyword(), AssertionMessages.SEARCH_FOR_JOB);
    // }

    // @Test
    // public void testAll() {
    //     HomePage homePage = new HomePage(driver);
    //     homePage.searchForJob("Software Developer");

    //     JobsPage searchResultPage = new JobsPage(driver);
    //     searchResultPage.moveToCompanies();

    //     CompaniesPage companiesPage = new CompaniesPage(driver);
    //     Assert.assertEquals(companiesPage.currentURL(), AssertionMessages.CURRENT_URL_COMPANIES);
    // }

    // @Test
    // public void testAllReverse() {
    //     HomePage homePage = new HomePage(driver);
    //     homePage.moveToCompanies();

    //     CompaniesPage companiesPage = new CompaniesPage(driver);
    //     companiesPage.moveToJobs();

    //     JobsPage jobsPage = new JobsPage(driver);
    //     jobsPage.checkIfActive();
    //     Assert.assertEquals(jobsPage.currentURL(), AssertionMessages.CURRENT_URL_JOBS);
    // }

    // @Test
    // public void testHometoJobToHome() {
    //     HomePage homePage = new HomePage(driver);
    //     homePage.moveToJobs();

    //     JobsPage jobsPage = new JobsPage(driver);
    //     jobsPage.checkIfActive();
    //     jobsPage.moveToHome();

    //     homePage = new HomePage(driver);

    //     Assert.assertEquals(homePage.currentURL(), AssertionMessages.CURRENT_URL_HOME);
    // }

    // //searched from home page then modifies search in job page
    // @Test
    // public void testHomeToJobAndSearch() {
    //     HomePage homePage = new HomePage(driver);
    //     homePage.searchForJob("Software Developer");

    //     JobsPage searchResultPage = new JobsPage(driver);
    //     searchResultPage.checkIfActive();

    //     searchResultPage.searchForJob("DevOps");
    //     Assert.assertEquals(searchResultPage.SearchJobKeyword(), AssertionMessages.SEARCH_FOR_JOB + AssertionMessages.SEARCH_FOR_DIFFERENT_JOB);

    // }

}
