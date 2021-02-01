package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.utils.exceptions.InvalidFieldsException;
import br.edu.ifsp.utils.exceptions.PatchNotFoundException;
import br.edu.ifsp.utils.exceptions.TrechoEmUsoException;
import br.edu.ifsp.utils.validators.IValidator;
import br.edu.ifsp.utils.validators.PathValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GerenciarTrechosUseCase {

    private TrechoDAO trechoDAO;
    private TrechoLinhaDAO trechoLinhaDAO;
    private IValidator validator;

    public GerenciarTrechosUseCase(TrechoDAO trechoDAO, TrechoLinhaDAO trechoLinhaDAO){

        this.trechoDAO = trechoDAO;
        this.trechoLinhaDAO = trechoLinhaDAO;
        this.validator = new PathValidator();
    }

    public List<Trecho> getAll(){
        return trechoDAO.findAll();
    }

    public Object insert(Trecho trecho){

        String msg = validator.validateFields(trecho);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);


        if(Optional.ofNullable(trechoDAO.findOne(trecho.getId())).isEmpty())
            return trechoDAO.create(trecho);
        return false;

    }

    public boolean update(Trecho trecho){

        String msg = validator.validateFields(trecho);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        return trechoDAO.update(trecho);
    }

    public boolean delete(Trecho trecho){
        String msg = validator.validateFields(trecho);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        if(!trechoLinhaDAO.getByTrechoId(trecho.getId()).isEmpty())
            throw new TrechoEmUsoException("O trecho já está em uso e não pode ser deletado");

        return trechoDAO.delete(trecho);
    }

    public boolean deleteByKey(UUID key){

        if(!trechoLinhaDAO.getByTrechoId(key).isEmpty())
            throw new TrechoEmUsoException("O trecho já está em uso e não pode ser deletado");

        return trechoDAO.deleteByKey(key);
    }

    public Trecho findOneByKey(UUID key){

        if(trechoDAO.findOneByKey(key) == null)
            throw new PatchNotFoundException("Trecho não encontrado");

        return trechoDAO.findOneByKey(key);
    }


}
