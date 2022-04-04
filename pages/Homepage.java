package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;


public class Homepage {
    private WebDriver driver;

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyHomepage(String homepageUrl, String username, String userRole) {
        Assert.assertEquals(this.driver.getCurrentUrl(), homepageUrl);
        verifyUsername(username);
        verifyUserRole(userRole);
    }

    private void verifyUsername(final String username) {
        try {
            WebElement user = this.driver.findElement(By.id("user-name"));
            assert (user.getText().contains(username));
        } catch (Exception e) {
            throw new RuntimeException("Expected user is not logged in!");
        }
    }

    private void verifyUserRole(final String role) {
        WebElement userRole = this.driver.findElement(By.id("user-role"));
        Assert.assertEquals(userRole.getText(), role);
    }

    public void openSinglePlSearchPage() {
        this.driver.findElement(By.xpath("//a[@class='dropdown-toggle create-dropdown']")).click();
        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href ='/places/single_place_searches/new']"))).click();
    }


}
