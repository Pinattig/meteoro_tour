package br.edu.ifsp.application.controller;

import java.io.IOException;

import br.edu.ifsp.application.view.WindowLoader;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        WindowLoader.setRoot("primary");
    }
}