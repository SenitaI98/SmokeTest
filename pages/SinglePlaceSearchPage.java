package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SinglePlaceSearchPage {
    private WebDriver driver;

    public SinglePlaceSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return this.driver.findElement(By.xpath("//div[@class ='create-report-header']")).getText();
    }

    public void enterReportName(String reportName) {
        this.driver.findElement(By.xpath("//div[@class='floatlabel-wrapper']/input[@name='name']")).sendKeys(reportName);
    }

    public void enterPlaceName(String placeName) {
        this.driver.findElement(By.id("single_text")).sendKeys(placeName);
    }

    public void enterPhone(String phone) {
        this.driver.findElement(By.id("single_phone")).sendKeys(phone);
    }

    public void enterFollowingLocation(final String followingLocation) {
        WebElement location = this.driver.findElement(By.xpath("//div[@class='form-group']/input"));
        location.sendKeys(followingLocation);
        clickDropdown();

    }

    private void clickDropdown() {
        new WebDriverWait(this.driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='typeahead dropdown-menu']"))).click();
    }

    public boolean buttonEnabled() {
        WebElement button = this.driver.findElement(By.xpath("//button[@class='btn large-btn run-btn run-all-btn']"));
        return button.isEnabled();
    }

    public void createReportButton() {
        this.driver.findElement(By.xpath("//button[@class='btn large-btn run-btn run-all-btn']")).click();
    }

    public void confirmLocationButton() {
        this.driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button[@class = 'btn default-btn plab']")).click();
    }

    public void chooseCategory(String value) {
        openDropdownMeni();
        //Locating element by text value
        String path = "//input[@type='radio' and @value='" + value + "']/./..";
        WebElement category = this.driver.findElement(By.xpath(path));
        category.click();

    }

    private void openDropdownMeni() {
        this.driver.findElement(By.className("btn-group")).click();
    }

    public String selectedCategoryDisplayed() {
        WebElement selectedCategory = this.driver.findElement(By.className("multiselect-selected-text"));
        return selectedCategory.getText();
    }

}
