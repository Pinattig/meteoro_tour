package br.edu.ifsp.domain.usecases.onibus;

import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.utils.exceptions.InvalidFieldsException;
import br.edu.ifsp.utils.validators.BusValidator;
import br.edu.ifsp.utils.validators.IValidator;

import java.util.List;

public class GerenciarOnibusUseCase {

    private OnibusDAO onibusDAO;
    private IValidator validator;

    public GerenciarOnibusUseCase(OnibusDAO onibusDAO) {
        this.validator = new BusValidator();
        this.onibusDAO = onibusDAO;
    }

    public List<Onibus> getAll(){
        return onibusDAO.findAll();
    }

    public Object insert(Onibus onibus){
        String msg = validator.validateFields(onibus);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        return onibusDAO.create(onibus);
    }

    public boolean update(Onibus onibus){

        String msg = validator.validateFields(onibus);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        return onibusDAO.update(onibus);
    }

    public boolean delete(Onibus onibus){

        String msg = validator.validateFields(onibus);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        return onibusDAO.delete(onibus);
    }

    public boolean deleteByKey(String key){
        return onibusDAO.deleteByKey(key);
    }

    private boolean verificarCampos(Onibus onibus) {
        return !(onibus.getRenavam().equals("") || onibus.getPlaca().equals(""));
    }

}
