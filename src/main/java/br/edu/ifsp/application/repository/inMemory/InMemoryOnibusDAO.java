package br.edu.ifsp.application.repository.inMemory;

import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.usecases.onibus.OnibusDAO;

import java.util.*;

public class InMemoryOnibusDAO implements OnibusDAO {

    private static final Map<String, Onibus> db = new LinkedHashMap<>();

    @Override
    public boolean create(Onibus type) {
        db.put(type.getRenavam(), type);
        return true;
    }

    @Override
    public Optional<Onibus> findOne(String key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Onibus> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Onibus type) {
        db.put(type.getRenavam(), type);
        return true;
    }

    @Override
    public boolean deleteByKey(String key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Onibus type) {
        if(db.containsKey(type.getRenavam())){
            db.remove(type.getRenavam());
            return true;
        }
        return false;
    }
}
