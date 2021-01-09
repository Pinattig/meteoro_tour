package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.usecases.viagem.ViagemDAO;

import java.io.IOException;
import java.time.LocalDate;

public class EmitirRelatorioDiarioUseCase {

    EmitirRelatoriosUseCase emitirRelatoriosUseCase;

    public EmitirRelatorioDiarioUseCase(ViagemDAO viagemDAO) {
        this.emitirRelatoriosUseCase = new EmitirRelatoriosUseCase(viagemDAO);
    }

    public void relatorioDiario() throws IOException {
        Relatorio relatorio = emitirRelatoriosUseCase.gerarRelatorio(LocalDate.now(), LocalDate.now());
        relatorio.salvarEmArquivo();
    }
}
