package br.edu.ifsp.domain.entities.trecho;

import br.edu.ifsp.domain.entities.linha.Linha;

import java.time.LocalTime;
import java.util.UUID;

public class TrechoLinha {
    private UUID id;
    private LocalTime horarioSaida;
    private int ordem;

    private Linha linha;
    private AssentosTrechoLinha assentosTrechoLinha;
    private Trecho trecho;

    public TrechoLinha() {
    }

    public TrechoLinha(LocalTime horarioSaida, int ordem, Linha linha, AssentosTrechoLinha assentosTrechoLinha, Trecho trecho) {
        this.id = UUID.randomUUID();
        this.horarioSaida = horarioSaida;
        this.ordem = ordem;
        this.linha = linha;
        this.assentosTrechoLinha = assentosTrechoLinha;
        this.trecho = trecho;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public AssentosTrechoLinha getAssentosTrechoLinha() {
        return assentosTrechoLinha;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public void ocuparAssento(String key){
        this.assentosTrechoLinha.setAssento(key);
    }

    public String getTrechoName(){
        return trecho != null ? trecho.getNome() : null;
    }

    public UUID getTrechoId(){
        return  trecho != null ? trecho.getId() : null;
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
