package br.edu.ifsp.application.controller;


import br.edu.ifsp.domain.usecases.login.FazerLoginUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginUIController {

    @FXML TextField txtLogin;
    @FXML TextField txtSenha;
    @FXML Button btnEntrarVendedor;
    @FXML Button btnEntrarAdmin;



    public void entrarAdmin(ActionEvent actionEvent) {
    }

    public void entrarVendedor(ActionEvent actionEvent) {

    }
}
