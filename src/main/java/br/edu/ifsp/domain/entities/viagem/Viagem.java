package br.edu.ifsp.domain.entities.viagem;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Viagem {
    private UUID id;
    private LocalDate data;
    private LocalTime horarioSaida;
    private String cidadeOrigem;
    private String cidadeDestino;
    private boolean assentosDisponiveis;
    private boolean assentosPrefDisponiveis;

    private Passagem passagem;
    private List<TrechoLinha> trechoLinhas;
    private Linha linha;

    public Viagem() {
    }

    public Viagem(String cidadeOrigem, String cidadeDestino, boolean assentosDisponiveis, boolean assentosPrefDisponiveis, Passagem passagem, TrechoLinha trechoLinha, Linha linha) {
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.assentosDisponiveis = assentosDisponiveis;
        this.assentosPrefDisponiveis = assentosPrefDisponiveis;
        this.passagem = passagem;
        this.trechoLinhas.add(trechoLinha);
        this.linha = linha;
        this.data = LocalDate.now();
        this.horarioSaida = LocalTime.now();
        this.id = UUID.randomUUID();
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

    public Linha getLinha() {
        return linha;
    }

    public Map<String, Boolean> verificarDisponibilidade() {
        Map<String, Boolean> totalAssentos = trechoLinhas.get(0).getAssentosTrechoLinha().getAssentosVendidos();
        return totalAssentos.entrySet()
                .stream()
                .filter(x -> x.getValue() == false)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
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
                ", trechoLinha=" + trechoLinhas+
                ", linha=" + linha +
                '}';
    }
}
