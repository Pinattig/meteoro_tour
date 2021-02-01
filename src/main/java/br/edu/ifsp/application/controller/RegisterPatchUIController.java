package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalTime;

import static br.edu.ifsp.application.main.Main.gerenciarTrechosUseCase;

public class RegisterPatchUIController {

    @FXML TextField txtNome;
    @FXML TextField txtCidadeOrigem;
    @FXML TextField txtCidadeDestino;
    @FXML TextField txtQuilometragem;
    @FXML TextField txtTempoDuracao;
    @FXML TextField txtValorTrecho;
    @FXML TextField txtTaxaEmbarque;
    @FXML TextField txtValorSeguro;

    @FXML Button btnCadastrar;

    @FXML Label lbError;

    public void cadastrarTrecho(ActionEvent actionEvent) {
        try{
            Trecho trecho = pegarValoresDosCampos();
            System.out.println(gerenciarTrechosUseCase.insert(trecho));
            lbError.setText("Trecho cadastrado com sucesso!");
        }catch(RuntimeException e){
            lbError.setText(e.getMessage());
        }
    }

    private Trecho pegarValoresDosCampos() {
        double km = Double.parseDouble(txtQuilometragem.getText());
        double embarque = Double.parseDouble(txtTaxaEmbarque.getText());
        double seguro = Double.parseDouble(txtValorSeguro.getText());
        double passagem = Double.parseDouble(txtValorTrecho.getText());
        String cidadeDestino = txtCidadeDestino.getText();
        String cidadeOrigem = txtCidadeOrigem.getText();
        String nome = txtNome.getText();
        LocalTime duracao = LocalTime.parse(txtTempoDuracao.getText());


        Trecho trecho = new Trecho(cidadeOrigem,cidadeDestino,km,duracao,passagem,embarque,seguro,nome);
        return trecho;

    }

    public void backToAction(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PatchUI", 410, 550);
    }
}
