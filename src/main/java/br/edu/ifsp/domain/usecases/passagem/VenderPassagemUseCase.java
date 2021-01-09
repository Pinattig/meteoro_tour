package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.viagem.GerarViagemUseCase;
import br.edu.ifsp.utils.exceptions.PassageIsNotCreated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VenderPassagemUseCase {
    private PassagemDAO passagemDAO;
    private GerarViagemUseCase gerarViagemUseCase;

    public VenderPassagemUseCase(PassagemDAO passagemDAO, GerarViagemUseCase gerarViagemUseCase) {
        this.passagemDAO = passagemDAO;
        this.gerarViagemUseCase = gerarViagemUseCase;
    }


    public Passagem venderPassagem(String cidadeOrigem, String cidadeDestino, LocalDate dataViagem, LocalTime horarioSaida, String assento, String nome, String cpf, String rg, String telefone, boolean seguro) {
        Viagem viagem = gerarViagemUseCase.gerarViagem(dataViagem,cidadeOrigem,cidadeDestino,horarioSaida);
        Map<String, Boolean> assentosDisponiveis  = viagem.getAssentosDisponiveis();

        viagem.ocuparAssentos(assento);
        Long numeroPassagem = randomNumber();
        double precoTotal = getAmount(viagem.getTrechoLinhas());
        Passagem passagem = new Passagem(numeroPassagem, precoTotal, nome, cpf, rg, telefone, seguro, viagem);
        if(!passagemDAO.create(passagem))
            throw new PassageIsNotCreated("A passagem não foi criada");
        return passagem;
    }


    private Long randomNumber(){
        Random random = new Random();
        Long numPassagem = random.nextLong();
        return numPassagem;
    }

    private double getAmount(List<TrechoLinha> trechoLinhaList){
        double amount = 0;
        for (TrechoLinha trechoLinha : trechoLinhaList) {
            Trecho trecho = trechoLinha.getTrecho();
            amount += trecho.getValorPassagem() + trecho.getValorSeguro();
        }
        return amount;
    }

}
