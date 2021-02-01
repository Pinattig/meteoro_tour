package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.linha.Linha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

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
            lbError.setText("Linha cadastrada com sucesso!");
        } catch (RuntimeException e) {
            lbError.setText(e.getMessage());
        }
    }

    public void backToAction(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("BusLineUI", 425, 545);
    }
}
