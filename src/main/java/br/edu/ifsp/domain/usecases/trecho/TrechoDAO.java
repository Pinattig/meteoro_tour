package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.utils.DAO;

public interface TrechoDAO extends DAO<Trecho, Integer> {

    public boolean trechoIsUsed(Integer id);

}
