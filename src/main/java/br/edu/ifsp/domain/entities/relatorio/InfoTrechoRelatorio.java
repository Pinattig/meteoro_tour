package br.edu.ifsp.domain.entities.relatorio;

import br.edu.ifsp.domain.entities.relatorio.InfoLinhaRelatorio;

import java.time.LocalTime;

public class InfoTrechoRelatorio {
    private LocalTime horario;
    private String nomeTrecho;
    private int fluxoUso;
    private double lucro;

    public InfoTrechoRelatorio() {
    }

    public InfoTrechoRelatorio(LocalTime horario, String nomeTrecho, int fluxoUso, double lucro) {
        this.horario = horario;
        this.nomeTrecho = nomeTrecho;
        this.fluxoUso = fluxoUso;
        this.lucro = lucro;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public String getNomeTrecho() {
        return nomeTrecho;
    }

    public void setNomeTrecho(String nomeTrecho) {
        this.nomeTrecho = nomeTrecho;
    }

    public int getFluxoUso() {
        return fluxoUso;
    }

    public void increaseFluxoUso(int qtd){
        fluxoUso += qtd;
    }

    public void increaseLucro(double qtd){
        lucro += qtd;
    }

    public double getLucro() {
        return lucro;
    }

    @Override
    public String toString() {
        return "InfoTrechoRelatorio{" +
                "horario=" + horario +
                ", nomeTrecho='" + nomeTrecho + '\'' +
                ", fluxoUso=" + fluxoUso +
                ", lucro=" + lucro +
                '}';
    }
}
