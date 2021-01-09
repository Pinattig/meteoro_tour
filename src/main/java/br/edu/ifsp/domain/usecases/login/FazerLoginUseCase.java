package br.edu.ifsp.domain.usecases.login;

public class FazerLoginUseCase {

    private AutenticarSenhaUseCase autenticarSenhaUseCase;

    public FazerLoginUseCase(LoginDAO loginDAO) {
        this.autenticarSenhaUseCase = new AutenticarSenhaUseCase(loginDAO);
    }

    public boolean loginAsAdmin(String senha, String login) {
        return autenticarSenhaUseCase.authenticateLogin(senha, login);
    }

    public boolean loginAsSeller() {
        return true;
    }
}
