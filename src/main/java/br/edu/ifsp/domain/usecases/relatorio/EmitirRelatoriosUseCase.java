package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.viagem.ViagemDAO;

import java.time.LocalDate;
import java.util.List;

public class EmitirRelatoriosUseCase {
    private ViagemDAO viagemDAO;

    public EmitirRelatoriosUseCase(ViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    public void gerarRelatorio(LocalDate dataInicio, LocalDate dataFim) {
        List<Viagem> viagens = viagemDAO.getViagensByDate(dataInicio, dataFim);
    }
}
