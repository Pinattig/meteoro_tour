package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.linha.Linha;
import br.edu.ifsp.domain.entities.onibus.Onibus;
import br.edu.ifsp.domain.entities.trecho.Trecho;
import br.edu.ifsp.domain.entities.trecho.TrechoLinha;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static br.edu.ifsp.application.main.Main.*;

public class BusLineUIController {

    @FXML
    private Pane areaCamposLinha;

    @FXML private TextField txtConsultarLinha;
    @FXML private TextField txtTrechoId;
    @FXML private Label lbNomeLinha;


    @FXML private Button btnRemoverLinha;
    @FXML private Button addTrecho;

    @FXML
    private TableView<TrechoLinha> trechosLinhaTable;
    @FXML
    private TableColumn<TrechoLinha, String> clId;
    @FXML
    private TableColumn<TrechoLinha, String> clNome;
    @FXML
    private TableColumn<TrechoLinha, Integer> clIndex;
    @FXML
    private TableColumn<TrechoLinha, LocalTime> clHorario;


    private ObservableList<TrechoLinha> tableData;
    private Linha searchedLinha;

    @FXML
    private void initialize(){
        bindTableDataToTableView();
        bindColumnsTableToModelProperties();
    }

    public void consultarLinha(ActionEvent actionEvent) {
        searchedLinha = gerenciarLinhaUseCase.getOne(Long.parseLong(txtConsultarLinha.getText())).get();
        if(searchedLinha != null){
            lbNomeLinha.setText(searchedLinha.getNome());
            loadData(searchedLinha);
            handleSearchFilds();
        }
    }

    public void cadastrarLinha(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("RegisterBusLineUI", 160, 344);
    }

    public void adicionarTrechoLinha(ActionEvent actionEvent) {
        String trechoId = txtTrechoId.getText();
        System.out.println(UUID.fromString(trechoId));
        Trecho trecho = gerenciarTrechosUseCase.findOneByKey(UUID.fromString(trechoId));
        TrechoLinha trechoLinha = new TrechoLinha();
        trechoLinha.setTrecho(trecho);
        trechoLinha.setLinha(searchedLinha);
        trechoLinha.setOrdem(0);
        tableData.add(trechoLinha);
    }

    public void removerLinha(ActionEvent actionEvent) {
    }

    private void setEditableColumns(){
        clIndex.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        clIndex.setOnEditCommit(e -> {
            TrechoLinha trechoLinha = e.getTableView().getItems().get(e.getTablePosition().getRow());
            trechoLinha.setOrdem(Integer.valueOf(e.getNewValue()));
            gerenciarLinhaUseCase.addTrechoLinha(trechoLinha);
            //msgFeedback.setText("Placa editada com sucesso");
            //msgFeedback.setVisible(true);
        });

        clHorario.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        clHorario.setOnEditCommit(e -> {
            TrechoLinha trechoLinha = e.getTableView().getItems().get(e.getTablePosition().getRow());

            trechoLinha.setHorarioSaida(e.getNewValue());
            gerenciarLinhaUseCase.addTrechoLinha(trechoLinha);


            //msgFeedback.setText("Placa editada com sucesso");
            //msgFeedback.setVisible(true);
        });

        trechosLinhaTable.setEditable(true);
    }

    private void loadData(Linha linha) {
        List<TrechoLinha> trechosLinha = gerenciarLinhaUseCase.getTrechosLinhaByKey(linha.getId());
        tableData.clear();
        tableData.addAll(trechosLinha);
    }

    private void bindColumnsTableToModelProperties() {
        clId.setCellValueFactory(new PropertyValueFactory<>("trechoId"));
        clNome.setCellValueFactory(new PropertyValueFactory<>("trechoName"));
        clIndex.setCellValueFactory(new PropertyValueFactory<>("ordem"));
        clHorario.setCellValueFactory(new PropertyValueFactory<>("horarioSaida"));

        setEditableColumns();

    }

    private void bindTableDataToTableView() {
        tableData = FXCollections.observableArrayList();
        trechosLinhaTable.setItems(tableData);
    }

    private void handleSearchFilds(){
        areaCamposLinha.setVisible(!areaCamposLinha.isVisible());
        trechosLinhaTable.setVisible(!trechosLinhaTable.isVisible());
        btnRemoverLinha.setVisible(!btnRemoverLinha.isVisible());
        addTrecho.setVisible(!addTrecho.isVisible());
        txtTrechoId.setVisible(!txtTrechoId.isVisible());
    }
}
