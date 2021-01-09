package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.utils.DAO;

import java.util.Optional;

public interface PassagemDAO extends DAO<Passagem,Long> {
    Optional<Passagem> findByCpf(String cpf);
}
