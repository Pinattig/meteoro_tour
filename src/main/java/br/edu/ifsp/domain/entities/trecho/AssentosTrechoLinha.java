package br.edu.ifsp.domain.entities.trecho;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class AssentosTrechoLinha {
    private LocalDate data;
    private Map<String, Boolean> assentosVendidos;


    public AssentosTrechoLinha() {
    }

    public AssentosTrechoLinha(LocalDate data) {
        this.data = data;
        this.assentosVendidos = initializeSeats();
    }

    public LocalDate getData() {
        return data;
    }

    public Map<String, Boolean> getAssentosVendidos() {
        return assentosVendidos;
    }

    private Map<String, Boolean> initializeSeats() {
        Map<String, Boolean> map = new LinkedHashMap<>();
        for (int i = 1; i < 44; i++) {
            map.put(String.valueOf(i), false);
        }
        return map;
    }

    @Override
    public String toString() {
        return "AssentosTrechoLinha{" +
                "data=" + data +
                ", assentosVendidos=" + assentosVendidos +
                '}';
    }
}
