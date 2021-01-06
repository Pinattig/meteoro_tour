package br.edu.ifsp.domain.entities.trecho;

import java.time.LocalDate;
import java.util.Map;

public class AssentosTrechoLinha {
    private LocalDate data;
    private Map<String, Boolean> assentosVendidos;

    private TrechoLinha trechoLinha;

    public AssentosTrechoLinha() {
    }

    public AssentosTrechoLinha(LocalDate data, TrechoLinha trechoLinha) {
        this.data = data;
        this.trechoLinha = trechoLinha;
    }

    public LocalDate getData() {
        return data;
    }

    public TrechoLinha getTrechoLinha() {
        return trechoLinha;
    }

    @Override
    public String toString() {
        return "AssentosTrechoLinha{" +
                "data=" + data +
                ", assentosVendidos=" + assentosVendidos +
                ", trechoLinha=" + trechoLinha +
                '}';
    }
}
