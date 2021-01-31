package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminMainUIController {
    
    @FXML Button btnGerenciarPassagem;
    @FXML Button btnGerenciarFuncionario;
    @FXML Button btnGerenciarOnibus;
    @FXML Button btnGerenciarLinha;
    @FXML Button btnGerenciarTrecho;

    public void gerenciarPassagem(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PassageUI");
    }

    public void gerenciarFuncionario(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("EmployeeUI");
    }

    public void gerenciarOnibus(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("BusUI");
    }

    public void gerenciarLinha(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("BusLineUI");
    }

    public void gerenciarTrecho(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PatchUI");
    }
}
