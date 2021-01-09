package br.edu.ifsp.domain.usecases.viagem;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;
import br.edu.ifsp.domain.usecases.trecho.TrechoDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GerarViagemUseCase {

    private LinhaDAO linhaDAO;
    private TrechoDAO trechoDAO;
    private TrechoLinhaDAO trechoLinhaDAO;

    public GerarViagemUseCase(LinhaDAO linhaDAO, TrechoDAO trechoDAO, TrechoLinhaDAO trechoLinhaDAO) {
        this.linhaDAO = linhaDAO;
        this.trechoDAO = trechoDAO;
        this.trechoLinhaDAO = trechoLinhaDAO;
    }

    public Viagem gerarViagem(LocalDate data, String cidadeOrigem, String cidadeDestino, LocalTime horarioSaida) {
        Trecho trecho = trechoDAO.getByCities(cidadeOrigem, cidadeDestino, data);
        List<TrechoLinha> listTrecholinha = trechoLinhaDAO.getByTrechoId(trecho.getId());
        Viagem viagem = new Viagem(cidadeOrigem, cidadeDestino, listTrecholinha.get(0).getLinha(), data, horarioSaida);
        return viagem;
    }

}
