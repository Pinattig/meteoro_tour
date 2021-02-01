package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.utils.exceptions.NotAvaliableSeatException;
import br.edu.ifsp.utils.exceptions.PassageIsExpirateException;
import br.edu.ifsp.utils.exceptions.PassageNotFoundException;
import br.edu.ifsp.domain.entities.passagem.Passagem;

import java.time.LocalDate;
import java.util.Optional;

public class ReagendarPassagensUseCase {
    private PassagemDAO passagemDAO;
    private ConsultarPassagemVendidaUseCase consultarPassagemVendidaUseCase;
    private VenderPassagemUseCase venderPassagemUseCase;
    private DevolverPassagemUseCase devolverPassagemUseCase;

    public ReagendarPassagensUseCase(PassagemDAO passagemDAO, VenderPassagemUseCase venderPassagemUseCase) {
        this.passagemDAO = passagemDAO;
        this.consultarPassagemVendidaUseCase = new ConsultarPassagemVendidaUseCase(passagemDAO);
        this.venderPassagemUseCase = venderPassagemUseCase;
        this.devolverPassagemUseCase = new DevolverPassagemUseCase(passagemDAO);

    }

    public Passagem reagendar(Long numPassagem, LocalDate newDate){
        Optional<Passagem> passagem = consultarPassagemVendidaUseCase.consultarPassagem(numPassagem);
        return reagendarPassagem(passagem, String.valueOf(newDate));
    }

    public Passagem reagendar(String cpf, LocalDate newDate){
        Optional<Passagem> passagem = consultarPassagemVendidaUseCase.consultarPassagemByCpf(cpf);
        return reagendarPassagem(passagem, String.valueOf(newDate));
    }

    private Passagem reagendarPassagem(Optional<Passagem> passagem, String newDate) {
        if(passagem.isEmpty())
            throw new PassageNotFoundException("A passagem não foi encontrada");
        if(passagem.get().verificarValidade())
            throw new PassageIsExpirateException("A passagem está expirada");

        Viagem viagem = passagem.get().getViagem();
        Passagem novaPassagem = venderPassagemUseCase.venderPassagem(viagem.getCidadeOrigem(),viagem.getCidadeDestino(),newDate, String.valueOf(viagem.getHorarioSaida()),passagem.get().getAssento(),passagem.get().getNome(),passagem.get().getCpf(),passagem.get().getRg(),passagem.get().getTelefone(),passagem.get().isSeguro(),passagem.get().getTipoEspecial());

        if(!viagem.verificarAssentosDisponiveis())
            throw new NotAvaliableSeatException("Não há assentos disponiveis");


        devolverPassagemUseCase.devolverPassagem(passagem);

        return novaPassagem;
    }

    public Optional<Passagem> getByCPF(String cpf){
        return passagemDAO.findByCpf(cpf);
    }
}
