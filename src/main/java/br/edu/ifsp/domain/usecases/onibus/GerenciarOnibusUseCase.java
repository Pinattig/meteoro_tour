package br.edu.ifsp.domain.usecases.onibus;

import br.edu.ifsp.domain.entities.onibus.Onibus;

import java.util.List;

public class GerenciarOnibusUseCase {

    private OnibusDAO onibusDAO;

    public GerenciarOnibusUseCase(OnibusDAO onibusDAO) {
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

    public boolean deleteByKey(String key){
        return onibusDAO.deleteByKey(key);
    }
}
