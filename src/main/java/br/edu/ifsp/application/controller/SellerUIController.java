package br.edu.ifsp.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SellerUIController {
    
    @FXML Label lbNumeroPassagem;
    @FXML Label lbValorPassagem;
    @FXML Label lbNomeCliente;
    @FXML Label lbRGcliente;
    @FXML Label lbCPFcliente;
    @FXML Label lbTelefoneCliente;
    @FXML Label lbTipoEspecial;
    @FXML Label lbAssento;
    
    @FXML TextField txtConsulta;
    
    @FXML CheckBox cbSeguro;
    
    @FXML Button btnVenderPassagem;
    @FXML Button btnPesquisar;


    public void venderPassagem(ActionEvent actionEvent) {
    }

    public void pesquisar(ActionEvent actionEvent) {
    }
}
