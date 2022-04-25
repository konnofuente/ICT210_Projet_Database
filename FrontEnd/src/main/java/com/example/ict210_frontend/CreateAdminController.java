package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateAdminController implements Initializable {

    @FXML private JFXButton signup_btn;

    @FXML private JFXButton return_btn;

    @FXML private TextField id_txt;

    @FXML private TextField name_txt;

    @FXML private TextField password_txt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        return_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"login.fxml","MENU",600);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        signup_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (id_txt.getText().trim().isEmpty() || password_txt.getText().trim().isEmpty()) {


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please enter the Matricule and password");
                    alert.show();

                } else {

                    try {
                        DBUtils.signup_admin(actionEvent,id_txt.getText(),name_txt.getText(),password_txt.getText());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    id_txt.clear();
                    name_txt.clear();
                    password_txt.clear();

                }
            }
        });

    }
}
