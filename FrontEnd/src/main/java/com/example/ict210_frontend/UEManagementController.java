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

public class UEManagementController implements Initializable {

    @FXML
    private JFXButton search_btn;

    @FXML
    private JFXButton add_btn;

    @FXML
    private JFXButton refresh_btn;

    @FXML
    private TextField tfsearch;

    @FXML
    private JFXButton delete_btn;

    @FXML
    private JFXButton return_btn;

    @FXML private TableView<Ue> CourseTable;

    @FXML private TableColumn<Ue, Integer> idCol;

    @FXML private TableColumn<Ue, String> nameCol;

    ObservableList<Ue> courselist= FXCollections.observableArrayList();
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
                DBUtils.small_window(actionEvent,"add_course.fxml","MENU");
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
            courselist.clear();
            query = "select * from ue";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                courselist.add(new Ue(
                        resultSet.getInt("IDUE"),
                        resultSet.getString("NOM_UE")
                ));

                CourseTable.setItems(courselist);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        idCol.setCellValueFactory(new PropertyValueFactory<Ue,Integer>("idCol"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Ue,String>("nameCol"));


        //initialise listenner for filtered the list
        FilteredList<Ue> filteredData =new FilteredList<>(courselist, b ->true);


        tfsearch.textProperty().addListener((observable, oldValue, newValue)->{
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

        SortedList<Ue> sortData=new SortedList<>(filteredData);

        //bind sorted result with table
        sortData.comparatorProperty().bind(CourseTable.comparatorProperty());

        //apply filtered and sorted data to Table view
        CourseTable.setItems(sortData);
    }

}
