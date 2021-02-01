package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.gerenciarOnibusUseCase;

public class RegisterBusUIController {
    
    @FXML TextField txtPlaca;
    @FXML TextField txtRenavam;
    @FXML
    Label lbMsgFeedback;
    @FXML Button btnCadastrar;


    public void cadastrarOnibus(ActionEvent actionEvent) {
        try{
            Onibus onibus = new Onibus(txtRenavam.getText(), txtPlaca.getText());
            gerenciarOnibusUseCase.insert(onibus);
            lbMsgFeedback.setText("Onibus cadastrado com sucesso!");
            lbMsgFeedback.setVisible(true);
        }catch (RuntimeException e){
            lbMsgFeedback.setText(e.getMessage());
            lbMsgFeedback.setVisible(true);
        }

    }

    public void backToAction(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("BusUI", 330, 640);
    }
}
