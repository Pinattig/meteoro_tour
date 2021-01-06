package br.edu.ifsp.domain.entities.vendedor;

import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.viagem.Viagem;

import java.util.List;

public class Vendedor {

    private Passagem passagem;
    private List<Viagem> viagens;

    public Vendedor() {
    }

    public void emitirPassagem(Passagem passagem) {

    }

}
