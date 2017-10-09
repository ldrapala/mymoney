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
        Expenses entity = copy(expenses);
        entity.setId(idCounter);
        db.put(idCounter, entity);
        idCounter++;
        return entity;
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
        Expenses entity = copy(expenses);
        return db.put(entity.getId(), entity);
    }

    @Override
    public void delete(int id) {
        db.remove(id);
    }
    
    private Expenses copy(Expenses expenses){
        Expenses result = new Expenses();
        result.setId(expenses.getId());
        result.setAmount(expenses.getAmount());
        result.setCategory(expenses.getCategory());
        result.setDate(expenses.getDate());
        result.setDetails(expenses.getDetails());
        return result;
    }
    
}
