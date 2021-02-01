package br.edu.ifsp.domain.entities.trecho;

import java.time.LocalTime;
import java.util.UUID;

public class Trecho {
    private UUID id;
    private String nome;
    private String cidadeOrigem;
    private String cidadeDestino;
    private double quilometragem;
    private LocalTime tempoDuracao;
    private double valorPassagem;
    private double taxaEmbarque;
    private double valorSeguro;


    public Trecho() {

    }

    public Trecho(String cidadeOrigem, String cidadeDestino, double quilometragem, LocalTime tempoDuracao, double valorPassagem, double taxaEmbarque, double valorSeguro, String nome) {
        this.id = UUID.randomUUID();
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.quilometragem = quilometragem;
        this.tempoDuracao = tempoDuracao;
        this.valorPassagem = valorPassagem;
        this.taxaEmbarque = taxaEmbarque;
        this.valorSeguro = valorSeguro;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public LocalTime getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(LocalTime tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(double valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public double getTaxaEmbarque() {
        return taxaEmbarque;
    }

    public void setTaxaEmbarque(double taxaEmbarque) {
        this.taxaEmbarque = taxaEmbarque;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    @Override
    public String toString() {
        return "Trecho{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidadeOrigem='" + cidadeOrigem + '\'' +
                ", cidadeDestino='" + cidadeDestino + '\'' +
                ", quilometragem=" + quilometragem +
                ", tempoDuracao=" + tempoDuracao +
                ", valorPassagem=" + valorPassagem +
                ", taxaEmbarque=" + taxaEmbarque +
                ", valorSeguro=" + valorSeguro +
                '}';
    }
}
