package br.edu.ifsp.domain.entities.linha;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Linha {
    private Long id;
    private String nome;

    private List<TrechoLinha> listTrechoLinha;

    public Linha() {
    }

    public Linha(String nome, List<TrechoLinha> listTrechoLinha) {
        this(null, nome, listTrechoLinha);
    }

    public Linha(Long id, String nome, List<TrechoLinha> listTrechoLinha) {
        this.listTrechoLinha = listTrechoLinha;
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

    public List<TrechoLinha> gerarTrechosViagem(String cidadeOrigem, String cidadeDestino, LocalDate data) {
        List<TrechoLinha> returnTrechoLinha = new ArrayList<>();

        for (TrechoLinha trechoLinha : listTrechoLinha) {
            String trechoCidadeOrigem = trechoLinha.getTrecho().getCidadeOrigem();
            if(trechoCidadeOrigem.equals(cidadeOrigem)){
                returnTrechoLinha.add(trechoLinha);
            }
        }

        if(returnTrechoLinha.isEmpty())
            return null;

        String trechoCidadeDestino = returnTrechoLinha.get(0).getTrecho().getCidadeDestino();
        if(trechoCidadeDestino.equals(cidadeDestino))
            return returnTrechoLinha;

        int index = returnTrechoLinha.get(0).getOrdem();
        for (int i = index + 1; i < listTrechoLinha.size(); i++) {
            TrechoLinha trechoLinha = listTrechoLinha.get(i);
            returnTrechoLinha.add(trechoLinha);


        }
    }

    public void addTrechoLinha(Trecho trecho, LocalTime horarioSaida) {

    }

    @Override
    public String toString() {
        return "Linha{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", trechoLinha=" + listTrechoLinha +
                '}';
    }
}
