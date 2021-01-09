package br.edu.ifsp.domain.usecases.viagem;

import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.utils.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ViagemDAO extends DAO<Viagem, UUID> {
    public List<Viagem> getViagensByDate(LocalDate dataInicio, LocalDate dataFim);
}
