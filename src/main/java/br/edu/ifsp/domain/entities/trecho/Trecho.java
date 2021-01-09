package br.edu.ifsp.domain.entities.trecho;

import java.util.List;
import java.util.UUID;

public class Trecho {
    private UUID id;
    private String cidadeOrigem;
    private String cidadeDestino;
    private double quilometragem;
    private double tempoDuracao;
    private double valorPassagem;
    private double taxaEmbarque;
    private double valorSeguro;

    private List<TrechoLinha> trechoLinhas;

    public Trecho() {
    }

    public Trecho(String cidadeOrigem, String cidadeDestino, double quilometragem, double tempoDuracao, double valorPassagem, double taxaEmbarque, double valorSeguro) {
        this.id = UUID.randomUUID();
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.quilometragem = quilometragem;
        this.tempoDuracao = tempoDuracao;
        this.valorPassagem = valorPassagem;
        this.taxaEmbarque = taxaEmbarque;
        this.valorSeguro = valorSeguro;
    }

    public UUID getId() {
        return id;
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

    public double getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(double tempoDuracao) {
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
                "cidadeOrigem='" + cidadeOrigem + '\'' +
                ", cidadeDestino='" + cidadeDestino + '\'' +
                ", quilometragem=" + quilometragem +
                ", tempoDuracao=" + tempoDuracao +
                ", valorPassagem=" + valorPassagem +
                ", taxaEmbarque=" + taxaEmbarque +
                ", valorSeguro=" + valorSeguro +
                ", trechoLinha=" + trechoLinhas +
                '}';
    }
}
