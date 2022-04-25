package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML private Button login_btn;

    @FXML private JFXButton signup_btn;

    @FXML private TextField name_txt;

    @FXML private TextField password_txt;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /**
                try {
                    DBUtils.changeover(actionEvent,"menu.fxml","MENU",900);
                } catch (IOException e) {
                    e.printStackTrace();
                }**/
                try {
                    DBUtils.login_admin(actionEvent,name_txt.getText(),password_txt.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        signup_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"create_admin.fxml","MENU",600);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
