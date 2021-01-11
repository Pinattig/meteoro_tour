package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.utils.exceptions.PassageNotFoundException;

import java.util.Optional;

public class ReemitirPassagemUseCase {
    private ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;
    private PassagemDAO passagemDAO;

    public ReemitirPassagemUseCase(PassagemDAO passagemDAO) {
        this.consultarPassagemVendidaUseCase = new ConsultarPassagemVendidaUseCase(passagemDAO);
    }

    public Optional<Passagem> reemitirPassagem(String cpf){
        Optional<Passagem> passagem = consultarPassagemVendidaUseCase.consultarPassagemByCpf(cpf);
        if(passagem.isEmpty())
            throw new PassageNotFoundException("A passagem não foi encontrada");
        return passagem;
    }


}
