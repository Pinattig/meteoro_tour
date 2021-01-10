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

    private List<TrechoLinha> listTrechoLinha;

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

    public List<TrechoLinha> gerarTrechosViagem(String cidadeOrigem, String cidadeDestino, LocalDate data) {
        List<TrechoLinha> trechosViagem = new ArrayList<>();

        for (TrechoLinha trechoLinha : listTrechoLinha) {
            String trechoCidadeOrigem = trechoLinha.getTrecho().getCidadeOrigem();
            if(trechoCidadeOrigem.equals(cidadeOrigem)){
                trechosViagem.add(trechoLinha);
                break;
            }
        }

        if(trechosViagem.isEmpty())
            return null;

        String trechoCidadeDestino = trechosViagem.get(0).getTrecho().getCidadeDestino();
        if(trechoCidadeDestino.equals(cidadeDestino))
            return trechosViagem;

        int index = trechosViagem.get(0).getOrdem();
        for (int i = index + 1; i < listTrechoLinha.size(); i++) {
            TrechoLinha trechoLinha = listTrechoLinha.get(i);
            trechosViagem.add(trechoLinha);
            String destinoTrechoLinha = trechoLinha.getTrecho().getCidadeDestino();
            if(destinoTrechoLinha.equals(cidadeDestino))
                break;
        }

        return trechosViagem;
    }

    public void addTrechoLinha(Trecho trecho, LocalTime horarioSaida) {
        AssentosTrechoLinha assentos = new AssentosTrechoLinha(LocalDate.now());
        TrechoLinha trechoLinha = new TrechoLinha(horarioSaida, listTrechoLinha.size()+1, this, assentos, trecho);
        listTrechoLinha.add(trechoLinha);
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
