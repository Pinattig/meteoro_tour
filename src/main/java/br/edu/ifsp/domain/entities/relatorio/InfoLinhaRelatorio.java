package br.edu.ifsp.domain.entities.relatorio;

import java.util.List;

public class InfoLinhaRelatorio {
    private int data;
    private String nomeLinha;

    private List<InfoTrechoRelatorio> infoTrechoRelatorios;

    public InfoLinhaRelatorio() {
    }

    public InfoLinhaRelatorio(int data, String nomeLinha, InfoTrechoRelatorio infoTrechoRelatorio) {
        this.data = data;
        this.nomeLinha = nomeLinha;
        this.infoTrechoRelatorios.add(infoTrechoRelatorio);
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
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
