package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Reports {
    private WebDriver driver;

    public Reports(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getCreatedReport(String reportName) {
        String path = "//div[@class='query_name']/a[text()='" + reportName + "']";
        return this.driver.findElement(By.xpath(path));
    }

    public void openCreatedReport(String reportName) {
        WebElement createdReport = getCreatedReport(reportName);
        new WebDriverWait(this.driver, Duration.ofSeconds(120)).until(ExpectedConditions.elementToBeClickable(createdReport)).click();
    }

    private void checkCreatedReport(String reportName) {
        String checkboxValue = findValueOfCreatedReport(reportName);
        String path = "//*[@name='queries' and @value='" + checkboxValue + "']/./..";
        this.driver.findElement(By.xpath(path)).click();
    }

    private String findValueOfCreatedReport(String reportName) {
        String path = "//div[@class='query_name']/a[text()='" + reportName + "']";
        String id = this.driver.findElement(By.xpath(path)).getAttribute("id");
        String value = id.substring(8);
        return value;
    }

    public void deleteCreatedReport(String reportName) {
        checkCreatedReport(reportName);
        this.driver.findElement(By.id("action-delete")).click();
        confirmDelete();

    }

    private void confirmDelete() {
        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("confirm-link"))).click();
    }

    public String successfullyDeletedQuery() {
        return this.driver.findElement(By.id("alert-place")).getText();
    }


}
