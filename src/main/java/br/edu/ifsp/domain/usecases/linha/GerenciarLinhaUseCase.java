package br.edu.ifsp.domain.usecases.linha;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;
import br.edu.ifsp.utils.exceptions.InvalidFieldsException;
import br.edu.ifsp.utils.validators.BusLineValidator;
import br.edu.ifsp.utils.validators.IValidator;

import java.util.List;
import java.util.Optional;

public class GerenciarLinhaUseCase {

    private LinhaDAO linhaDAO;
    private TrechoLinhaDAO trechoLinhaDAO;
    private IValidator validator;

    public GerenciarLinhaUseCase(LinhaDAO linhaDAO, TrechoLinhaDAO trechoLinhaDAO)
    {
        this.linhaDAO = linhaDAO;
        this.trechoLinhaDAO = trechoLinhaDAO;
        this.validator = new BusLineValidator();
    }

    public List<Linha> getAll(){
        return linhaDAO.findAll();
    }

    public boolean insert(Linha linha){

        String msg = validator.validateFields(linha);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        if(linhaDAO.findOne(linha.getId()).isPresent())
            throw new IllegalArgumentException("A linha ja existe!");

        return linhaDAO.create(linha);
    }

    public boolean update(Linha linha){
        String msg = validator.validateFields(linha);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        List<TrechoLinha> trechoLinhaList = trechoLinhaDAO.getByLinhaId(linha.getId());
        for (TrechoLinha trechoLinha : trechoLinhaList) {

            trechoLinha.setLinha(linha);
            trechoLinhaDAO.update(trechoLinha);
        }
        return linhaDAO.update(linha);
    }

    public boolean delete(Linha linha){
        String msg = validator.validateFields(linha);
        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        List<TrechoLinha> trechoLinhaList = trechoLinhaDAO.getByLinhaId(linha.getId());
        for (TrechoLinha trechoLinha : trechoLinhaList) {
            trechoLinhaDAO.delete(trechoLinha);
        }
        return linhaDAO.delete(linha);
    }

    public boolean deleteByKey(Long key){
        List<TrechoLinha> trechoLinhaList = trechoLinhaDAO.getByLinhaId(key);
        for (TrechoLinha trechoLinha : trechoLinhaList) {
            trechoLinhaDAO.delete(trechoLinha);
        }
        return linhaDAO.deleteByKey(key);
    }

    public Optional<Linha> getOne(Long id){
        return linhaDAO.findOne(id);
    }

    public boolean addTrechoLinha(TrechoLinha trechoLinha){
        return trechoLinhaDAO.create(trechoLinha);
    }

    public List<TrechoLinha> getTrechosLinhaByKey(Long key){
        return trechoLinhaDAO.getByLinhaId(key);
    }

}
