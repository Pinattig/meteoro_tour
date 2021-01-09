package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.utils.exceptions.PassageNotFoundException;

import java.util.Optional;

public class ReemitirPassagemUseCase {
    private ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;

    public ReemitirPassagemUseCase(ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase) {
        this.consultarPassagemVendidaUseCase = consultarPassagemVendidaUseCase;
    }

    public Optional<Passagem> reemitirPassagem(String cpf){
        Optional<Passagem> passagem = consultarPassagemVendidaUseCase.consultarPassagemByCpf(cpf);
        if(passagem.isEmpty())
            throw new PassageNotFoundException("A passagem não foi encontrada");
        return passagem;
    }
}
