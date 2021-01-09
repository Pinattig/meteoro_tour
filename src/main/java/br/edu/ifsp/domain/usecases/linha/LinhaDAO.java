package br.edu.ifsp.domain.usecases.linha;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.utils.DAO;

import java.time.LocalDate;

public interface LinhaDAO extends DAO<Linha, Long> {
    public boolean verifyPatch(String cidadeOrigem, String cidadeDestino, LocalDate data);
}
