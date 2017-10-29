package pl.edu.pjatk.tau.luke.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pl.edu.pjatk.tau.luke.domain.Expenses;

/**
 *
 * @author s12165
 */
public class ExpensesServiceManager {

    private final ExpensesService expensesService;

    public ExpensesServiceManager(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    public List<Expenses> findRecordsByRegex(String regexString) {
        if (regexString == null) {
            return new ArrayList<>();
        }
        List<Expenses> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regexString);
        for (Expenses expenses : expensesService.getAll()) {
            Matcher matcher = pattern.matcher(expenses.getCategory());
            boolean matches = matcher.matches();
            if (matches) {
                result.add(expenses);
            }
        }
        return result;
    }

    public void deleteRecords(List<Expenses> objectsToRemove) {
        for (Expenses expenses : objectsToRemove) {
            expensesService.delete(expenses.getId());
        }
    }
}
