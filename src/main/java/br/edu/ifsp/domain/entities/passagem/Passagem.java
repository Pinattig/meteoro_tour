package br.edu.ifsp.domain.entities.passagem;

import br.edu.ifsp.domain.entities.viagem.Viagem;

import java.time.LocalDate;

public class Passagem {
    private Long numPassagem;
    private double precoTotal;
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private boolean seguro;
    private TipoEspecial tipoEspecial;
    private String assento;

    private Viagem viagem;

    public Passagem() {
    }

    public Passagem(double precoTotal, String nome, String cpf, String rg, String telefone, boolean seguro, Viagem viagem,TipoEspecial tipoEspecial, String assento) {
        this(null, precoTotal, nome, cpf, rg, telefone, seguro, viagem, tipoEspecial, assento);
    }

    public Passagem(Long numPassagem, double precoTotal, String nome, String cpf, String rg, String telefone, boolean seguro, Viagem viagem, TipoEspecial tipoEspecial, String assento) {
        this.numPassagem = numPassagem;
        this.precoTotal = precoTotal;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.seguro = seguro;
        this.viagem = viagem;
        this.tipoEspecial = tipoEspecial;
        this.assento = assento;
    }


    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public long getNumPassagem() {
        return numPassagem;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public boolean verificarValidade() {
        return LocalDate.now().isAfter(this.viagem.getData());
    }

    public TipoEspecial getTipoEspecial() {
        return tipoEspecial;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    @Override
    public String toString() {
        return "Passagem{" +
                "numPassagem=" + numPassagem +
                ", precoTotal=" + precoTotal +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", telefone='" + telefone + '\'' +
                ", seguro=" + seguro +
                ", tipoEspecial=" + tipoEspecial +
                ", assento='" + assento + '\'' +
                ", viagem=" + viagem +
                '}';
    }
}
