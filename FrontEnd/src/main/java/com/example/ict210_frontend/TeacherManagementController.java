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
import java.util.Date;
import java.util.ResourceBundle;

public class TeacherManagementController implements Initializable {
    @FXML
    private JFXButton search_btn;

    @FXML
    private JFXButton add_btn;

    @FXML
    private JFXButton refresh_btn;

    @FXML
    private JFXButton delete_btn;

    @FXML
    private JFXButton return_btn;

    @FXML
    private TextField tfsearch;

    @FXML private TableView<Teacher> TeacherTable;

    @FXML private TableColumn<Teacher, Integer> idCol;

    @FXML private TableColumn<Teacher, String> nameCol;

    @FXML private TableColumn<Teacher, String> sexCol;

    ObservableList<Teacher> teacherslist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable();

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

        refresh_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                refreshTable();
            }
        });

        add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.small_window(actionEvent,"add_teacher.fxml","MENU");
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
            teacherslist.clear();
            query = "select * from enseignant";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                teacherslist.add(new Teacher(
                        resultSet.getInt("IDENSEIGNANT"),
                        resultSet.getString("NOM_ENSEIGNANT"),
                        resultSet.getString("SEXE")
                ));
                TeacherTable.setItems(teacherslist);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        idCol.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.Teacher,Integer>("idCol"));
        nameCol.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.Teacher,String>("nameCol"));
        sexCol.setCellValueFactory(new PropertyValueFactory<com.example.ict210_frontend.Teacher,String>("sexCol"));

        //initialise listenner for filtered the list
        FilteredList<Teacher> filteredData =new FilteredList<>(teacherslist, b ->true);

        tfsearch.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(teacher -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(teacher.getIdCol().toString().indexOf(searchKeyword)> -1){
                    return true;//mean we found id

                }else if(teacher.getNameCol().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;//mean we found name
                }else if(teacher.getSexCol().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;//mean we found name
                }
                else
                    return false;//no match found
            });
        });

        SortedList<Teacher> sortData=new SortedList<>(filteredData);

        //bind sorted result with table
        sortData.comparatorProperty().bind(TeacherTable.comparatorProperty());

        //apply filtered and sorted data to Table view
        TeacherTable.setItems(sortData);
    }
}
