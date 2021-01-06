package br.edu.ifsp.domain.entities.trecho;

import br.edu.ifsp.domain.entities.linha.Linha;

import java.time.LocalTime;

public class TrechoLinha {
    private LocalTime horarioSaida;
    private int ordem;

    private Linha linha;
    private AssentosTrechoLinha assentosTrechoLinha;
    private Trecho trecho;

    public TrechoLinha() {
    }

    public TrechoLinha(LocalTime horarioSaida, int ordem, Linha linha, AssentosTrechoLinha assentosTrechoLinha, Trecho trecho) {
        this.horarioSaida = horarioSaida;
        this.ordem = ordem;
        this.linha = linha;
        this.assentosTrechoLinha = assentosTrechoLinha;
        this.trecho = trecho;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Linha getLinha() {
        return linha;
    }

    public AssentosTrechoLinha getAssentosTrechoLinha() {
        return assentosTrechoLinha;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    @Override
    public String toString() {
        return "TrechoLinha{" +
                "horarioSaida=" + horarioSaida +
                ", ordem=" + ordem +
                ", linha=" + linha +
                ", assentosTrechoLinha=" + assentosTrechoLinha +
                ", trecho=" + trecho +
                '}';
    }
}
