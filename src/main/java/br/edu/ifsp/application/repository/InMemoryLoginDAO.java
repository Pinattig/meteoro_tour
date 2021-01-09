package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.linha.LinhaDAO;
import br.edu.ifsp.domain.usecases.login.LoginDAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryLoginDAO implements LoginDAO {

    private static final Map<String, String> db = new LinkedHashMap<>();

    @Override
    public boolean authenticateLogin(String senha, String login) {
        return db.containsKey(login) && db.get(login).equals(senha);
    }

    @Override
    public boolean createLogin(String senha, String login){
        db.put(login, senha);
        return true;
    }


}
