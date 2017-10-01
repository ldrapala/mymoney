package pl.edu.pjatk.tau.luke.service;

import com.google.common.base.Optional;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pjatk.tau.luke.domain.Expenses;

/**
 *
 * @author s12165
 */
public class ExpensesServiceTest {

    @Test
    public void add_with_success() {
        ExpensesService unitUnderTest = getExpensesService();
        Expenses expenses = new Expenses();
        expenses.setAmount(400 * 100);
        expenses.setDate(new Date());
        expenses.setCategory("Gift");
        expenses.setDetails("Watch");
        Expenses entity = unitUnderTest.add(expenses);
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(expenses.getAmount(), entity.getAmount());
        assertEquals(expenses.getDate(), entity.getDate());
        assertEquals(expenses.getCategory(), entity.getCategory());
        assertEquals(expenses.getDetails(), entity.getDetails());
        assertEquals(1, unitUnderTest.getAll().size());
    }

    @Test
    public void get_with_success() {
        ExpensesService unitUnderTest = getExpensesService();
        Expenses expenses = new Expenses();
        expenses.setAmount(400 * 100);
        expenses.setDate(new Date());
        expenses.setCategory("Gift");
        expenses.setDetails("Watch");
        Expenses entity = unitUnderTest.add(expenses);
        Optional<Expenses> resultFromDB = unitUnderTest.get(entity.getId());
        assertTrue(resultFromDB.isPresent());
        assertEquals(expenses.getAmount(), entity.getAmount());
        assertEquals(expenses.getDate(), entity.getDate());
        assertEquals(expenses.getCategory(), entity.getCategory());
        assertEquals(expenses.getDetails(), entity.getDetails());
    }

    @Test
    public void get_with_failure() {
        ExpensesService unitUnderTest = getExpensesService();
        Optional<Expenses> resultFromDB = unitUnderTest.get(Integer.MAX_VALUE);
        assertFalse(resultFromDB.isPresent());
    }

    @Test
    public void getAll_with_results() {
        ExpensesService unitUnderTest = getExpensesService();
        Expenses expenses = new Expenses();
        unitUnderTest.add(expenses);
        expenses = new Expenses();
        unitUnderTest.add(expenses);
        assertEquals(2, unitUnderTest.getAll().size());
    }

    @Test
    public void getAll_with_no_result() {
        ExpensesService unitUnderTest = getExpensesService();
        assertEquals(0, unitUnderTest.getAll().size());
    }

    @Test
    public void update_with_success() {
        ExpensesService unitUnderTest = getExpensesService();
        Expenses expenses = new Expenses();
        expenses.setAmount(400 * 100);
        expenses.setDate(new Date());
        expenses.setCategory("Gift");
        expenses.setDetails("Watch");
        unitUnderTest.add(expenses);
        expenses.setAmount(350*100);
        Expenses entityUpdated = unitUnderTest.update(expenses);
        assertNotNull(entityUpdated);
        assertNotNull(entityUpdated.getId());
        assertEquals(expenses.getAmount(), entityUpdated.getAmount());
        assertEquals(expenses.getDate(), entityUpdated.getDate());
        assertEquals(expenses.getCategory(), entityUpdated.getCategory());
        assertEquals(expenses.getDetails(), entityUpdated.getDetails());
        assertEquals(1, unitUnderTest.getAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_with_failure() {
        ExpensesService unitUnderTest = getExpensesService();
        unitUnderTest.update(new Expenses());
    }

    @Test
    public void delete_with_success() {
        ExpensesService unitUnderTest = getExpensesService();
        Expenses entity = unitUnderTest.add(new Expenses());
        int beforeDelete = unitUnderTest.getAll().size();
        unitUnderTest.delete(entity.getId());
        Optional<Expenses> empty = unitUnderTest.get(entity.getId());
        int afterDelete = unitUnderTest.getAll().size();
        assertFalse(empty.isPresent());
        assertTrue(beforeDelete == afterDelete + 1);
    }
    @Test
    public void delete_with_failure() {
        ExpensesService unitUnderTest = getExpensesService();
        unitUnderTest.add(new Expenses());
        int beforeDelete = unitUnderTest.getAll().size();
        unitUnderTest.delete(Integer.MIN_VALUE);
        int afterDelete = unitUnderTest.getAll().size();
        assertTrue(beforeDelete == afterDelete);
    }

    private ExpensesService getExpensesService() {
        return new ExpensesServiceImpl();
    }
}