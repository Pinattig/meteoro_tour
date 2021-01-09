package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;
import br.edu.ifsp.utils.exceptions.TrechoEmUsoException;

import java.util.List;
import java.util.UUID;

public class GerenciarTrechosUseCase {

    private TrechoDAO trechoDAO;
    private TrechoLinhaDAO trechoLinhaDAO;

    public GerenciarTrechosUseCase(TrechoDAO trechoDAO, TrechoLinhaDAO trechoLinhaDAO){

        this.trechoDAO = trechoDAO;
        this.trechoLinhaDAO = trechoLinhaDAO;
    }

    public List<Trecho> getAll(){
        return trechoDAO.findAll();
    }

    public Object insert(Trecho trecho){
        return trechoDAO.create(trecho);
    }

    public boolean update(Trecho trecho){
        return trechoDAO.update(trecho);
    }

    public boolean delete(Trecho trecho){

        return trechoDAO.delete(trecho);
    }

    public boolean deleteByKey(UUID key){

        if(!trechoLinhaDAO.getByTrechoId(key).isEmpty())
            throw new TrechoEmUsoException("O trecho já está em uso e não pode ser deletado");

        return trechoDAO.deleteByKey(key);
    }


}
