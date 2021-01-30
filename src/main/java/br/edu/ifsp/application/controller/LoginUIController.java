package br.edu.ifsp.application.controller;


import br.edu.ifsp.utils.UserPermissionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static br.edu.ifsp.application.main.Main.fazerLoginUseCase;

public class LoginUIController extends UserPermissionManager {

    @FXML private TextField txtLogin;
    @FXML private TextField txtSenha;

    public void entrarAdmin(ActionEvent actionEvent) {
        String senha = txtSenha.getText();
        String login = txtLogin.getText();

        boolean isLogado =  fazerLoginUseCase.loginAsAdmin(senha, login);
        if(isLogado)
            super.setUserPermission("Administrador");
    }

    public void entrarVendedor(ActionEvent actionEvent) {
        super.setUserPermission("Vendedor");
    }


}
