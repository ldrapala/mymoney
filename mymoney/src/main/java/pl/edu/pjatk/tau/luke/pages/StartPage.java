package pl.edu.pjatk.tau.luke.pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {

    private static final String URL = "http://automationpractice.com";

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/span/strong")
    private WebElement phoneNo;

    @FindBy(id = "homeslider")
    private WebElement loremSlider;

    @FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div")
    private WebElement basketLink;

    @FindBy(xpath = "//*[@id=\"home-page-tabs\"]/li[2]/a")
    private WebElement bestSellerButton;

    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[2]")
    private WebElement dressesButton;

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
    private WebElement signIn;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPhoneNo() {
        return phoneNo;
    }

    public WebElement getLoremSlider() {
        return loremSlider;
    }

    public WebElement getBasketLink() {
        return basketLink;
    }

    public void open() {
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public void clickBestSellers() {
        bestSellerButton.click();
    }

    public WebElement getDressesButton() {
        return dressesButton;
    }

    public void clickSignIn() {
        signIn.click();
    }

    public WebElement getDressesSubMenu() {
        return driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[1]"));
    }

    public List<WebElement> getProducts() {
        List<WebElement> elements = driver.findElement(By.cssSelector("#blockbestsellers"))
                .findElements(By.tagName("li"));
        List<WebElement> returnList = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                returnList.add(element);
            }
        }
        return returnList;
    }

    public boolean isPrintedChiffonDressVisible(List<WebElement> elements) {
        for (WebElement element : elements) {
            if (element.getText().contains("Printed Chiffon Dress")) {
                return true;
            }
        }
        return false;
    }

    public boolean isCasualDressesVisible() {
        return getDressesSubMenu().getText().contains("Casual Dresses");
    }

    public void setMouseOverDressesButton() {
        Actions actions = new Actions(driver);
        WebElement we = getDressesButton();
        actions.moveToElement(we)
                .build()
                .perform();
        WebElement dressesSubMenu = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(getDressesSubMenu()));
    }

    public boolean isDressesSubmenuVisible() {
        return getDressesButton().isDisplayed();
    }
}

