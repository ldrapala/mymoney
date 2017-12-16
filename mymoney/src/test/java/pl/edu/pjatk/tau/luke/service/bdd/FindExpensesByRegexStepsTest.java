package pl.edu.pjatk.tau.luke.service.bdd;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import pl.edu.pjatk.tau.luke.domain.Expenses;
import pl.edu.pjatk.tau.luke.service.ExpensesService;
import pl.edu.pjatk.tau.luke.service.ExpensesServiceImpl;
import pl.edu.pjatk.tau.luke.service.ExpensesServiceManager;

public class FindExpensesByRegexStepsTest {

    private ExpensesService dao;
    private List<Expenses> findRecordsByRegex;
    private Expenses expenses1;
    private Expenses expenses2;

    @Before
    public void setup() {
        dao = new ExpensesServiceImpl();
        expenses1 = new Expenses();
        expenses1.setAmount(500000);
        expenses1.setCategory("Electronics");
        expenses1.setDate(new Date(2017, 12, 15));
        expenses1.setDetails("Monitor");
        expenses2 = new Expenses();
        expenses2.setAmount(200000);
        expenses2.setCategory("Toys");
        expenses2.setDate(new Date(2017, 10, 27));
        expenses2.setDetails("Lego bricks");
    }

    @Given("^there is one expenses$")
    public void there_is_one_expenses() {
        Expenses entity = dao.add(expenses1);
        assertEquals(expenses1.getAmount(), entity.getAmount());
        assertEquals(expenses1.getCategory(), entity.getCategory());
        assertEquals(expenses1.getDate(), entity.getDate());
        assertEquals(expenses1.getDetails(), entity.getDetails());

    }

    @Given("^another one with different category$")
    public void another_one_with_different_category() {
        Expenses entity = dao.add(expenses2);
        assertEquals(expenses2.getAmount(), entity.getAmount());
        assertEquals(expenses2.getCategory(), entity.getCategory());
        assertEquals(expenses2.getDate(), entity.getDate());
        assertEquals(expenses2.getDetails(), entity.getDetails());
    }

    @When("^beginning of his category is passed as \"(.*)\"$")
    public void beginning_of_his_category_is_passed_as(String regex) {
        ExpensesServiceManager service = new ExpensesServiceManager(dao);
        findRecordsByRegex = service.findRecordsByRegex(regex);
        assertFalse(findRecordsByRegex.isEmpty());
    }

    @Then("^we should find this expenses$")
    public void we_should_find_this_expenses() {
        Expenses entity = findRecordsByRegex.get(0);
        assertEquals("Toys", entity.getCategory());
    }

    @Then("^I should not see more than one expenses$")
    public void i_should_not_see_more_than_one_expenses() {
        assertEquals(1, findRecordsByRegex.size());
    }
}
