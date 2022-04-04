package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyLoginPage(String host) {
        Assert.assertEquals(this.driver.getCurrentUrl(), host);
        Assert.assertEquals(this.driver.getTitle(), "PlaceLab");
        getLogo().isDisplayed();
    }

    private WebElement getLogo() {
        WebElement logo = this.driver.findElement(By.xpath("//div[@id= 'login']/img"));
        return logo;
    }

    public void enterEmail(final String email) {
        this.driver.findElement(By.id("email")).sendKeys(email);
    }

    public void enterPassword(final String password) {
        this.driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickLoginButton() {
        this.driver.findElement(By.name("commit")).click();
    }


    //SING OUT Methods
    public void singOutButton() {
        dropDownSingOut();
        this.driver.findElement(By.xpath("//a[@href='/users/3659/logout']")).click();
    }

    private void dropDownSingOut() {
        this.driver.findElement(By.id("actions-nav-item")).click();
    }
}
