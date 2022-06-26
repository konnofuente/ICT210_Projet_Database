package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
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
import java.util.ResourceBundle;

public class ClassManagementController implements Initializable {

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

    @FXML private TableView<IClass> ClassTable;

    @FXML private TableColumn<IClass, Integer> idCol;

    @FXML private TableColumn<IClass, String> nameCol;

    ObservableList<IClass> classlist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable();

        refresh_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refreshTable();
            }
        });

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
                DBUtils.small_window(actionEvent,"add_class.fxml","MENU");
            }
        });

    }

    private void refreshTable() {

        try {
            String query=null;
            Connection connection =  DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetresaux", "root" , "1234");;
            PreparedStatement preparedStatement =null;
            ResultSet resultSet =null;


            // connection = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/sanka", "root" , "" );
            classlist.clear();
            query = "select * from salle";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                classlist.add(new IClass(
                        resultSet.getInt("ID_SALLE"),
                        resultSet.getString("NOM_SALLE")
                        ));

                ClassTable.setItems(classlist);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        idCol.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.IClass,Integer>("idCol"));
        nameCol.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.IClass,String>("nameCol"));


        //initialise listenner for filtered the list
        FilteredList<IClass> filteredData =new FilteredList<>(classlist, b ->true);

        tfsearch.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(classes -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(classes.getIdCol().toString().indexOf(searchKeyword)> -1){
                    return true;//mean we found id

                }else if(classes.getNameCol().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;//mean we found name
                }
                else
                    return false;//no match found
            });
        });

        SortedList<IClass> sortData=new SortedList<>(filteredData);

        //bind sorted result with table
        sortData.comparatorProperty().bind(ClassTable.comparatorProperty());

        //apply filtered and sorted data to Table view
        ClassTable.setItems(sortData);
    }
    }

