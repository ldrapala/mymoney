package pl.edu.pjatk.tau.luke.service.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        features = {"classpath:DeleteRecords.feature",
        "classpath:FindExpensesByRegex.feature"}
)

public class StartBddTest {
    
}
