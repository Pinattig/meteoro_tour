package br.edu.ifsp.domain.usecases.viagem;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;
import br.edu.ifsp.domain.usecases.trecho.TrechoLinhaDAO;
import br.edu.ifsp.domain.usecases.trecho.TrechoDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GerarViagemUseCase {

    private LinhaDAO linhaDAO;
    private TrechoDAO trechoDAO;
    private TrechoLinhaDAO trechoLinhaDAO;
    private ViagemDAO viagemDAO;

    public GerarViagemUseCase(LinhaDAO linhaDAO, TrechoDAO trechoDAO, TrechoLinhaDAO trechoLinhaDAO, ViagemDAO viagemDAO) {
        this.linhaDAO = linhaDAO;
        this.trechoDAO = trechoDAO;
        this.trechoLinhaDAO = trechoLinhaDAO;
        this.viagemDAO = viagemDAO;
    }

    public Viagem gerarViagem(LocalDate data, String cidadeOrigem, String cidadeDestino, LocalTime horarioSaida) {
        Trecho trecho = trechoDAO.getByCities(cidadeOrigem, cidadeDestino);
        List<TrechoLinha> listTrecholinha = trechoLinhaDAO.getByTrechoId(trecho.getId());
        Linha linha = listTrecholinha.get(0).getLinha();
        List<TrechoLinha> trechosViagem = gerarTrechosViagem(cidadeOrigem, cidadeDestino, data, linha);
        Viagem viagem = new Viagem(cidadeOrigem, cidadeDestino, linha, data, horarioSaida, trechosViagem);
        viagemDAO.create(viagem);
        return viagem;
    }

    private List<TrechoLinha> gerarTrechosViagem(String cidadeOrigem, String cidadeDestino, LocalDate data, Linha linha) {
        List<TrechoLinha> trechosViagem = new ArrayList<>();
        List<TrechoLinha> listTrechoLinha = trechoLinhaDAO.getByLinhaId(linha.getId());
        for (TrechoLinha trechoLinha : listTrechoLinha) {
            String trechoCidadeOrigem = trechoLinha.getTrecho().getCidadeOrigem();
            if(trechoCidadeOrigem.equals(cidadeOrigem)){
                trechosViagem.add(trechoLinha);
                break;
            }
        }

        if(trechosViagem.isEmpty())
            return null;

        String trechoCidadeDestino = trechosViagem.get(0).getTrecho().getCidadeDestino();
        if(trechoCidadeDestino.equals(cidadeDestino))
            return trechosViagem;

        int index = trechosViagem.get(0).getOrdem();
        for (int i = index + 1; i < listTrechoLinha.size(); i++) {
            TrechoLinha trechoLinha = listTrechoLinha.get(i);
            trechosViagem.add(trechoLinha);
            String destinoTrechoLinha = trechoLinha.getTrecho().getCidadeDestino();
            if(destinoTrechoLinha.equals(cidadeDestino))
                break;
        }

        return trechosViagem;
    }

    public Viagem findOne(UUID id){
        return viagemDAO.findOne(id).get();
    }
}
