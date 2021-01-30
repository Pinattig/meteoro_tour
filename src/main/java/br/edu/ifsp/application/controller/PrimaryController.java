package br.edu.ifsp.application.controller;

import java.io.IOException;

import br.edu.ifsp.application.view.WindowLoader;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        WindowLoader.setRoot("secondary");
    }
}
