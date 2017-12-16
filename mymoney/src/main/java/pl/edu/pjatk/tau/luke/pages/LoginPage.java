package pl.edu.pjatk.tau.luke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    private final WebDriver driver;

    @FindBy(id = "email_create")
    private WebElement emailInput;

    @FindBy(id = "email")
    private WebElement credentialsEmail;

    @FindBy(id = "passwd")
    private WebElement credentialsPassword;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public void setEmailInput(String email) {
        emailInput.sendKeys(email);
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector("#center_column > div"));
    }

    public void clickSubmitCreateButon() {
        driver.findElement(By.id("SubmitCreate")).click();
    }

    public void loginIntoAccount(String email, String pass) {
        credentialsEmail.clear();
        credentialsEmail.sendKeys(email);

        credentialsPassword.clear();
        credentialsPassword.sendKeys(pass);
        clickSingIn();
    }

    public void clickSingIn() {
        driver.findElement(By.id("SubmitLogin")).click();
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
}