package br.edu.ifsp.domain.usecases.login;

import br.edu.ifsp.utils.exceptions.IncorrectPasswordException;

public interface LoginDAO {

    public boolean authenticateLogin(String senha, String login);
}
