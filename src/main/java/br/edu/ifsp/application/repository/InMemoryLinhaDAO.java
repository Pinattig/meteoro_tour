package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;

import java.util.*;

public class InMemoryLinhaDAO implements LinhaDAO {

    private static final Map<Long, Linha> db = new LinkedHashMap<>();

    @Override
    public boolean create(Linha type) {
        db.put(type.getId(), type);
        return true;
    }

    @Override
    public Optional<Linha> findOne(Long key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Linha> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Linha type) {
        db.put(type.getId(), type);
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
    public boolean delete(Linha type) {
        if(db.containsKey(type.getId())){
            db.remove(type.getId());
            return true;
        }
        return false;
    }
}
