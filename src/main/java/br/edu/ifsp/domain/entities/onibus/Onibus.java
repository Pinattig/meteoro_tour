package br.edu.ifsp.domain.entities.onibus;

public class Onibus {
    private String renavam;
    private String placa;

    public Onibus() {
    }

    public Onibus(String renavam, String placa) {
        this.renavam = renavam;
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Onibus{" +
                "renavam='" + renavam + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }
}
