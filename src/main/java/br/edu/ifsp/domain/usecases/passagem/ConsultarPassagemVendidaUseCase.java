package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;

import java.util.Optional;

public class ConsultarPassagemVendidaUseCase {
    private PassagemDAO passagemDAO;


    public ConsultarPassagemVendidaUseCase(PassagemDAO passagemDAO) {
        this.passagemDAO = passagemDAO;
    }

    public Optional<Passagem> consultarPassagem(Long numPassagem, String cpf){
        Optional<Passagem> passagem = passagemDAO.findOne(numPassagem);
        if(passagem.isEmpty())
            passagem = passagemDAO.findByCpf(cpf);
        return passagem;
    }
    public Optional<Passagem> consultarPassagemByCpf(String cpf){
        return passagemDAO.findByCpf(cpf);
    }
}
