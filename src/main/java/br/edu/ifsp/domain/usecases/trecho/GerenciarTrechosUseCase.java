package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.utils.DAO;
import br.edu.ifsp.utils.exceptions.TrechoEmUso;

import java.util.List;

public class GerenciarTrechosUseCase {

    private TrechoDAO trechoDAO;

    public GerenciarTrechosUseCase(TrechoDAO trechoDAO){
        this.trechoDAO = trechoDAO;
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

    public boolean deleteByKey(Integer key){

        if(trechoDAO.trechoIsUsed(key))
            throw new TrechoEmUso("O trecho já está em uso e não pode ser deletado");

        return trechoDAO.deleteByKey(key);
    }


}