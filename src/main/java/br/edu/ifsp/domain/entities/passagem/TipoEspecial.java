package br.edu.ifsp.domain.entities.passagem;

public enum TipoEspecial {

    IDOSO("Idoso"),
    DEFICIENTE("Deficiente"),
    NAO("Não");

    private String label;

    TipoEspecial(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
