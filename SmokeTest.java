package placelab.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import placelab.pages.*;
import placelab.utilities.WebDriverSetup;

import java.time.Duration;


public class SmokeTest {
    public WebDriver driver;
    private String email = System.getProperty("email");
    private String password = System.getProperty("password");
    private String host = System.getProperty("host");
    private String username = System.getProperty("username");
    private String homepageUrl = System.getProperty("homepage");
    private String userRole = System.getProperty("userRole");
    private String singlePlaceSearchUrl = System.getProperty("singlePlaceSearchUrl");
    private String categoryValue = System.getProperty("categoryValue");
    private String reportName = System.getProperty("reportName");
    private String placeName = System.getProperty("placeName");
    private String followingLocation = System.getProperty("followingLocation");
    private String phone = System.getProperty("phone");
    private String expectedTitle;
    private Login login;
    private Homepage homepage;
    private SinglePlaceSearchPage singlePlaceSearch;
    private Reports reports;
    private CreatedReport createdReport;

    @Parameters({"browser"})

    @BeforeSuite(alwaysRun = true)
    public void openApp(String browser) {
        //init Driver
        driver = WebDriverSetup.getWebDriver(browser);
        login = new Login(driver);
        homepage = new Homepage(driver);
        singlePlaceSearch = new SinglePlaceSearchPage(driver);
        reports = new Reports(driver);
        createdReport = new CreatedReport(driver);

        //navigate to PlaceLab demo
        driver.navigate().to(host);
        login.verifyLoginPage(host);
    }

    @BeforeTest(alwaysRun = true, groups = {"Positive", "Negative"}, description = "Verify that user is able to Login on " + "PlaceLab App.")
    public void loginToPlaceLab() {
        login.enterEmail(email);
        login.enterPassword(password);
        login.clickLoginButton();

        //Verify valid login, displayed username and user-role
        homepage.verifyHomepage(homepageUrl, username, userRole);
    }

    @Test(priority = 1, groups = {"Positive"}, description = "Verify that user is on right page Single Place Search")
    public void openSinglePlaceSearchPage() {
        homepage.openSinglePlSearchPage();

        //Verify that Single Place Search page is opened
        expectedTitle = "Create Single Place Search Report";
        assert (singlePlaceSearch.getTitle().contains(expectedTitle));
        Assert.assertEquals(driver.getCurrentUrl(), singlePlaceSearchUrl);
    }

    /*
    //NEGATIVE TESTS-CREATE SINGLE PLACE SEARCH REPORT WITH ONLY 1 ENTRY(just for fun)
    @Test(priority = 2, groups = {"Negative"}, description = "Verify that user is not able to create Single Place Search report with only report name entry")
    public void testOnlyReportNameEntry() {
        singlePlaceSearch.enterReportName(reportName);
        Assert.assertEquals(singlePlaceSearch.buttonEnabled(), false, "Test failed, button is enabled!");
    }

    @Test(priority = 3, groups = {"Negative"}, description = "Verify that user is not able to create Single Place Search report with only place name entry")
    public void testOnlyPlaceNameEntry() {
        driver.navigate().refresh();
        singlePlaceSearch.enterPlaceName(placeName);
        Assert.assertEquals(singlePlaceSearch.buttonEnabled(), false, "Test failed, button is enabled!");
    }
    */
    //POSITIVE TESTS
    @Test(priority = 3, groups = {"Positive"}, description = "Verify that user can choose category in drop-down list")
    public void testChooseCategory() {
        singlePlaceSearch.chooseCategory(categoryValue);

        Assert.assertEquals(singlePlaceSearch.selectedCategoryDisplayed(), categoryValue, "Selected category is not displayed");
    }

