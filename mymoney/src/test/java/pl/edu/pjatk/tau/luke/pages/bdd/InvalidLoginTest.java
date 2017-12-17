package pl.edu.pjatk.tau.luke.pages.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import pl.edu.pjatk.tau.luke.pages.LoginPage;

public class InvalidLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = new DriverFactory().get();
        loginPage = new LoginPage(driver);
    }

    @After
    public void cleanup() {
        driver.quit();
    }

    @Given("^login page form$")
    public void login_page_form() {
        loginPage.open();
        assertTrue(loginPage.isSingInButtonVisible());
    }

    @When("^we login with invalid data \\(\"(.*)\" and \"(.*)\"\\)$")
    public void we_login_with_invalid_data_and(String email, String pass) {
        loginPage.loginIntoAccount(email, pass);
        String url = "http://automationpractice.com/index.php?controller=authentication";
        assertEquals(url, driver.getCurrentUrl());
    }

    @Then("^we should see error message$")
    public void we_should_see_error_message() {
        assertTrue(loginPage.hasAuthenticationFailed());
    }

}
