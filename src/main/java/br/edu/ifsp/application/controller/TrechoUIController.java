package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.usecases.trecho.GerenciarTrechosUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.UUID;

import static br.edu.ifsp.application.main.Main.gerenciarTrechosUseCase;

public class TrechoUIController {

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

    @FXML Pane areaCampoTrecho;

    private Trecho trechoConsultado;



    public void consultar(ActionEvent actionEvent) {
        String idTrecho = txtConsulta.getText();
        trechoConsultado = gerenciarTrechosUseCase.findOneByKey(UUID.fromString(idTrecho));
        System.out.println("trechoConsultado = " + trechoConsultado);

        setarTextDosCampos();
        areaCampoTrecho.setVisible(true);
        habilitarBotoes();

    }

    public void cadastrar(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("SellTrechoUI");
    }

    public void editar(ActionEvent actionEvent) {
    }

    public void remover(ActionEvent actionEvent) {
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
    }

    private void habilitarBotoes(){
        btnEditar.setVisible(true);
        btnRemover.setVisible(true);
    }
}
