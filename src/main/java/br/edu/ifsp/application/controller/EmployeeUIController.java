package br.edu.ifsp.application.controller;

import br.edu.ifsp.domain.entities.funcionario.Funcionario;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.util.List;

import static br.edu.ifsp.application.main.Main.gerenciarFuncionarioUseCase;

public class EmployeeUIController {

    @FXML
    private TableView<Funcionario> employeeTable;

    @FXML
    private TableColumn<Funcionario, String> clName;
    @FXML
    private TableColumn<Funcionario, String> clRG;
    @FXML
    private TableColumn<Funcionario, String> clCPF;
    @FXML
    private TableColumn<Funcionario, String> clPosition;

    @FXML
    private Label msgFeedback;

    private ObservableList<Funcionario> tableData;

    @FXML
    private void initialize(){
        bindTableDataToTableView();
        bindColumnsTableToModelProperties();
        loadData();
    }

    public void cadastrarFuncionario(ActionEvent actionEvent) {
    }

    public void removerFuncionario(ActionEvent actionEvent) {
        Funcionario selectedItem = employeeTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            gerenciarFuncionarioUseCase.delete(selectedItem);
            msgFeedback.setText("Onibus excluído com sucesso");
            msgFeedback.setVisible(true);
            loadData();
        }else{
            msgFeedback.setText("Selecione um onibus para excluir");
            msgFeedback.setVisible(true);
        }
    }


    private void loadData() {
        List<Funcionario> funcionarios = gerenciarFuncionarioUseCase.getAll();
        tableData.clear();
        tableData.addAll(funcionarios);
    }

    private void bindColumnsTableToModelProperties() {
        clName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        clCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        clPosition.setCellValueFactory(new PropertyValueFactory<>("cargo"));

        setEditableColumns();
    }

    private void setEditableColumns() {
        clName.setCellFactory(TextFieldTableCell.forTableColumn());
        clRG.setCellFactory(TextFieldTableCell.forTableColumn());
        clCPF.setCellFactory(TextFieldTableCell.forTableColumn());
        clPosition.setCellFactory(TextFieldTableCell.forTableColumn());

        clName.setOnEditCommit(e -> {
            Funcionario funcionario = e.getTableView().getItems().get(e.getTablePosition().getRow());
            funcionario.setNome(e.getNewValue());
            gerenciarFuncionarioUseCase.edit(funcionario);
            msgFeedback.setText("Nome editado com sucesso");
            msgFeedback.setVisible(true);
        });

        clRG.setOnEditCommit(e -> {
            Funcionario funcionario = e.getTableView().getItems().get(e.getTablePosition().getRow());
            funcionario.setRg(e.getNewValue());
            gerenciarFuncionarioUseCase.edit(funcionario);
            msgFeedback.setText("RG editado com sucesso");
            msgFeedback.setVisible(true);
        });

        clCPF.setOnEditCommit(e -> {
            Funcionario funcionario = e.getTableView().getItems().get(e.getTablePosition().getRow());
            funcionario.setCpf(e.getNewValue());
            gerenciarFuncionarioUseCase.edit(funcionario);
            msgFeedback.setText("CPF editado com sucesso");
            msgFeedback.setVisible(true);
        });

        clPosition.setOnEditCommit(e -> {
            Funcionario funcionario = e.getTableView().getItems().get(e.getTablePosition().getRow());
            funcionario.setCargo(e.getNewValue());
            gerenciarFuncionarioUseCase.edit(funcionario);
            msgFeedback.setText("Cargo editado com sucesso");
            msgFeedback.setVisible(true);
        });

        employeeTable.setEditable(true);
    }

    private void bindTableDataToTableView() {
        tableData = FXCollections.observableArrayList();
        employeeTable.setItems(tableData);
    }
}
