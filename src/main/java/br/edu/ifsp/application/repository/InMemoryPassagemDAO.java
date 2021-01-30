package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.usecases.passagem.PassagemDAO;

import java.util.*;

public class InMemoryPassagemDAO implements PassagemDAO {


    private static final Map<Long, Passagem> db = new LinkedHashMap<>();

    @Override
    public boolean create(Passagem type) {
        db.put(type.getNumPassagem(), type);
        return true;
    }

    @Override
    public Optional<Passagem> findOne(Long key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Passagem> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Passagem type) {
        db.put(type.getNumPassagem(), type);
        return true;
    }

    @Override
    public boolean deleteByKey(Long key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Passagem type) {
        if(db.containsKey(type.getNumPassagem())){
            db.remove(type.getNumPassagem());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Passagem> findByCpf(String cpf) {

        for (Passagem passagem : db.values()) {
            if(passagem.getCpf().equals(cpf))
                return Optional.of(db.get(passagem.getNumPassagem()));
        }

        return Optional.empty();
    }
}
