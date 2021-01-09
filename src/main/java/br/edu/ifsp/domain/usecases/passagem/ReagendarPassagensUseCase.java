package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.viagem.GerarViagemUseCase;
import br.edu.ifsp.utils.exceptions.NotAvaliableSeatException;
import br.edu.ifsp.utils.exceptions.PassageIsExpirateException;
import br.edu.ifsp.utils.exceptions.PassageNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class ReagendarPassagensUseCase {
    private PassagemDAO passagemDAO;
    private ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;
    private VenderPassagemUseCase venderPassagemUseCase;
    private DevolverPassagemUseCase devolverPassagemUseCase;

    public ReagendarPassagensUseCase(PassagemDAO passagemDAO, ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase,VenderPassagemUseCase venderPassagemUseCase,DevolverPassagemUseCase devolverPassagemUseCase) {
        this.passagemDAO = passagemDAO;
        this.consultarPassagemVendidaUseCase = consultarPassagemVendidaUseCase;
        this.venderPassagemUseCase = venderPassagemUseCase;
        this.devolverPassagemUseCase = devolverPassagemUseCase;

    }

    public Passagem reagendar(Long numPassagem, String cpf, LocalDate data, LocalTime horarioSaida, String assento){
        Optional<Passagem> passagem = consultarPassagemVendidaUseCase.consultarPassagem(numPassagem,cpf);
        if(passagem.isEmpty())
            throw new PassageNotFoundException("A passagem não foi encontrada");
        if(!passagem.get().verificarValidade())
            throw new PassageIsExpirateException("A passagem está expirada");

        Viagem viagem = passagem.get().getViagem();
        Passagem novaPassagem = venderPassagemUseCase.venderPassagem(viagem.getCidadeOrigem(),viagem.getCidadeDestino(),data,horarioSaida,assento,passagem.get().getNome(),passagem.get().getCpf(),passagem.get().getRg(),passagem.get().getTelefone(),passagem.get().isSeguro(),passagem.get().getTipoEspecial());

        if(!viagem.verificarAssentosDisponiveis())
            throw new NotAvaliableSeatException("Não há assentos disponiveis");


        devolverPassagemUseCase.devolverPassagem(passagem);

        return novaPassagem;
    }


}
