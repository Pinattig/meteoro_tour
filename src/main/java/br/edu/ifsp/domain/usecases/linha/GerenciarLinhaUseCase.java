package br.edu.ifsp.domain.usecases.linha;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;

import java.util.List;

public class GerenciarLinhaUseCase {

    private LinhaDAO linhaDAO;
    private TrechoLinhaDAO trechoLinhaDAO;

    public GerenciarLinhaUseCase(LinhaDAO linhaDAO, TrechoLinhaDAO trechoLinhaDAO)
    {
        this.linhaDAO = linhaDAO;
        this.trechoLinhaDAO = trechoLinhaDAO;
    }

    public List<Linha> getAll(){
        return linhaDAO.findAll();
    }

    public boolean insert(Linha linha){
        return linhaDAO.create(linha);
    }

    public boolean update(Linha linha){
        List<TrechoLinha> trechoLinhaList = trechoLinhaDAO.getByLinhaId(linha.getId());
        for (TrechoLinha trechoLinha : trechoLinhaList) {
            trechoLinha.setLinha(linha);
            trechoLinhaDAO.update(trechoLinha);
        }
        return linhaDAO.update(linha);
    }

    public boolean delete(Linha linha){
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

    public boolean addTrechoLinha(TrechoLinha trechoLinha){
        return trechoLinhaDAO.create(trechoLinha);
    }

}
