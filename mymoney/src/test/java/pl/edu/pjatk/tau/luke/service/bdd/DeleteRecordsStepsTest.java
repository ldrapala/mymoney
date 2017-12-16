package pl.edu.pjatk.tau.luke.service.bdd;

import com.google.common.base.Optional;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Arrays;
import java.util.Date;
import static org.junit.Assert.*;
import pl.edu.pjatk.tau.luke.domain.Expenses;
import pl.edu.pjatk.tau.luke.service.ExpensesService;
import pl.edu.pjatk.tau.luke.service.ExpensesServiceImpl;
import pl.edu.pjatk.tau.luke.service.ExpensesServiceManager;

public class DeleteRecordsStepsTest {

    private ExpensesService dao;
    private Expenses someData;
    private Expenses newData;
    private ExpensesServiceManager service;

    @Before
    public void setup() {
        dao = new ExpensesServiceImpl();
        service = new ExpensesServiceManager(dao);
        Expenses expenses = new Expenses();
        someData = dao.add(expenses);
    }

    @Given("^we add new expenses with (\\d+), (.*), (\\d+)-(\\d+)-(\\d+), (.*)$")
    public void we_add_new_expenses_with(int amount, String category, int year, int month, int day, String details) {
        Expenses expenses = new Expenses();
        expenses.setAmount(amount);
        expenses.setCategory(category);
        expenses.setDate(new Date(year, month, day));
        expenses.setDetails(details);
        newData = dao.add(expenses);
        assertEquals(amount, newData.getAmount());
        assertEquals(category, newData.getCategory());
        assertEquals(new Date(year, month, day), newData.getDate());
        assertEquals(details, newData.getDetails());
        assertEquals(2, dao.getAll().size());
    }

    @When("^we delete new expenses$")
    public void we_delete_new_expenses() {
        Optional<Expenses> expenses = dao.get(newData.getId());
        assertTrue(expenses.isPresent());
        service.deleteRecords(Arrays.asList(expenses.get()));
    }

    @Then("^new expenses should not be visible in db$")
    public void new_expenses_should_not_be_visible_in_db() {
        assertEquals(1, dao.getAll().size());
        Optional<Expenses> expenses = dao.get(newData.getId());
        assertTrue(!expenses.isPresent());
    }

    @Then("^the previous data should stay untouchable$")
    public void the_prevoius_data_should_stay_untouchable() {
        Optional<Expenses> previousData = dao.get(someData.getId());
        assertEquals(previousData.get().getAmount(), someData.getAmount());
        assertEquals(previousData.get().getCategory(), someData.getCategory());
        assertEquals(previousData.get().getDate(), someData.getDate());
        assertEquals(previousData.get().getDetails(), someData.getDetails());
    }
}
