package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.utils.exceptions.PatchNotFoundException;
import br.edu.ifsp.utils.exceptions.TrechoEmUsoException;

import java.util.List;
import java.util.Optional;
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

        if(trecho == null)
            throw new IllegalArgumentException("O trecho é nulo");

        if(verificarCampos(trecho))
            throw new IllegalArgumentException("Há campos nulos");


        if(Optional.ofNullable(trechoDAO.findOne(trecho.getId())).isEmpty())
            return trechoDAO.create(trecho);
        return false;

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

    public Trecho findOneByKey(UUID key){

        if(trechoDAO.findOneByKey(key) == null)
            throw new PatchNotFoundException("Trecho não encontrado");

        return trechoDAO.findOneByKey(key);
    }


    private boolean verificarCampos(Trecho trecho) {
        return trecho.getNome().equals("")
                || trecho.getCidadeDestino().equals("")
                || trecho.getCidadeOrigem().equals("");
    }

}
