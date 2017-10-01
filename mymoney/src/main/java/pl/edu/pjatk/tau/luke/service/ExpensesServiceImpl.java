package pl.edu.pjatk.tau.luke.service;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.edu.pjatk.tau.luke.domain.Expenses;

/**
 *
 * @author s12165
 */
public class ExpensesServiceImpl implements ExpensesService {
    
    private final Map<Integer, Expenses> db = new HashMap<>();
    private int idCounter = 1;

    @Override
    public Expenses add(Expenses expenses) {
        expenses.setId(idCounter);
        db.put(idCounter, expenses);
        idCounter++;
        return expenses;
    }

    @Override
    public Optional<Expenses> get(int id) {
        Expenses entity = db.get(id);
        return Optional.fromNullable(entity);
    }

    @Override
    public List<Expenses> getAll() {
        List<Expenses> result = new ArrayList<>();
        result.addAll(db.values());
        return result;
    }

    @Override
    public Expenses update(Expenses expenses) throws IllegalArgumentException {
        if(!db.containsKey(expenses.getId())){
            throw new IllegalArgumentException("No id in database: "+expenses.getId());
        }
        return db.get(expenses.getId());
    }

    @Override
    public void delete(int id) {
        db.remove(id);
    }
    
}
