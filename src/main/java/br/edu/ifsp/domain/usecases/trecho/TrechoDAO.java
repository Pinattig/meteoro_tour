package br.edu.ifsp.domain.usecases.trecho;

import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.utils.DAO;

import java.time.LocalDate;
import java.util.UUID;

public interface TrechoDAO extends DAO<Trecho, UUID> {

    public Trecho getByCities(String cidadeOrigem, String cidadeDestino);
}
