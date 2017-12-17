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
import pl.edu.pjatk.tau.luke.util.StringUtils;

public class RegisterPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private String email;

   @Before
    public void setup(){
        driver = new DriverFactory().get();
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        email = StringUtils.createRandomString("@com.pl");
    }
    
    @After
    public void cleanup(){
        driver.quit();
    }

    @Given("^registration form$")
    public void registration_form() {
        loginPage.open();
        loginPage.setEmailInput(email);
        loginPage.clickSubmitCreateButon();
        assertTrue(registerPage.isRegisterButtonVisible());
    }

    @When("^we add an user with \"(.*)\", \"(.*)\",\"(.*)\", \"(.*)\", (\\d+), (\\d+), (\\d+),"
            + " \"(.*)\", \"(.*)\", \"(.*)\", (\\d+), \"(.*)\", (\\d+)$")
    public void we_add_an_user_with(String gender, String firstName, String lastName,
            String pass, int day, int month, int year, String street, String city, String state,
            int zip, String country, int mobile) {
        loginPage.open();
        loginPage.setEmailInput(email);
        loginPage.clickSubmitCreateButon();
        registerPage.setGender(gender);
        registerPage.setFirstName(firstName);
        registerPage.setLastName(lastName);
        registerPage.setPassword(pass);
        registerPage.setDateOfBirth(day, month, Integer.toString(year));
        registerPage.setStreet(street);
        registerPage.setCity(city);
        registerPage.setState(state);
        registerPage.setZipCode(Integer.toString(zip));
        registerPage.setCountry(country);
        registerPage.setMobilePhone(Integer.toString(mobile));
        registerPage.clickSubmitAccountButton();
        assertTrue(registerPage.isLogoutButtonVisible());
    }

    @Then("^we should see user's account$")
    public void we_should_see_user_s_account() {
        assertTrue(registerPage.isLogoutButtonVisible());
        registerPage.clickLogoutButton();
    }

}
