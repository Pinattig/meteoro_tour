package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.passagem.Passagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;
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
    Label lbMsgFeedback;

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
        WindowLoader.setRoot("SellPassageUI", 570, 745);
    }

    public void reemitirPassagem(ActionEvent actionEvent) {
        try {
            String cpfCliente = lbCpfCliente.getText();
            reemitirPassagemUseCase.reemitirPassagem(cpfCliente);
        } catch (RuntimeException e) {
            exibirMensagemErro(e.getMessage());
        }
    }

    public void reagendarPassagem(ActionEvent actionEvent) {
        try {
            reagendarPassagensUseCase.reagendar(Long.parseLong(lbNumeroPassagem.getText()), LocalDate.parse(txtDataViagem.getText()));
            String dataViagem = txtDataViagem.getText();
            lbDataViagem.setText(dataViagem);
            alterarEstadosCampoData();
            String text = "Mensagem reagendada com sucesso";
            exibirMensagemSucesso(text);

        } catch (RuntimeException e) {
            exibirMensagemErro(e.getMessage());
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

            String text = "Mensagem devolvida com sucesso";
            exibirMensagemSucesso(text);
        } catch (RuntimeException e) {
            exibirMensagemErro(e.getMessage());
        }
    }

    public void consultarPassagem(ActionEvent actionEvent) {
        try{
            Long numPassagem = Long.parseLong(txtConsultarPassagem.getText());
            passagemConsultada = consultarPassagemVendidaUseCase.consultarPassagem(numPassagem);
            restaurarEstados();
            inserirTextoDosCampos();
            areaCamposPassagem.setVisible(true);
            habilitarBotoes(true);
            
        }catch (RuntimeException e){
            exibirMensagemErro(e.getMessage());
        }

    }

    private void inserirTextoDosCampos() {
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

    private void restaurarEstados(){
        areaCamposPassagem.setVisible(true);
        habilitarBotoes(false);
        lbMsgFeedback.setVisible(false);
        btnReagendar.setVisible(false);
    }

    private void exibirMensagem(String text, Color msgColor){
        lbMsgFeedback.setText(text);
        lbMsgFeedback.setTextFill(msgColor);
        lbMsgFeedback.setVisible(true);
    }

    private void exibirMensagemErro(String text){
        Color color = Color.web("#ff0000", 0.8);
        exibirMensagem(text, color);
    }

    private void exibirMensagemSucesso(String text){
        Color color = Color.web("#000000", 0.8);
        exibirMensagem(text, color);
    }
}
