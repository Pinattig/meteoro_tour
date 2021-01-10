package br.edu.ifsp.domain.entities.linha;

import br.edu.ifsp.domain.entities.trecho.AssentosTrechoLinha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Linha {
    private Long id;
    private String nome;



    public Linha() {
    }

    public Linha(String nome) {
        this(null, nome);
    }

    public Linha(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Linha{" +
                "id=" + id +
                ", nome='" + nome +
                '}';
    }
}
