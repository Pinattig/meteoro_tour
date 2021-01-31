package br.edu.ifsp.application.controller;


import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.utils.UserPermissionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.fazerLoginUseCase;

public class LoginUIController extends UserPermissionManager {

    @FXML private TextField txtLogin;
    @FXML private TextField txtSenha;
    @FXML private Label lbMsgError;

    public void entrarAdmin(ActionEvent actionEvent) throws IOException {
        String senha = txtSenha.getText();
        String login = txtLogin.getText();

        try{
            boolean isLogado =  fazerLoginUseCase.loginAsAdmin(senha, login);
            if(isLogado){
                super.setUserPermission("Administrador");
                WindowLoader.setRoot("BusUI");
            }else{
                lbMsgError.setVisible(true);
                lbMsgError.setText("Usuário ou senha incorretos");
            }
        }catch (RuntimeException e){
            lbMsgError.setText(e.getMessage());
            lbMsgError.setVisible(true);
        }
    }

    public void entrarVendedor(ActionEvent actionEvent) throws IOException {
        super.setUserPermission("Vendedor");
        WindowLoader.setRoot("PassageUI");
    }


}
