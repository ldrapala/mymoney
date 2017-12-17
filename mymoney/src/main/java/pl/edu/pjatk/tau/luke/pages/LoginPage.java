package pl.edu.pjatk.tau.luke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        PageFactory.initElements(driver, this);
    }

    public void setEmailInput(String email) {
        driver.findElement(By.id("email_create")).sendKeys(email);
    }

    public boolean isErrorMessageVisible() {
        return driver.findElement(By.cssSelector("#center_column > div")).isDisplayed();
    }

    public void clickSubmitCreateButon() {
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public void loginIntoAccount(String email, String pass) {
        WebElement emailInputField = driver.findElement(By.id("email"));
        emailInputField.clear();
        emailInputField.sendKeys(email);
        WebElement passwordInputField = driver.findElement(By.id("passwd"));
        passwordInputField.clear();
        passwordInputField.sendKeys(pass);
        getSignInButton().click();
    }

    public void clickSingInButton() {
        getSignInButton().click();
    }

    public boolean isSingInButtonVisible() {
        return getSignInButton().isDisplayed();
    }

    public boolean hasAuthenticationFailed() {
        WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOf(driver.findElement(By.cssSelector("#center_column > div.alert.alert-danger"))));
        return element.isDisplayed();
    }

    public boolean isLoginSuccessful() {
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions
                .visibilityOf(driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul"))));
        return element.isDisplayed();
    }

    private WebElement getSignInButton() {
        return driver.findElement(By.id("SubmitLogin"));
    }
}
