package br.edu.ifsp.domain.usecases.viagem;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;

import java.time.LocalDate;
import java.util.List;

public class GerarViagemUseCase {

    private LinhaDAO linhaDAO;

    public GerarViagemUseCase(LinhaDAO linhaDAO) {
        this.linhaDAO = linhaDAO;
    }

    public Viagem gerarViagem(LocalDate data, String cidadeOrigem, String cidadeDestino) {
        return null;
    }
}
