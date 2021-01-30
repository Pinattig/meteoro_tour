package br.edu.ifsp.domain.usecases.login;

public interface LoginDAO {

    public boolean authenticateLogin(String senha, String login);
    public boolean createLogin(String senha, String login);
}
