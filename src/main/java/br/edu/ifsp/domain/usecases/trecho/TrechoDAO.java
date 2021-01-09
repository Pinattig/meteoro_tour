package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.utils.DAO;

import java.time.LocalDate;

public interface TrechoDAO extends DAO<Trecho, Integer> {

    public boolean trechoIsUsed(Integer id);

    public Trecho getByCities(String cidadeOrigem, String cidadeDestino, LocalDate data);
}
