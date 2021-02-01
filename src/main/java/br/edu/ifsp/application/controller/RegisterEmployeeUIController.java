package br.edu.ifsp.application.controller;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static br.edu.ifsp.application.main.Main.gerenciarFuncionarioUseCase;
import static br.edu.ifsp.application.main.Main.gerenciarOnibusUseCase;

public class RegisterEmployeeUIController {

    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML TextField txtRg;
    @FXML TextField txtCargo;

    @FXML Label lbError;

    @FXML Button btnCadastrar;

    public void cadastrarFuncionario(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String rg = txtRg.getText();
        String cargo = txtCargo.getText();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setRg(rg);
        funcionario.setCargo(cargo);

        try {
            gerenciarFuncionarioUseCase.insert(funcionario);
            System.out.println(gerenciarFuncionarioUseCase.getAll());
        } catch (RuntimeException e) {
            lbError.setText(e.getMessage());
        }
    }
}
