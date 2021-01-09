package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.relatorio.InfoLinhaRelatorio;
import br.edu.ifsp.domain.entities.relatorio.InfoTrechoRelatorio;
import br.edu.ifsp.domain.entities.relatorio.Relatorio;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.viagem.ViagemDAO;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmitirRelatoriosUseCase {
    private ViagemDAO viagemDAO;

    public EmitirRelatoriosUseCase(ViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    public Relatorio gerarRelatorio(LocalDate dataInicio, LocalDate dataFim) {
        Map<Long, InfoLinhaRelatorio> infoLinhaRelatorioMap = new LinkedHashMap<>();
        List<InfoTrechoRelatorio> infoTrechoRelatorioList = new ArrayList<>();

        List<Viagem> viagens = viagemDAO.getViagensByDate(dataInicio, dataFim);
        for (Viagem viagem : viagens) {
            InfoLinhaRelatorio infoLinhaRelatorio;
            List<TrechoLinha> trechoLinhaList = viagem.getLinha().getListTrechoLinha();
            if(!infoLinhaRelatorioMap.containsKey(viagem.getLinha().getId())){

                for (TrechoLinha trechoLinha : trechoLinhaList) {
                    Trecho trecho = trechoLinha.getTrecho();
                    Double amount = trecho.getValorPassagem() + trecho.getValorSeguro() + trecho.getTaxaEmbarque();
                    InfoTrechoRelatorio infoTrechoRelatorio = new InfoTrechoRelatorio(trechoLinha.getHorarioSaida(), trechoLinha.getTrecho().getNome(), 1, amount);
                    infoTrechoRelatorioList.add(infoTrechoRelatorio);
                }

                infoLinhaRelatorio = new InfoLinhaRelatorio(viagem.getData(), viagem.getLinha().getNome(), infoTrechoRelatorioList);
                infoLinhaRelatorioMap.put(viagem.getLinha().getId(),infoLinhaRelatorio);

            }else{
                infoLinhaRelatorio = infoLinhaRelatorioMap.get(viagem.getLinha().getId());


                for (TrechoLinha trechoLinha : trechoLinhaList) {

                    Trecho trecho = trechoLinha.getTrecho();
                    InfoTrechoRelatorio infoTrechoRelatorio = infoLinhaRelatorio.getInfoTrechoRelatorioByName(trecho.getNome());
                    infoTrechoRelatorio.increaseFluxoUso(1);
                    Double amount = (trecho.getValorPassagem() + trecho.getValorSeguro() + trecho.getTaxaEmbarque());
                    infoTrechoRelatorio.increaseLucro(amount);
                }

            }
        }

        List<InfoLinhaRelatorio> infoLinhaRelatorioList = new ArrayList<>();
        infoLinhaRelatorioMap.forEach((k,v) -> infoLinhaRelatorioList.add(v));

        return new Relatorio(dataInicio, dataFim, infoLinhaRelatorioList);

    }

}
