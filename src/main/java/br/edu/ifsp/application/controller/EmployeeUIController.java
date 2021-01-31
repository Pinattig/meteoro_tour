package br.edu.ifsp.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EmployeeUIController {

    @FXML TextField txtConsultar;

    @FXML Label lbNome;
    @FXML Label lbRg;
    @FXML Label lbCpf;
    @FXML Label lbCargo;

    @FXML Button btnConsultar;
    @FXML Button btnCadastrar;
    @FXML Button btnEditar;
    @FXML Button btnRemover;

    @FXML Pane areaCampos;


    public void consultarFuncionario(ActionEvent actionEvent) {
    }

    public void cadastrarFuncionario(ActionEvent actionEvent) {
    }

    public void editarFuncionario(ActionEvent actionEvent) {
    }

    public void removerFuncionario(ActionEvent actionEvent) {
    }
}
