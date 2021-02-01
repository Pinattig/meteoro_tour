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
        WindowLoader.setRoot("PassageUI", 455, 655);
    }

    public void gerenciarFuncionario(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("EmployeeUI", 335, 610);
    }

    public void gerenciarOnibus(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("BusUI", 330, 640);
    }

    public void gerenciarLinha(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("BusLineUI", 425, 545);
    }

    public void gerenciarTrecho(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PatchUI", 410, 550);
    }
}
