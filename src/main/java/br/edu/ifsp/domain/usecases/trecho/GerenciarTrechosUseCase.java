package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.utils.DAO;

import java.util.List;

public class GerenciarTrechosUseCase {
    TrechoDAO trechoDAO;

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
            return false; //LANÇAR ERRO AQUI

        return trechoDAO.deleteByKey(key);
    }


}
