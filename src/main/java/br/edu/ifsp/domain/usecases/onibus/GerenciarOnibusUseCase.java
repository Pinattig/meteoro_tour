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
        if(onibus == null)
            throw new IllegalArgumentException("O onibus é nulo!");
        if(onibusDAO.findOne(onibus.getRenavam()).isPresent())
            throw new IllegalArgumentException("O onibus ja existe!");
        if(verificarCampos(onibus))
            return onibusDAO.create(onibus);
        return false;
    }

    public boolean update(Onibus onibus){
        if(onibus == null)
            throw new IllegalArgumentException("O onibus é nulo!");
        if(onibusDAO.findOne(onibus.getRenavam()).isEmpty())
            throw new IllegalArgumentException("O onibus não existe!");
        if(verificarCampos(onibus))
            return onibusDAO.update(onibus);
        return false;
    }

    public boolean delete(Onibus onibus){
        return onibusDAO.delete(onibus);
    }

    public boolean deleteByKey(String key){
        return onibusDAO.deleteByKey(key);
    }

    private boolean verificarCampos(Onibus onibus) {
        return !(onibus.getRenavam().equals("") || onibus.getPlaca().equals(""));
    }

}
