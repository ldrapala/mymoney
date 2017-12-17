package pl.edu.pjatk.tau.luke.pages.bdd;

import com.google.common.base.Supplier;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class DriverFactory implements Supplier<WebDriver> {

    private WebDriver driver;

    @Override
    public WebDriver get() {
            PhantomJsDriverManager.getInstance().setup();
            driver = new PhantomJSDriver();
            driver.manage()
                    .timeouts()
                    .implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        return driver;
    }
    
}
