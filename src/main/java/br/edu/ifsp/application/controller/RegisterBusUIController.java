package br.edu.ifsp.application.controller;

import br.edu.ifsp.domain.entities.onibus.Onibus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static br.edu.ifsp.application.main.Main.gerenciarOnibusUseCase;

public class RegisterBusUIController {
    
    @FXML TextField txtPlaca;
    @FXML TextField txtRenavam;

    @FXML Label lbError;

    @FXML Button btnCadastrar;

    public void cadastrarOnibus(ActionEvent actionEvent) {
        String placa = txtPlaca.getText();
        String renavam = txtRenavam.getText();

        Onibus onibus = new Onibus();
        onibus.setPlaca(placa);
        onibus.setRenavam(renavam);

        try {
            gerenciarOnibusUseCase.insert(onibus);
        } catch (RuntimeException e) {
            lbError.setText(e.getMessage());
        }

    }

}
