package br.edu.ifsp.application.controller;

import br.edu.ifsp.domain.entities.linha.Linha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import static br.edu.ifsp.application.main.Main.gerenciarLinhaUseCase;

public class RegisterBusLineUIController {

    @FXML TextField txtNome;

    @FXML Label lbError;

    @FXML Button btnCadastrar;

    public void cadastrarLinha(ActionEvent actionEvent) {
        String nome = txtNome.getText();

        Linha linha = new Linha();
        linha.setNome(nome);

        try {
            gerenciarLinhaUseCase.insert(linha);
        } catch (RuntimeException e) {
            lbError.setText(e.getMessage());
        }
    }
}
