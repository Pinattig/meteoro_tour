package br.edu.ifsp.application.controller;

import br.edu.ifsp.domain.entities.viagem.Viagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class SellPassageUIController {

    @FXML Label lbNumeroPassagem;
    @FXML Label lbValorPassagem;
    @FXML Label lbNomeCliente;
    @FXML Label lbRgCliente;
    @FXML Label lbCpfCliente;
    @FXML Label lbTelefoneCliente;
    @FXML Label lbTipoEspecial;
    @FXML Label lbAssento;

    @FXML TextField txtNumeroPassagem;
    @FXML TextField txtValorPassagem;
    @FXML TextField txtNomeCliente;
    @FXML TextField txtRgCliente;
    @FXML TextField txtCpfCliente;
    @FXML TextField txtTelefoneCliente;
    @FXML TextField txtTipoEspecial;
    @FXML TextField txtAssento;

    @FXML CheckBox cbSeguro;

    @FXML Button btnVender;

    @FXML TableView<Viagem> tableViagens;

    @FXML TableColumn<Viagem, LocalDate> cData;
    @FXML TableColumn<Viagem, String> cHorarioSaida;
    @FXML TableColumn<Viagem, String> cCidadeOrigem;
    @FXML TableColumn<Viagem, String> cCidadeDestino;


    public void vender(ActionEvent actionEvent) {
    }
}
