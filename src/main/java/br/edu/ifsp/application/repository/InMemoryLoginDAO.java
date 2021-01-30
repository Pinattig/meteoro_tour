package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.usecases.login.LoginDAO;

import java.util.LinkedHashMap;
import java.util.Map;

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
