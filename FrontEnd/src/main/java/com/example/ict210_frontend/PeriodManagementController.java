package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.xdevapi.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Period;
import java.util.Date;
import java.util.ResourceBundle;

public class PeriodManagementController implements Initializable {
    @FXML
    private JFXButton search_btn;

    @FXML
    private JFXButton add_btn;

    @FXML
    private TextField tfsearch;


    @FXML
    private JFXButton refresh_btn;

    @FXML
    private JFXButton delete_btn;

    @FXML
    private JFXButton return_btn;

    @FXML private TableView<IPeriod> PeriodTable;

    @FXML private TableColumn<IPeriod, Integer> idPeriod;

    @FXML private TableColumn<IPeriod, Integer> idSeance;

    @FXML private TableColumn<IPeriod, Integer> idUe;

    @FXML private TableColumn<IPeriod, Integer> idSalle;

    @FXML private TableColumn<IPeriod, Date> jour;

    Date date=new Date();

    ObservableList<IPeriod> periodslist= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable(); // this function help us to refreash the table

        return_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"menu.fxml","MENU",900);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.small_window(actionEvent,"add_period.fxml","MENU");
            }
        });

        refresh_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                refreshTable();
            }
        });



    }

    private void refreshTable() {
        try {
            String query=null;
            Connection connection =  DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetresaux", "root" , "1234" );;
            PreparedStatement preparedStatement =null;
            ResultSet resultSet =null;
            Client client =null;

            // connection = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/sanka", "root" , "" );
            periodslist.clear();
            query = "SELECT * FROM cours ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                periodslist.add(new IPeriod(
                        resultSet.getInt("IDCOURS"),
                        resultSet.getInt("IDSEANCE"),
                        resultSet.getInt("IDUE"),
                        resultSet.getInt("ID_SALLE"),
                        resultSet.getDate("JOUR")
                ));
                PeriodTable.setItems(periodslist);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        idPeriod.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.IPeriod,Integer>("idPeriod"));
        idSeance.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.IPeriod,Integer>("idSeance"));
        idUe.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.IPeriod,Integer>("idUe"));
        idSalle.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.IPeriod,Integer>("idSalle"));
        jour.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.IPeriod,Date>("jour"));

        //initialise listenner for filtered the list
        FilteredList<IPeriod> filteredData =new FilteredList<>(periodslist, b ->true);

        tfsearch.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(period -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(period.getIdPeriod().toString().indexOf(searchKeyword)> -1){
                    return true;//mean we found id

                }else if(period.getIdSeance().toString().indexOf(searchKeyword) > -1){
                    return true;//mean we found name
                }else if(period.getIdUe().toString().indexOf(searchKeyword) > -1){
                    return true;//mean we found name
                }
                else if(period.getIdSalle().toString().indexOf(searchKeyword) > -1){
                    return true;//mean we found name
                }
                else
                    return false;//no match found
            });
        });

        SortedList<IPeriod> sortData=new SortedList<>(filteredData);

        //bind sorted result with table
        sortData.comparatorProperty().bind(PeriodTable.comparatorProperty());

        //apply filtered and sorted data to Table view
        PeriodTable.setItems(sortData);
    }

}
