package br.edu.ifsp.domain.entities.viagem;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
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
    private List<TrechoLinha> trechoLinhas;
    private Linha linha;

    public Viagem() {
    }

    public Viagem(String cidadeOrigem, String cidadeDestino,Linha linha, LocalDate data, LocalTime horarioSaida, List<TrechoLinha> trechosViagem) {
        this(UUID.randomUUID(), cidadeOrigem, cidadeDestino, linha, data, horarioSaida, trechosViagem);
    }

    public Viagem(UUID id, String cidadeOrigem, String cidadeDestino, Linha linha, LocalDate data, LocalTime horarioSaida, List<TrechoLinha> trechosViagem) {
        this.id = id;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.trechoLinhas = trechosViagem;
        this.linha = linha;
        this.data = LocalDate.now();
        this.horarioSaida = LocalTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Linha getLinha() {
        return linha;
    }

    public Map<String, Boolean> getAssentosDisponiveis(){
        Map<String, Boolean> responseMap = new LinkedHashMap<>();

        for (TrechoLinha trechoLinha : trechoLinhas) {

            if(responseMap.isEmpty()){
                responseMap = trechoLinha.getAssentosTrechoLinha().getAssentosVendidos();
                responseMap = responseMap.entrySet()
                            .stream()
                            .filter(x -> x.getValue() == false)
                            .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            }else{
                for (String key : responseMap.keySet()) {
                    if(trechoLinha.getAssentosTrechoLinha().getAssentosVendidos().get(key)){
                        responseMap.remove(key);
                    }
                }
            }
        }

        return responseMap;
    }

    public boolean verificarAssentosDisponiveis(){
        for (TrechoLinha trechoLinha : trechoLinhas) {
            if(trechoLinha.getAssentosTrechoLinha().getAssentosDisponiveis() == 0)
                return false;
        }
        return true;
    }

    public boolean verificarAssentosPrefDisponiveis(){

        for (TrechoLinha trechoLinha : trechoLinhas) {
            if(trechoLinha.getAssentosTrechoLinha().getAssentosPrefDisponiveis() == 0){
                return false;
            }
        }
        return true;
    }

    public void ocuparAssentos(String key){
        for (TrechoLinha trechoLinha : trechoLinhas) {
            trechoLinha.ocuparAssento(key);
        }
    }

    public void ocuparAssentosPref(String key){
        for (TrechoLinha trechoLinha : trechoLinhas) {
            trechoLinha.ocuparAssento(key);
            trechoLinha.getAssentosTrechoLinha().decreaseAssentoPref();
        }
    }

    public List<TrechoLinha> getTrechoLinhas() {
        return trechoLinhas;
    }

    @Override
    public String toString() {
        return "Viagem{" +
                "data=" + data +
                ", horarioSaida=" + horarioSaida +
                ", cidadeOrigem='" + cidadeOrigem + '\'' +
                ", cidadeDestino='" + cidadeDestino + '\'' +
                ", trechoLinha=" + trechoLinhas+
                ", linha=" + linha +
                '}';
    }
}
