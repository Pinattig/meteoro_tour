package br.edu.ifsp.domain.entities.linha;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import br.edu.ifsp.domain.entities.viagem.Viagem;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Linha {
    private Long id;
    private String nome;

    private Viagem viagem;
    private List<TrechoLinha> trechoLinha;

    public Linha() {
    }

    public Linha(String nome, Viagem viagem, TrechoLinha trechoLinha) {
        this(null, nome, viagem, trechoLinha);
    }

    public Linha(Long id, String nome, Viagem viagem, TrechoLinha trechoLinha) {
        this.id = id;
        this.nome = nome;
        this.viagem = viagem;
        this.trechoLinha.add(trechoLinha);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public List<TrechoLinha> gerarTrechosViagem(String cidadeOrigem, String cidadeDestino, LocalDate data) {
        return null;
    }

    public void addTrechoLinha(Trecho trecho, LocalTime horarioSaida) {

    }

    @Override
    public String toString() {
        return "Linha{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", viagem=" + viagem +
                ", trechoLinha=" + trechoLinha +
                '}';
    }
}
