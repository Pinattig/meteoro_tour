package br.edu.ifsp.domain.entities.trecho;

import java.time.LocalDate;
import java.util.Map;

public class AssentosTrechoLinha {
    private LocalDate data;
    private Map<String, Boolean> assentosVendidos;


    public AssentosTrechoLinha() {
    }

    public AssentosTrechoLinha(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }


    @Override
    public String toString() {
        return "AssentosTrechoLinha{" +
                "data=" + data +
                ", assentosVendidos=" + assentosVendidos +
                '}';
    }
}
