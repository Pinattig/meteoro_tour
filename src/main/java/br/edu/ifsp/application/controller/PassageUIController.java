package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.*;

public class PassageUIController {

    @FXML
    TextField txtConsultarPassagem;
    @FXML
    TextField txtDataViagem;

    @FXML
    Label lbNumeroPassagem;
    @FXML
    Label lbValorPassagem;
    @FXML
    Label lbNomeCliente;
    @FXML
    Label lbRgCliente;
    @FXML
    Label lbCpfCliente;
    @FXML
    Label lbTelefoneCliente;
    @FXML
    Label lbTipoEspecial;
    @FXML
    Label lbNumeroViagem;
    @FXML
    Label lbDataViagem;
    @FXML
    Label lbAssento;
    @FXML
    Label lbError;

    @FXML
    Button btnConsultarPassagem;
    @FXML
    Button btnVenderPassagem;
    @FXML
    Button btnDevolverPassagem;
    @FXML
    Button btnReagendarPassagem;
    @FXML
    Button btnReemitirPassagem;
    @FXML
    Button btnReagendar;


    @FXML
    CheckBox cbSeguro;

    @FXML
    Pane areaCamposPassagem;

    private Optional<Passagem> passagemConsultada;

    @FXML
    private void initialize() {

    }

    public void venderPassagem(ActionEvent actionEvent) throws IOException {
        //venderPassagemUseCase.venderPassagem(passagem);

        WindowLoader.setRoot("SellPassageUI");
    }

    public void reemitirPassagem(ActionEvent actionEvent) {
        try {
            String cpfCliente = lbCpfCliente.getText();
            reemitirPassagemUseCase.reemitirPassagem(cpfCliente);
        } catch (RuntimeException e) {
            lbError.setText(e.getMessage());
            lbError.setVisible(true);
        }
    }

    public void reagendarPassagem(ActionEvent actionEvent) {
        try {
            reagendarPassagensUseCase.reagendar(Long.parseLong(lbNumeroPassagem.getText()), LocalDate.parse(txtDataViagem.getText()));
            String dataViagem = txtDataViagem.getText();
            lbDataViagem.setText(dataViagem);
            alterarEstadosCampoData();
        } catch (RuntimeException e) {
            lbError.setText(e.getMessage());
            lbError.setVisible(true);
        }
    }

    public void habilitarCampoData(ActionEvent actionEvent) {
        String dataViagem = lbDataViagem.getText();
        txtDataViagem.setText(dataViagem);
        alterarEstadosCampoData();
    }

    public void devolverPassagem(ActionEvent actionEvent) {
        try {
            devolverPassagemUseCase.devolverPassagem(passagemConsultada);
        } catch (RuntimeException e) {
            lbError.setText(e.getMessage());
            lbError.setVisible(true);
        }
    }

    public void consultarPassagem(ActionEvent actionEvent) {
        Long numPassagem = Long.parseLong(txtConsultarPassagem.getText());
        passagemConsultada = consultarPassagemVendidaUseCase.consultarPassagem(numPassagem);

        setarTextoDosCampos();
        areaCamposPassagem.setVisible(true);

        habilitarBotoes(true);
    }

    private void setarTextoDosCampos() {
        lbNumeroPassagem.setText(String.valueOf(passagemConsultada.get().getNumPassagem()));
        lbValorPassagem.setText(String.valueOf(passagemConsultada.get().getPrecoTotal()));
        lbNomeCliente.setText(passagemConsultada.get().getNome());
        lbRgCliente.setText(passagemConsultada.get().getRg());
        lbCpfCliente.setText(passagemConsultada.get().getCpf());
        lbTelefoneCliente.setText(passagemConsultada.get().getTelefone());
        lbTipoEspecial.setText(passagemConsultada.get().getTipoEspecial().name());
        lbAssento.setText(passagemConsultada.get().getAssento());
        lbNumeroViagem.setText(String.valueOf(passagemConsultada.get().getViagem().getId()));
        lbDataViagem.setText(String.valueOf(passagemConsultada.get().getViagem().getData()));
        cbSeguro.setSelected(true);
    }

    private void habilitarBotoes(boolean estado) {
        btnDevolverPassagem.setVisible(estado);
        btnReagendarPassagem.setVisible(estado);
        btnReemitirPassagem.setVisible(estado);
    }

    private void alterarEstadosCampoData() {
        txtDataViagem.setVisible(!txtDataViagem.isVisible());
        lbDataViagem.setVisible(!lbDataViagem.isVisible());
        habilitarBotoes(btnReagendar.isVisible());
        btnReagendar.setVisible(!btnReagendar.isVisible());
    }
}
