module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.ifsp.application.view to javafx.fxml;
    opens br.edu.ifsp.application.controller to javafx.fxml;
    opens br.edu.ifsp.domain.entities.onibus to javafx.fxml;
    opens br.edu.ifsp.domain.entities.funcionario to javafx.fxml;
    opens br.edu.ifsp.domain.entities.linha to javafx.fxml;
    opens br.edu.ifsp.domain.entities.trecho to javafx.fxml;
    exports br.edu.ifsp.application.view;
    exports br.edu.ifsp.application.controller;
    exports br.edu.ifsp.domain.entities.onibus;
    exports br.edu.ifsp.domain.entities.funcionario;
    exports br.edu.ifsp.domain.entities.linha;
    exports br.edu.ifsp.domain.entities.trecho;
}