package br.edu.ifsp.domain.entities.trecho;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class AssentosTrechoLinha {
    private LocalDate data;
    private Map<String, Boolean> assentosVendidos;
    private Integer assentosPrefDisponiveis;
    private Integer assentosDisponiveis;


    public AssentosTrechoLinha() {
    }

    public AssentosTrechoLinha(LocalDate data) {
        this.data = data;
        this.assentosVendidos = initializeSeats();
        this.assentosPrefDisponiveis = 2;
        this.assentosDisponiveis = 44;
    }

    public LocalDate getData() {
        return data;
    }

    public Map<String, Boolean> getAssentosVendidos() {
        return assentosVendidos;
    }

    public Integer getAssentosPrefDisponiveis() {
        return assentosPrefDisponiveis;
    }

    public void setAssentosPrefDisponiveis(Integer assentosPrefDisponiveis) {
        this.assentosPrefDisponiveis = assentosPrefDisponiveis;
    }

    private Map<String, Boolean> initializeSeats() {
        Map<String, Boolean> map = new LinkedHashMap<>();
        for (int i = 1; i < 44; i++) {
            map.put(String.valueOf(i), false);
        }
        return map;
    }

    public void decreaseAssentoPref(){
        if(this.assentosPrefDisponiveis > 0)
            this.assentosPrefDisponiveis--;
    }
    public void decreaseAssento(){
        if(this.assentosPrefDisponiveis > 0)
            this.assentosPrefDisponiveis--;
    }

    public Integer getAssentosDisponiveis() {
        return assentosPrefDisponiveis;
    }

    public void setAssento(String key){
        this.assentosVendidos.put(key, true);
    }

    @Override
    public String toString() {
        return "AssentosTrechoLinha{" +
                "data=" + data +
                ", assentosVendidos=" + assentosVendidos +
                '}';
    }
}
