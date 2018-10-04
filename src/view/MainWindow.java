/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.GestionListaEnMemoria;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import model.ModTableCell;

import model.Monstruos;

/**
 *
 * @author idoia
 */
public class MainWindow extends Application {

    private final TableView<Monstruos> table = new TableView<>();

    final HBox hb = new HBox();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());


        stage.setTitle("Tabla de caracteristicas");
        stage.setWidth(700);
        stage.setHeight(550);
        final Label label = new Label("Monstruos");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        
        Callback<TableColumn<Monstruos, String>, TableCell<Monstruos, String>> comboBoxCellFactory
                = (TableColumn<Monstruos, String> param) -> new ModTableCell();

        TableColumn<Monstruos, String> firstNameCol = new TableColumn<>("Nombre");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.<Monstruos>forTableColumn());
        firstNameCol.setOnEditCommit((TableColumn.CellEditEvent<Monstruos, String> t) -> {
                    ((Monstruos) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setFirstName(t.getNewValue());
                });

        TableColumn<Monstruos, String> especieCol
                = new TableColumn<>("Especie");
        especieCol.setMinWidth(100);
        especieCol.setCellValueFactory(
                cellData -> new SimpleStringProperty
        (cellData.getValue().getEspecie()));
        especieCol.setCellFactory(comboBoxCellFactory);
        especieCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Monstruos, String> t) -> {
                    ((Monstruos) t.getTableView().getItems()
                            .get(t.getTablePosition().getRow()))
                            .setEspecie(t.getNewValue());
                });

        TableColumn<Monstruos, String> elementoCol = new TableColumn<>("Elemento");
        elementoCol.setMinWidth(150);
        elementoCol.setCellValueFactory(
                new PropertyValueFactory<>("elemento"));
        elementoCol.setCellFactory(TextFieldTableCell.<Monstruos>forTableColumn());
        elementoCol.setOnEditCommit((TableColumn.CellEditEvent<Monstruos, String> t) -> {
                    ((Monstruos) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setElemento(t.getNewValue());
                });
        TableColumn<Monstruos, String> WeaknessCol = new TableColumn<>("Debilidad");
        WeaknessCol.setMinWidth(150);
        WeaknessCol.setCellValueFactory(new PropertyValueFactory<>("Weakness"));
        WeaknessCol.setCellFactory(TextFieldTableCell.<Monstruos>forTableColumn());
        WeaknessCol.setOnEditCommit((TableColumn.CellEditEvent<Monstruos, String> t) -> {
                    ((Monstruos) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setWeakness(t.getNewValue());
                });
        
        TableColumn<Monstruos, String> endureCol = new TableColumn<>("Resistencia");
        endureCol.setMinWidth(150);
        endureCol.setCellValueFactory(new PropertyValueFactory<>("endure"));
        endureCol.setCellFactory(TextFieldTableCell.<Monstruos>forTableColumn());
        endureCol.setOnEditCommit((TableColumn.CellEditEvent<Monstruos, String> t) -> {
                    ((Monstruos) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setEndure(t.getNewValue());
                });

        table.setItems(FXCollections.observableList(GestionListaEnMemoria.cargarDatos()));
        table.getColumns().addAll(firstNameCol, especieCol, elementoCol, WeaknessCol, endureCol);
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("Nombre");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());

        final ComboBox addEspecie = new ComboBox(FXCollections.observableList(GestionListaEnMemoria.cargarDatosEspecie()));
        addEspecie.setMaxWidth(100);
        addEspecie.setPromptText("Especie");

        final TextField addElemento = new TextField();
        addElemento.setMaxWidth(elementoCol.getPrefWidth());
        addElemento.setPromptText("Elemento");

        final TextField addWeakness = new TextField();
        addWeakness.setMaxWidth(WeaknessCol.getPrefWidth());
        addWeakness.setPromptText("Debilidad");
        
        final TextField addEndure = new TextField();
        addEndure.setMaxWidth(endureCol.getPrefWidth());
        addEndure.setPromptText("Resistencia");

        final Button addButton = new Button("Añadir");
        addButton.setOnAction((ActionEvent e) -> {
            if ("".equals(addFirstName.getText()) || addEspecie.getSelectionModel().isEmpty() || addElemento.getText() == "" ||  addWeakness.getText() == ""|| addEndure.getText() == "") {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ezin da gehitu!");
                alert.setContentText("Ezin da daturik gorde taulan guztiak bete barik.");
                alert.showAndWait();
            } else {
            Monstruos p = new Monstruos(addFirstName.getText(), addEspecie.getSelectionModel().getSelectedItem().toString(), addElemento.getText(), addWeakness.getText(), addEndure.getText());
            table.getItems().add(p);
            addFirstName.clear();
            addEspecie.getSelectionModel().clearSelection();
            addElemento.clear();
            addWeakness.clear();
            addEndure.clear();
            }});

        final Button removeButton = new Button("Borrar");
        removeButton.setOnAction((ActionEvent e) -> {
            Monstruos mon = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(mon);
        });

        hb.getChildren().addAll(addFirstName, addEspecie, addElemento, addWeakness, addEndure, addButton, removeButton);
        hb.setSpacing(3);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event)-> {
            try { 
                PrintWriter pw = new PrintWriter("Monstruos.txt");
                for(int i = 0; i < table.getItems().size(); i++){
                    pw.println(table.getItems().get(i).getFirstName()+","
                            +table.getItems().get(i).getEspecie()+","
                            +table.getItems().get(i).getElemento()+","
                            +table.getItems().get(i).getWeakness()+","
                            +table.getItems().get(i).getEndure()
                    );
                    
                }
                pw.close();
            } catch (FileNotFoundException ex) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Ez da artxiboa aurkitu datuak gordetzeko.");
                error.showAndWait();
            }
            
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
