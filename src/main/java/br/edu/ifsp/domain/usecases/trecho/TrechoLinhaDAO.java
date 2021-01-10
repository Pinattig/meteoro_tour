package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.utils.DAO;

import java.util.List;
import java.util.UUID;

public interface TrechoLinhaDAO extends DAO<TrechoLinha, UUID> {
    public List<TrechoLinha> getByTrechoId(UUID trechoId);

    public List<TrechoLinha> getByLinhaId(Long linhaId);
}
