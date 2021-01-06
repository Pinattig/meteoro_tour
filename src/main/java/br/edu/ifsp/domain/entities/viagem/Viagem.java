package br.edu.ifsp.domain.entities.viagem;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;

import java.time.LocalDate;
import java.time.LocalTime;

public class Viagem {
    private LocalDate data;
    private LocalTime horarioSaida;
    private String cidadeOrigem;
    private String cidadeDestino;
    private boolean assentosDisponiveis;
    private boolean assentosPrefDisponiveis;

    private Passagem passagem;
    private TrechoLinha trechoLinha;
    private Linha linha;

    public Viagem() {
    }

    public Viagem(String cidadeOrigem, String cidadeDestino, boolean assentosDisponiveis, boolean assentosPrefDisponiveis, Passagem passagem, TrechoLinha trechoLinha, Linha linha) {
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.assentosDisponiveis = assentosDisponiveis;
        this.assentosPrefDisponiveis = assentosPrefDisponiveis;
        this.passagem = passagem;
        this.trechoLinha = trechoLinha;
        this.linha = linha;
        this.data = LocalDate.now();
        this.horarioSaida = LocalTime.now();
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public boolean isAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis(boolean assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public boolean isAssentosPrefDisponiveis() {
        return assentosPrefDisponiveis;
    }

    public void setAssentosPrefDisponiveis(boolean assentosPrefDisponiveis) {
        this.assentosPrefDisponiveis = assentosPrefDisponiveis;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public TrechoLinha getTrechoLinha() {
        return trechoLinha;
    }

    public Linha getLinha() {
        return linha;
    }

    public void verificarDisponibilidade() {

    }

    @Override
    public String toString() {
        return "Viagem{" +
                "data=" + data +
                ", horarioSaida=" + horarioSaida +
                ", cidadeOrigem='" + cidadeOrigem + '\'' +
                ", cidadeDestino='" + cidadeDestino + '\'' +
                ", assentosDisponiveis=" + assentosDisponiveis +
                ", assentosPrefDisponiveis=" + assentosPrefDisponiveis +
                ", passagem=" + passagem +
                ", trechoLinha=" + trechoLinha +
                ", linha=" + linha +
                '}';
    }
}
