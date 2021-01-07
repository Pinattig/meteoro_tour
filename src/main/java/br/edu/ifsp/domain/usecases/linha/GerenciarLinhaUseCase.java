package br.edu.ifsp.domain.usecases.linha;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.utils.DAO;

import java.util.List;

public class GerenciarLinhaUseCase {

    DAO linhaDAO;

    public GerenciarLinhaUseCase(DAO linhaDAO) {
        this.linhaDAO = linhaDAO;
    }

    public List<Linha> getAll(){
        return linhaDAO.findAll();
    }

    public Object create(Linha linha){
        return linhaDAO.create(linha);
    }

    public boolean update(Linha linha){
        return linhaDAO.update(linha);
    }

    public boolean delete(Linha linha){
        return linhaDAO.delete(linha);
    }

    public boolean deleteByKey(Object key){
        return linhaDAO.deleteByKey(key);
    }
}
