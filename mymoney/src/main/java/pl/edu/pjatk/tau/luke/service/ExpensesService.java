package pl.edu.pjatk.tau.luke.service;

import com.google.common.base.Optional;
import java.util.List;
import pl.edu.pjatk.tau.luke.domain.Expenses;

/**
 *
 * @author s12165
 */
public interface ExpensesService {
    
    Expenses add(Expenses expenses);
    
    Optional<Expenses> get(int id);
    
    List<Expenses> getAll();
    
    Expenses update(Expenses expenses) throws IllegalArgumentException;
    
    void delete(int id);
    
}
