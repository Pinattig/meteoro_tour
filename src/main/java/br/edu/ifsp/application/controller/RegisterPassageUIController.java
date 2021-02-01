package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.passagem.TipoEspecial;
import br.edu.ifsp.domain.entities.viagem.Viagem;
import br.edu.ifsp.domain.usecases.viagem.GerarViagemUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static br.edu.ifsp.application.main.Main.venderPassagemUseCase;

public class RegisterPassageUIController {

    @FXML Label lbNumeroPassagem;
    @FXML Label lbValorPassagem;
    @FXML Label lbNomeCliente;
    @FXML Label lbRgCliente;
    @FXML Label lbCpfCliente;
    @FXML Label lbTelefoneCliente;
    @FXML Label lbTipoEspecial;
    @FXML Label lbAssento;
    @FXML Label lbMsgFeedBack;

    @FXML TextField txtNumeroPassagem;
    @FXML TextField txtValorPassagem;
    @FXML TextField txtNomeCliente;
    @FXML TextField txtRgCliente;
    @FXML TextField txtCpfCliente;
    @FXML TextField txtTelefoneCliente;
    @FXML TextField txtTipoEspecial;
    @FXML TextField txtAssento;
    @FXML TextField txtOrigem;
    @FXML TextField txtData;
    @FXML TextField txtDestino;
    @FXML TextField txtHora;

    @FXML CheckBox cbSeguro;

    @FXML Button btnVender;

    public void vender(ActionEvent actionEvent) {
        try{
            venderPassagemUseCase.venderPassagem(txtOrigem.getText(),
                    txtDestino.getText(),
                    txtData.getText(),
                    txtHora.getText(),
                    txtAssento.getText(),
                    txtNomeCliente.getText(),
                    txtCpfCliente.getText(),
                    txtRgCliente.getText(),
                    txtTelefoneCliente.getText(),
                    cbSeguro.isSelected(),
                    TipoEspecial.NAO);
            lbMsgFeedBack.setText("Passagem criada com sucesso");
        }catch (RuntimeException e){
            lbMsgFeedBack.setText(e.getMessage());
        }
    }

    public void backToAction(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PassageUI", 455, 655);
    }
}
