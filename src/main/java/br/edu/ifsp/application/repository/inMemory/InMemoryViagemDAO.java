package br.edu.ifsp.application.repository.inMemory;

import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.viagem.ViagemDAO;

import java.time.LocalDate;
import java.util.*;

public class InMemoryViagemDAO implements ViagemDAO {

    private static final Map<UUID, Viagem> db = new LinkedHashMap<>();

    @Override
    public List<Viagem> getViagensByDate(LocalDate dataInicio, LocalDate dataFim) {
        List<Viagem> viagens = new ArrayList<>();

        for (Viagem viagem : db.values()) {
            if(dataInicio.isBefore(viagem.getData()) && dataFim.isAfter(viagem.getData()))
                viagens.add(viagem);
        }

        return viagens;
    }

    @Override
    public boolean create(Viagem type) {
        db.put(type.getId(), type);
        return true;
    }

    @Override
    public Optional<Viagem> findOne(UUID key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Viagem> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Viagem type) {
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
    public boolean delete(Viagem type) {
        if(db.containsKey(type.getId())){
            db.remove(type.getId());
            return true;
        }
        return false;
    }
}
