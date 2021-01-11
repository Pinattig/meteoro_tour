package br.edu.ifsp.domain.usecases.passagem;

import br.edu.ifsp.domain.entities.passagem.Passagem;

import java.util.Optional;

public class ConsultarPassagemVendidaUseCase {
    private PassagemDAO passagemDAO;


    public ConsultarPassagemVendidaUseCase(PassagemDAO passagemDAO) {
        this.passagemDAO = passagemDAO;
    }

    public Optional<Passagem> consultarPassagem(Long numPassagem){
        Optional<Passagem> passagem = passagemDAO.findOne(numPassagem);
        if(!passagem.isEmpty())
            return passagemDAO.findByCpf(passagem.get().getCpf());
        return Optional.empty();
    }
    public Optional<Passagem> consultarPassagemByCpf(String cpf){
        return passagemDAO.findByCpf(cpf);
    }
}
