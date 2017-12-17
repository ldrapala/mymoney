package pl.edu.pjatk.tau.luke.pages.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import pl.edu.pjatk.tau.luke.pages.LoginPage;
import pl.edu.pjatk.tau.luke.pages.RegisterPage;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @Before
    public void setup(){
        driver = new DriverFactory().get();
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }
    
    @After
    public void cleanup(){
        driver.quit();
    }

    @Given("^login form$")
    public void login_form() {
        loginPage.open();
        assertTrue(loginPage.isSingInButtonVisible());
    }

    @When("^we login with valid data$")
    public void we_login_with_valid_data() {
        loginPage.loginIntoAccount("email@com.pl", "password");
    }

    @Then("^we should see account page$")
    public void we_should_see_account_page() throws InterruptedException {
        registerPage.clickLogoutButton();
    }

}
