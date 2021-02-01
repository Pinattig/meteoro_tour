package br.edu.ifsp.application.repository.inMemory;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.usecases.funcionario.FuncionarioDAO;

import java.util.*;

public class InMemoryFuncionarioDAO implements FuncionarioDAO {

    private static final Map<String, Funcionario> db = new LinkedHashMap<>();


    @Override
    public boolean create(Funcionario type) {
        db.put(type.getCpf(), type);
        return true;
    }

    @Override
    public Optional<Funcionario> findOne(String key) {

        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Funcionario> findAll() {

        return new ArrayList<>(db.values());

    }

    @Override
    public boolean update(Funcionario type) {
        db.put(type.getCpf(), type);
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
    public boolean delete(Funcionario type) {
        if(db.containsKey(type.getCpf())){
            db.remove(type.getCpf());
            return true;
        }
        return false;
    }
}
