package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.usecases.viagem.GerarViagemUseCase;
import br.edu.ifsp.utils.exceptions.NotAvaliableSeatException;
import br.edu.ifsp.utils.exceptions.PassageNotFoundException;

import java.util.Optional;

public class DevolverPassagemUseCase {

    private PassagemDAO passagemDAO;
    private ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;


    public DevolverPassagemUseCase(PassagemDAO passagemDAO) {
        this.passagemDAO = passagemDAO;
        this.consultarPassagemVendidaUseCase = new ConsultarPassagemVendidaUseCase(passagemDAO);

    }
    public void devolverPassagem(Optional<Passagem> passagem) {

        if(consultarPassagemVendidaUseCase.consultarPassagem(passagem.get().getNumPassagem(),passagem.get().getNome()).isEmpty())
            throw new PassageNotFoundException("A passagem não foi encontrada");

        if(!passagem.get().verificarValidade())
            throw new NotAvaliableSeatException("Não há assentos disponiveis");

        passagemDAO.deleteByKey(passagem.get().getNumPassagem());
    }
}
