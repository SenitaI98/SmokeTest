package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreatedReport {
    private WebDriver driver;

    public CreatedReport(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public String getAnalysisInfoWidget() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Analysis Info']")).getText();
    }

    public String getSimilarityScoreWidget() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Similarity Score']")).getText();
    }

    public String getSimilarityDegreeWidget() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Name Similarity Degree (Reference)']")).getText();
    }

    public String getAttributeMatchWidget() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Attribute Match']")).getText();
    }

    public String getAttributeCompleteness() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Attribute Completeness']")).getText();
    }

    public String getWebsiteFrequency() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Website Frequency']")).getText();
    }

    public String getPhoneFrequency() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Phone Frequency']")).getText();
    }

    public String getCategoryFrequency() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Category Frequency']")).getText();
    }

    public String getDistanceAnalysis() {
        return this.driver.findElement(By.xpath("//h5[@class='no-capitalize']/span[text()='Distance Analysis']")).getText();
    }

    public String getPlaceLocation() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Place Location']")).getText();
    }

    public String getAddressFrequency() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5/span[text()='Address Frequency']")).getText();
    }

    public String getPlaceLocationGoogleMaps() {
        return this.driver.findElement(By.xpath("//h5[@class='no-capitalize']/span[text()='Place Location - Google Maps']")).getText();
    }

    public String getRawData() {
        return this.driver.findElement(By.xpath("//div[@class='widget-title']/h5[text()='Raw data']")).getText();
    }

}
