package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;

import java.util.*;

public class InMemoryTrechoLinhaDAO implements TrechoLinhaDAO {

    private static final Map<UUID, TrechoLinha> db = new LinkedHashMap<>();

    @Override
    public List<TrechoLinha> getByTrechoId(UUID trechoId) {

        List<TrechoLinha> trechoLinhaList = new ArrayList<>();

        for (TrechoLinha trechoLinha : db.values()) {
            if(trechoLinha.getId() == trechoId)
                trechoLinhaList.add(trechoLinha);
        }

        return trechoLinhaList;
    }

    @Override
    public boolean create(TrechoLinha type) {
        db.put(type.getId(), type);
        return true;
    }

    @Override
    public Optional<TrechoLinha> findOne(UUID key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<TrechoLinha> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(TrechoLinha type) {
        db.put(type.getId(), type);
        return true;
    }

    @Override
    public boolean deleteByKey(UUID key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(TrechoLinha type) {
        if(db.containsKey(type.getId())){
            db.remove(type.getId());
            return true;
        }
        return false;
    }
}
