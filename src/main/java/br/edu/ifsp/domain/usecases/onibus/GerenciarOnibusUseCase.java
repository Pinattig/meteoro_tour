package br.edu.ifsp.domain.usecases.onibus;

import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.utils.DAO;

import java.util.List;

public class GerenciarOnibusUseCase {
    DAO onibusDAO;

    public GerenciarOnibusUseCase(DAO onibusDAO) {
        this.onibusDAO = onibusDAO;
    }

    public List<Onibus> getAll(){
        return onibusDAO.findAll();
    }

    public Object insert(Onibus onibus){
        return onibusDAO.create(onibus);
    }

    public boolean update(Onibus onibus){
        return onibusDAO.update(onibus);
    }

    public boolean delete(Onibus onibus){
        return onibusDAO.delete(onibus);
    }

    public boolean deleteByKey(Object key){
        return onibusDAO.deleteByKey(key);
    }
}
