package br.edu.ifsp.domain.usecases.viagem;

import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.utils.DAO;

import java.util.UUID;

public interface ViagemDAO extends DAO<Viagem, UUID> {

}
