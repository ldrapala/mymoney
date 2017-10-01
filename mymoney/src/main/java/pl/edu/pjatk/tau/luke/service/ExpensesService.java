package pl.edu.pjatk.tau.luke.service;

import java.util.List;
import pl.edu.pjatk.tau.luke.domain.Expenses;

/**
 *
 * @author s12165
 */
public interface ExpensesService {
    
    void add(Expenses expenses);
    
    Expenses get(int id) throws IllegalArgumentException;
    
    List<Expenses> getAll();
    
    void update(Expenses expenses);
    
    void delete(int id);
    
}
