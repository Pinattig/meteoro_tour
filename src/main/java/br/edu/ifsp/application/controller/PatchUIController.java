package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalTime;
import java.util.UUID;

import static br.edu.ifsp.application.main.Main.gerenciarTrechosUseCase;

public class PatchUIController {

    @FXML TextField txtConsulta;

    @FXML Button btnConsulta;
    @FXML Button btnCadastrar;
    @FXML Button btnEditar;
    @FXML Button btnRemover;

    @FXML Label lbNome;
    @FXML Label lbCidadeOrigem;
    @FXML Label lbCidadeDestino;
    @FXML Label lbQuilometragem;
    @FXML Label lbDuracao;
    @FXML Label lbValor;
    @FXML Label lbEmbarque;
    @FXML Label lbSeguro;
    @FXML Label lbMsgFeedback;

    @FXML Pane areaCampoTrecho;

    @FXML TextField txtEditarNome;
    @FXML TextField txtEditarOrigem;
    @FXML TextField txtEditarDestino;
    @FXML TextField txtEditarKm;
    @FXML TextField txtEditarTempo;
    @FXML TextField txtEditarValor;
    @FXML TextField txtEditarEmbarque;
    @FXML TextField txtEditarSeguro;

    @FXML Button btnAtualizar;
    @FXML Button btnCancelar;

    private Trecho trechoConsultado;

    private boolean edicao = false;
    private boolean modoAtualizar = false;


    public void consultar(ActionEvent actionEvent) {
        try{
            String nameTrecho = txtConsulta.getText();
            trechoConsultado = gerenciarTrechosUseCase.findOneByKey(UUID.fromString(nameTrecho));
            if(trechoConsultado != null)
                habilitarBotoes();
            areaCampoTrecho.setVisible(true);
            setarTextDosCampos();

        }catch (RuntimeException e){
            exibirMensagemErro(e.getMessage());
        }

    }

    public void cadastrar(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("RegisterPatchUI", 290, 630);
    }

    public void editar(ActionEvent actionEvent) {
        exibirCamposParaEditar();
        btnAtualizar.setVisible(true);
        txtConsulta.setDisable(true);
        setarCamposParaEditar();
        btnCancelar.setVisible(true);
    }



    public void remover(ActionEvent actionEvent){
        gerenciarTrechosUseCase.delete(trechoConsultado);
        limparCampos();

    }

    private void setarTextDosCampos(){
        lbNome.setText(trechoConsultado.getNome());
        lbCidadeDestino.setText(trechoConsultado.getCidadeDestino());
        lbCidadeOrigem.setText(trechoConsultado.getCidadeOrigem());
        lbDuracao.setText(String.valueOf(trechoConsultado.getTempoDuracao()));
        lbEmbarque.setText(String.valueOf(trechoConsultado.getTaxaEmbarque()));
        lbQuilometragem.setText(String.valueOf(trechoConsultado.getQuilometragem()));
        lbSeguro.setText(String.valueOf(trechoConsultado.getValorSeguro()));
        lbValor.setText(String.valueOf(trechoConsultado.getValorPassagem()));
        gerenciarTrechosUseCase.update(trechoConsultado);

    }

    private void habilitarBotoes(){
        btnEditar.setVisible(!btnEditar.isVisible());
        btnRemover.setVisible(!btnRemover.isVisible());
    }

    public void atualizar(ActionEvent actionEvent) {
        if(!verificarErrosNosCampos())
            exibirMensagemErro("Há campos com valores incorretos");
        else{
            try{
                System.out.println(trechoConsultado);
                System.out.println(gerenciarTrechosUseCase.update(trechoConsultado));
                SetarNovosValoresParaOTrecho();

                cancelar(actionEvent);
                setarTextDosCampos();
                exibirMensagem("Trecho editado com sucesso", Color.web("#000000", 0.8));
            }catch (RuntimeException e){
                exibirMensagemErro(e.getMessage());
            }

        }
    }



    private void SetarNovosValoresParaOTrecho() {
        trechoConsultado.setQuilometragem(Double.parseDouble(txtEditarKm.getText()));
        trechoConsultado.setCidadeDestino(txtEditarDestino.getText());
        trechoConsultado.setCidadeOrigem(txtEditarOrigem.getText());
        trechoConsultado.setNome(txtEditarNome.getText());
        trechoConsultado.setTaxaEmbarque(Double.parseDouble(txtEditarEmbarque.getText()));
        trechoConsultado.setTempoDuracao(LocalTime.parse(txtEditarTempo.getText()));
        trechoConsultado.setValorSeguro(Double.parseDouble(txtEditarSeguro.getText()));
        trechoConsultado.setValorPassagem(Double.parseDouble(txtEditarValor.getText()));
    }

    private void exibirCamposParaEditar() {
        txtEditarNome.setVisible(!txtEditarNome.isVisible());
        txtEditarOrigem.setVisible(!txtEditarOrigem.isVisible());
        txtEditarDestino.setVisible(!txtEditarDestino.isVisible());
        txtEditarKm.setVisible(!txtEditarKm.isVisible());
        txtEditarTempo.setVisible(!txtEditarTempo.isVisible());
        txtEditarValor.setVisible(!txtEditarValor.isVisible());
        txtEditarEmbarque.setVisible(!txtEditarEmbarque.isVisible());
        txtEditarSeguro.setVisible(!txtEditarSeguro.isVisible());
        habilitarBotoes();

    }

    public void cancelar(ActionEvent actionEvent) {
        btnCancelar.setVisible(false);
        btnAtualizar.setVisible(false);
        lbMsgFeedback.setText("");
        exibirCamposParaEditar();
        txtConsulta.setDisable(false);
        setarTextDosCampos();

    }

    private void setarCamposParaEditar(){
        txtEditarNome.setText(trechoConsultado.getNome());
        txtEditarOrigem.setText(trechoConsultado.getCidadeOrigem());
        txtEditarDestino.setText(trechoConsultado.getCidadeDestino());
        txtEditarKm.setText(String.valueOf(trechoConsultado.getQuilometragem()));
        txtEditarTempo.setText(String.valueOf(trechoConsultado.getTempoDuracao()));
        txtEditarValor.setText(String.valueOf(trechoConsultado.getValorPassagem()));
        txtEditarEmbarque.setText(String.valueOf(trechoConsultado.getTaxaEmbarque()));
        txtEditarSeguro.setText(String.valueOf(trechoConsultado.getValorSeguro()));

    }

    private void exibirMensagemErro(String text){
        Color color = Color.web("#ff0000", 0.8);
        exibirMensagem(text, color);
    }
    private void exibirMensagem(String text, Color msgColor){
        lbMsgFeedback.setText(text);
        lbMsgFeedback.setTextFill(msgColor);
        lbMsgFeedback.setVisible(true);
    }

    private boolean verificarErrosNosCampos() {
        return tentarConverterParaDouble(txtEditarKm.getText()) && tentarConverterParaDouble(txtEditarValor.getText())
                && tentarConverterParaDouble(txtEditarEmbarque.getText()) && tentarConverterParaDouble(txtEditarSeguro.getText());

    }

    private boolean tentarConverterParaDouble(String numero){
        try{
            Double.parseDouble(numero);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private void limparCampos(){
        habilitarBotoes();
        areaCampoTrecho.setVisible(false);
        txtConsulta.setText("");
    }


    public void backToAction(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("AdminMainUI", 269, 481);
    }
}
