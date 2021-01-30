package br.edu.ifsp.application.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static br.edu.ifsp.application.main.Main.fazerLoginUseCase;

public class LoginUIController {

    @FXML private TextField txtLogin;
    @FXML private TextField txtSenha;
    @FXML private Button btnEntrarVendedor;
    @FXML private Button btnEntrarAdmin;

    private static String userPermission;


    public void entrarAdmin(ActionEvent actionEvent) {
        String senha = txtSenha.getText();
        String login = txtLogin.getText();

        boolean isLogado =  fazerLoginUseCase.loginAsAdmin(senha, login);
        if(isLogado)
            userPermission = "Administrador";
    }

    public void entrarVendedor(ActionEvent actionEvent) {
        userPermission = "Vendedor";
    }

    public static String getUserPermission() {
        return userPermission;
    }
}
