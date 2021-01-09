package br.edu.ifsp.domain.entities.relatorio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InfoLinhaRelatorio {
    private LocalDate data;
    private String nomeLinha;

    private List<InfoTrechoRelatorio> infoTrechoRelatorios;

    public InfoLinhaRelatorio() {
    }


    public InfoLinhaRelatorio(LocalDate data, String nomeLinha){
        this(data, nomeLinha, new ArrayList<>());
    }

    public InfoLinhaRelatorio(LocalDate data, String nomeLinha, List<InfoTrechoRelatorio> infoTrechoRelatorioList) {
        this.data = data;
        this.nomeLinha = nomeLinha;
        this.infoTrechoRelatorios = infoTrechoRelatorioList;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }


    public List<InfoTrechoRelatorio> getInfoTrechoRelatorios() {
        return infoTrechoRelatorios;
    }

    public void setInfoTrechoRelatorios(List<InfoTrechoRelatorio> infoTrechoRelatorios) {
        this.infoTrechoRelatorios = infoTrechoRelatorios;
    }


    public InfoTrechoRelatorio getInfoTrechoRelatorioByName(String name){

        for (InfoTrechoRelatorio infoTrechoRelatorio : infoTrechoRelatorios) {
            if(infoTrechoRelatorio.getNomeTrecho().equals(name))
                return infoTrechoRelatorio;
        }

        return null;
    }

    @Override
    public String toString() {
        return "InfoLinhaRelatorio{" +
                "data=" + data +
                ", nomeLinha='" + nomeLinha + '\'' +
                ", infoTrechoRelatorios=" + infoTrechoRelatorios +
                '}';
    }
}
