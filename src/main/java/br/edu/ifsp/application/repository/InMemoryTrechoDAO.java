package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.usecases.trecho.TrechoDAO;

import java.util.*;

public class InMemoryTrechoDAO implements TrechoDAO {

    private static final Map<UUID, Trecho> db = new LinkedHashMap<>();

    @Override
    public Trecho getByCities(String cidadeOrigem, String cidadeDestino) {

        for (Trecho trecho : db.values()) {
            if(trecho.getCidadeOrigem().equals(cidadeOrigem) && trecho.getCidadeDestino().equals(cidadeDestino))
                //System.out.println("teste");
                return trecho;
        }
        return null;
    }

    @Override
    public boolean create(Trecho type) {
        db.put(type.getId(), type);
        return true;
    }

    @Override
    public Optional<Trecho> findOne(UUID key) {
        return null;
    }

    @Override
    public List<Trecho> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Trecho type) {
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
    public boolean delete(Trecho type) {
        if(db.containsKey(type.getId())){
            db.remove(type.getId());
            return true;
        }
        return false;
    }

    @Override
    public Trecho findOneByKey(UUID key) {
        if(db.containsKey(key))
            
            return db.get(key);
        else
            return null;
    }



}
