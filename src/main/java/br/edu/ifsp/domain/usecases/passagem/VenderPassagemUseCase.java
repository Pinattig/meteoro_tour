package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.utils.exceptions.InvalidFieldsException;
import br.edu.ifsp.utils.exceptions.NotAvaliableSeatException;
import br.edu.ifsp.utils.exceptions.PassageIsNotCreatedException;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.passagem.TipoEspecial;
import br.edu.ifsp.domain.usecases.viagem.GerarViagemUseCase;
import br.edu.ifsp.utils.validators.IValidator;
import br.edu.ifsp.utils.validators.PassageValidator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VenderPassagemUseCase {
    private PassagemDAO passagemDAO;
    private GerarViagemUseCase gerarViagemUseCase;
    private IValidator validator;

    public VenderPassagemUseCase(PassagemDAO passagemDAO, GerarViagemUseCase gerarViagemUseCase) {
        this.passagemDAO = passagemDAO;
        this.gerarViagemUseCase = gerarViagemUseCase;
        this.validator = new PassageValidator();
    }

    public Passagem venderPassagem(String cidadeOrigem, String cidadeDestino, LocalDate dataViagem, LocalTime horarioSaida, String assento, String nome, String cpf, String rg, String telefone, boolean seguro, TipoEspecial tipoEspecial) {
        Viagem viagem = gerarViagemUseCase.gerarViagem(dataViagem,cidadeOrigem,cidadeDestino,horarioSaida);

        if(!viagem.verificarAssentosDisponiveis())
            throw new NotAvaliableSeatException("não há assentos disponiveis");

        Map<String, Double> amount = getAmount(viagem.getTrechoLinhas());

        if(tipoEspecial.equals("Idoso")){
            if(viagem.verificarAssentosPrefDisponiveis()) {
                amount.put("passagem", 0d);
                viagem.ocuparAssentosPref(assento);
            }
            else{
                amount.put("passagem", amount.get("passagem")/2);
                viagem.ocuparAssentos(assento);
            }
        }

        if(tipoEspecial.equals("Deficiente")){
            amount.put("passagem", 0d);
            tipoEspecial.equals("Deficiente");
        }

        if(tipoEspecial.equals("Não"))
            viagem.ocuparAssentos(assento);

        double precoTotal = amount.get("passagem") + (seguro ? amount.get("seguro") : 0);

        Long numeroPassagem = randomNumber();
        Passagem passagem = new Passagem(numeroPassagem, precoTotal, nome, cpf, rg, telefone, seguro, viagem, tipoEspecial, assento);

        String msg = validator.validateFields(passagem);

        if(!msg.equals(""))
            throw new InvalidFieldsException(msg);

        passagemDAO.create(passagem);

        return passagem;
    }


    private Long randomNumber(){
        Random random = new Random();
        Long numPassagem = random.nextLong();
        return numPassagem;
    }

    private Map<String, Double> getAmount(List<TrechoLinha> trechoLinhaList){
        Map<String, Double> amount = new LinkedHashMap<>();
        for (TrechoLinha trechoLinha : trechoLinhaList) {
            Trecho trecho = trechoLinha.getTrecho();
            if(amount.isEmpty()){
                amount.put("passagem", trecho.getValorPassagem() + trecho.getTaxaEmbarque());
                amount.put("seguro", trecho.getValorSeguro());
            }else{
                Double passagem = amount.get("passagem") + trecho.getValorPassagem() + trecho.getTaxaEmbarque();
                Double seguro = amount.get("seguro") + trecho.getValorSeguro();

                amount.put("passagem", passagem);
                amount.put("seguro", seguro);
            }
        }
        return amount;
    }

}
