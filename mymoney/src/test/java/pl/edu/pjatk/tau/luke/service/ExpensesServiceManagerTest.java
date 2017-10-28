package pl.edu.pjatk.tau.luke.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import pl.edu.pjatk.tau.luke.domain.Expenses;
import static org.mockito.Mockito.*;
import org.mockito.internal.verification.AtLeast;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 *
 * @author s12165
 */
@RunWith(MockitoJUnitRunner.class)
public class ExpensesServiceManagerTest {
    
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 
    private ExpensesServiceManager unitUnderTest;
    
    @Mock
    private ExpensesService expensesService;
    
    @Before
    public void setUp() {
        unitUnderTest = new ExpensesServiceManager(expensesService);
    }
    
    /**
     * Test of findRecordsByRegex method, of class ExpensesServiceManager.
     */
    @Test
    public void findRecordsByNull_returnNoResults() {
        List<Expenses> results = unitUnderTest.findRecordsByRegex(null);
        assertTrue(results.isEmpty());
    }
    
    @Test
    public void findRecordsByEmptyString_returnAllResults() {
        when(expensesService.getAll()).thenReturn(getData("First", "Second"));
        List<Expenses> results = unitUnderTest.findRecordsByRegex("...s.");
        assertTrue(results.size() == 1);
    }
    
    private List<Expenses> getData(String...categories){
        List<Expenses> results = new ArrayList<>();
        for (String category : categories) {
            Expenses expenses = new Expenses();
            expenses.setCategory(category);
            results.add(expenses);
        }
        return results;
    }

    /**
     * Test of deleteRecords method, of class ExpensesServiceManager.
     */
    @Test
    public void deleteRecords() {
    }
}