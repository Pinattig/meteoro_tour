package br.edu.ifsp.application.controller;

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

import static br.edu.ifsp.application.main.Main.gerenciarOnibusUseCase;

public class BusUIController {

    @FXML
    private TableView<Onibus> busTable;
    @FXML
    private TableColumn<Onibus, String> clPlaca;
    @FXML
    private TableColumn<Onibus, String> clRenavam;

    @FXML
    private Label msgFeedback;

    @FXML
    private Button btnExcluir;

    private ObservableList<Onibus> tableData;

    @FXML
    private void initialize(){
        bindTableDataToTableView();
        bindColumnsTableToModelProperties();
        loadData();
    }

    public void cadastrarOnibus(ActionEvent actionEvent) {
    }

    public void delete(ActionEvent actionEvent) {
        Onibus selectedItem = busTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            gerenciarOnibusUseCase.delete(selectedItem);
            msgFeedback.setText("Onibus excluído com sucesso");
            msgFeedback.setVisible(true);
            loadData();
        }else{
            msgFeedback.setText("Selecione um onibus para excluir");
            msgFeedback.setVisible(true);
        }
    }

    private void loadData() {
        List<Onibus> allBus = gerenciarOnibusUseCase.getAll();
        tableData.clear();
        tableData.addAll(allBus);
    }

    private void bindColumnsTableToModelProperties() {
        clPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        clRenavam.setCellValueFactory(new PropertyValueFactory<>("renavam"));

        setEditableColumns();
    }

    private void bindTableDataToTableView() {
        tableData = FXCollections.observableArrayList();
        busTable.setItems(tableData);
    }

    private void setEditableColumns(){
        clPlaca.setCellFactory(TextFieldTableCell.forTableColumn());
        clPlaca.setOnEditCommit(e -> {
            Onibus onibus = e.getTableView().getItems().get(e.getTablePosition().getRow());
            onibus.setPlaca(e.getNewValue());
            gerenciarOnibusUseCase.update(onibus);
            msgFeedback.setText("Placa editada com sucesso");
            msgFeedback.setVisible(true);
        });

        clRenavam.setCellFactory(TextFieldTableCell.forTableColumn());
        clRenavam.setOnEditCommit(e -> {
            Onibus onibus = e.getTableView().getItems().get(e.getTablePosition().getRow());
            onibus.setRenavam(e.getNewValue());
            gerenciarOnibusUseCase.update(onibus);
            msgFeedback.setText("Renavam editado com sucesso");
            msgFeedback.setVisible(true);
        });

        busTable.setEditable(true);
    }

}
