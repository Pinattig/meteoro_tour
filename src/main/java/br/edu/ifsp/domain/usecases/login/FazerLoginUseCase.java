package br.edu.ifsp.domain.usecases.login;

public class FazerLoginUseCase {

    private AutenticarSenhaUseCase autenticarSenhaUseCase;
    private LoginDAO loginDAO;

    public FazerLoginUseCase(LoginDAO loginDAO) {
        this.autenticarSenhaUseCase = new AutenticarSenhaUseCase(loginDAO);
        this.loginDAO = loginDAO;
    }

    public boolean loginAsAdmin(String senha, String login) {
        return autenticarSenhaUseCase.authenticateLogin(senha, login);
    }

    public boolean loginAsSeller() {
        return true;
    }

    public boolean createLogin(String login, String senha){
        return loginDAO.createLogin(senha, login);
    }
}
