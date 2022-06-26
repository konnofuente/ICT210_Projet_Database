package com.example.ict210_frontend;

import com.mysql.cj.xdevapi.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TimetableController implements Initializable {

    @FXML
    private Button return_btn;

    @FXML
    private Button refresh_btn;

    @FXML
    private Button add_period;

    @FXML
    private Label mon1;
    @FXML
    private Label mon2;
    @FXML
    private Label mon3;
    @FXML
    private Label mon4;
    @FXML
    private Label mon5;


    @FXML
    private Label tue1;
    @FXML
    private Label tue2;
    @FXML
    private Label tue3;
    @FXML
    private Label tue4;
    @FXML
    private Label tue5;
    @FXML

    private Label wed1;
    @FXML
    private Label wed2;
    @FXML
    private Label wed3;
    @FXML
    private Label wed4;
    @FXML
    private Label wed5;


    @FXML
    private Label thu1;
    @FXML
    private Label thu2;
    @FXML
    private Label thu3;
    @FXML
    private Label thu4;
    @FXML
    private Label thu5;


    @FXML
    private Label fri1;
    @FXML
    private Label fri2;
    @FXML
    private Label fri4;
    @FXML
    private Label fri3;
    @FXML
    private Label fri5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Return_day();
        //UpdateTimeTable()
        //LocalDate today= LocalDate.of(2022, 06, 30);
        java.sql.Date today=java.sql.Date.valueOf("2020-02-05");

        //DayOfWeek dayOfWeek = today.getDayOfWeek();

        //tue1.setText(Return_day(today));
        //TimeTableInfo(3,203,today);
        UpdateTimeTable();
        refresh_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UpdateTimeTable();
            }
        });

        add_period.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.small_window(actionEvent,"add_period.fxml","Period");
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
    }

    private void TimeTableInfo(Integer Sceance, Integer UE, Date day) {
        LocalDate day2=day.toLocalDate();
        System.out.println(Return_day(day2));
        switch (Return_day(day2)){

            case "mon":
                switch (Sceance){
                    case 1:
                        mon1.setText(String.valueOf(UE));
                        break;
                    case 2:
                        mon2.setText(String.valueOf(UE));
                        break;
                    case 3:
                        mon3.setText(String.valueOf(UE));
                        break;
                    case 4:
                        mon4.setText(String.valueOf(UE));
                        break;
                    case 5:
                        mon5.setText(String.valueOf(UE));
                        break;
                    default:
                        break;
                }
                break;
            case "tue":
                switch (Sceance){
                    case 1:
                        tue1.setText(String.valueOf(UE));
                        break;
                    case 2:
                        tue2.setText(String.valueOf(UE));
                        break;
                    case 3:
                        tue3.setText(String.valueOf(UE));
                        break;
                    case 4:
                        tue4.setText(String.valueOf(UE));
                        break;
                    case 5:
                        tue5.setText(String.valueOf(UE));
                        break;
                    default:
                        break;
                }
                break;
            case "wed":
                switch (Sceance){
                    case 1:
                        wed1.setText(String.valueOf(UE));
                        break;
                    case 2:
                        wed2.setText(String.valueOf(UE));
                        break;
                    case 3:
                        wed3.setText(String.valueOf(UE));
                        break;
                    case 4:
                        wed4.setText(String.valueOf(UE));
                        break;
                    case 5:
                        wed5.setText(String.valueOf(UE));
                        break;
                    default:
                        break;
                }
                break;
            case "thu":
                switch (Sceance){
                    case 1:
                        thu1.setText(String.valueOf(UE));
                        break;
                    case 2:
                        thu2.setText(String.valueOf(UE));
                        break;
                    case 3:
                        thu3.setText(String.valueOf(UE));
                        break;
                    case 4:
                        thu4.setText(String.valueOf(UE));
                        break;
                    case 5:
                        thu5.setText(String.valueOf(UE));
                        break;
                    default:
                        break;
                }
                break;
            case "fri":
                switch (Sceance){
                    case 1:
                        fri1.setText(String.valueOf(UE));
                        break;
                    case 2:
                        fri2.setText(String.valueOf(UE));
                        break;
                    case 3:
                        fri3.setText(String.valueOf(UE));
                        break;
                    case 4:
                        fri4.setText(String.valueOf(UE));
                        break;
                    case 5:
                        fri5.setText(String.valueOf(UE));
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void UpdateTimeTable() {
        try {
            String query=null;
            Connection connection =  DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetresaux", "root" , "1234" );;
            PreparedStatement preparedStatement =null;
            ResultSet resultSet =null;
            Client client =null;

            // connection = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/sanka", "root" , "" );

            query = "SELECT * FROM cours ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            //Date.valueOf(jour)
            while(resultSet.next()){
                TimeTableInfo(
                        resultSet.getInt("IDSEANCE"),
                        resultSet.getInt("IDUE"),
                        resultSet.getDate("JOUR"));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static String Return_day(LocalDate day) {
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        switch(dayOfWeek.getValue()) {
            case 1:
                return "mon";
            case 2:
                return "tue";
            case 3:
                return "wed";
            case 4:
                return "thu";
            case 5:
                return "fri";
            default:
                return "invalid";
        }
    }
}