    @Test(priority = 4, groups = {"Positive"}, description = "Verify that user is able to create report with all field entered",
            suiteName = "Smoke Test")
    public void testAllFieldEntered() {
        driver.navigate().refresh();
        singlePlaceSearch.enterReportName(reportName);
        singlePlaceSearch.enterPlaceName(placeName);
        singlePlaceSearch.enterPhone(phone);
        singlePlaceSearch.enterFollowingLocation(followingLocation);
        singlePlaceSearch.chooseCategory(categoryValue);
        singlePlaceSearch.confirmLocationButton();

        //Verify create report button is enabled
        Assert.assertEquals(singlePlaceSearch.buttonEnabled(), true, "Test failed, button is  disabled!");
        singlePlaceSearch.createReportButton();

        //Wait until the new report is created
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

    }

    @Test(priority = 5, groups = {"Positive"}, description = "Verify that report is created")
    public void testIsReportCreated() {

        WebElement report = reports.getCreatedReport(reportName);
        Assert.assertEquals(report.getText(), reportName, "Report is not created");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.placelab.com/queries", "Wrong Url opened, report is not created");

    }

    @Test(priority = 6, groups = {"Positive"}, description = "Verify that user is able to open created report page")
    public void testCreatedReport() {
        reports.openCreatedReport(reportName);
        //Verify is right page opened (created report page)
        String expectedTitle = "PlaceLab - " + reportName;
        Assert.assertEquals(createdReport.getPageTitle(), expectedTitle, "Test fail, wrong report opened!");

        createdReport.getAnalysisInfoWidget();
    }

    @Test(priority = 7, groups = {"Positive"}, description = "Verify that created report has all the necessary widgets")
    public void testPresenceOfWidgets() {
        Assert.assertEquals(createdReport.getAnalysisInfoWidget(), "Analysis Info", "There is no Analysis Info widget!");
        Assert.assertEquals(createdReport.getSimilarityScoreWidget(), "Similarity Score", "There is no Similarity Score widget!");
        Assert.assertEquals(createdReport.getSimilarityDegreeWidget(), "Name Similarity Degree (Reference)", "There is no Name Similarity Degree (Reference) widget!");
        Assert.assertEquals(createdReport.getAttributeMatchWidget(), "Attribute Match", "There is no Attribute Match widget!");
        Assert.assertEquals(createdReport.getAttributeMatchWidget(), "Attribute Match", "There is no Attribute Match widget!");
        Assert.assertEquals(createdReport.getAttributeCompleteness(), "Attribute Completeness", "There is no Attribute Completeness widget!");
        Assert.assertEquals(createdReport.getPhoneFrequency(), "Phone Frequency", "There is no Phone Frequency widget!");
        Assert.assertEquals(createdReport.getWebsiteFrequency(), "Website Frequency", "There is no Website Frequency widget!");
        Assert.assertEquals(createdReport.getCategoryFrequency(), "Category Frequency", "There is no Category Frequency widget!");
        Assert.assertEquals(createdReport.getDistanceAnalysis(), "Distance Analysis", "There is no Distance Analysis widget!");
        Assert.assertEquals(createdReport.getAddressFrequency(), "Address Frequency", "There is no Address Frequency widget!");
        Assert.assertEquals(createdReport.getPlaceLocation(), "Place Location", "There is no Place Location widget!");
        Assert.assertEquals(createdReport.getPlaceLocationGoogleMaps(), "Place Location - Google Maps", "There is no Place Location - Google Maps widget!");
        Assert.assertEquals(createdReport.getRawData(), "Raw data", "There is no Raw data widget!");

        driver.navigate().back();
    }

    @Test(priority = 8, groups = {"Positive"}, description = "Delete created report")
    private void deleteCreatedReport() {
        reports.deleteCreatedReport(reportName);
        //Verify that query is successfully deleted
        assert (reports.successfullyDeletedQuery().contains("Successfully deleted selected queries"));
    }

    @AfterTest(alwaysRun = true, description = "Verify that user is able to sing out from "+
            "PlaceLab App.")
    private void singOut() {
        login.singOutButton();

        //Verify successful logout
        Assert.assertEquals(driver.getCurrentUrl(), host, "Unsuccessful logout");
    }

    @AfterSuite(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

}
