package br.edu.ifsp.domain.usecases.linha;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.utils.DAO;

import java.time.LocalDate;
import java.util.UUID;

public interface LinhaDAO extends DAO<Linha, Long> {
}
