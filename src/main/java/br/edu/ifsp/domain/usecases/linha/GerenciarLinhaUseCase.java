package br.edu.ifsp.domain.usecases.linha;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;

import java.util.List;
import java.util.Optional;

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
        if(linha == null)
            throw new IllegalArgumentException("A linha é nula!");
        if(linhaDAO.findOne(linha.getId()).isPresent())
            throw new IllegalArgumentException("O linha ja existe!");
        if(verificarCampos(linha))
            return linhaDAO.create(linha);
        return false;
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

    public Optional<Linha> getOne(Long id){
        return linhaDAO.findOne(id);
    }

    public boolean addTrechoLinha(TrechoLinha trechoLinha){
        return trechoLinhaDAO.create(trechoLinha);
    }

    public List<TrechoLinha> getTrechosLinhaByKey(Long key){
        return trechoLinhaDAO.getByLinhaId(key);
    }

    private boolean verificarCampos(Linha linha) {
        return !(linha.getNome().equals(""));
    }

}
