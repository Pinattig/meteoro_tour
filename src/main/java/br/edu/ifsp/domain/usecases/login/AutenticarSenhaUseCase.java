package br.edu.ifsp.domain.usecases.login;

import br.edu.ifsp.utils.exceptions.IncorrectPasswordException;

public class AutenticarSenhaUseCase {

    private LoginDAO loginDAO;

    public AutenticarSenhaUseCase(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public boolean authenticateLogin(String senha, String login) throws IncorrectPasswordException {
        if(!loginDAO.authenticateLogin(senha, login))
            throw new IncorrectPasswordException("Usuário ou Senha incorreta");
        return true;
    }
}
