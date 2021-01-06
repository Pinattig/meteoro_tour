package br.edu.ifsp.domain.entities.relatorio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Relatorio {
    private LocalDate dataInicio;
    private LocalDate dataFinal;

    private List<InfoLinhaRelatorio> infolinhaRelatorios;

    public Relatorio() {
    }

    public Relatorio(LocalDate dataInicio, LocalDate dataFinal) {
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void salvarEmArquivo() {

    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "dataInicio=" + dataInicio +
                ", dataFinal=" + dataFinal +
                ", infolinhaRelatorios=" + infolinhaRelatorios +
                '}';
    }
}
