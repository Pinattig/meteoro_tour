package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.usecases.viagem.GerarViagemUseCase;

public class ReagendarPassagensUseCase {
    private PassagemDAO passagemDAO;
    private ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;

    public ReagendarPassagensUseCase(PassagemDAO passagemDAO, ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase) {
        this.passagemDAO = passagemDAO;
        this.consultarPassagemVendidaUseCase = consultarPassagemVendidaUseCase;
    }


}
