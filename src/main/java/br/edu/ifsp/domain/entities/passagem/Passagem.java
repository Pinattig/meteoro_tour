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

    private Viagem viagem;

    public Passagem() {
    }

    public Passagem(double precoTotal, String nome, String cpf, String rg, String telefone, boolean seguro, Viagem viagem) {
        this(null, precoTotal, nome, cpf, rg, telefone, seguro, viagem);
    }

    public Passagem(Long numPassagem, double precoTotal, String nome, String cpf, String rg, String telefone, boolean seguro, Viagem viagem) {
        this.numPassagem = numPassagem;
        this.precoTotal = precoTotal;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.seguro = seguro;
        this.viagem = viagem;
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
        return LocalDate.now() == this.viagem.getData();
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
                ", viagem=" + viagem +
                '}';
    }
}
