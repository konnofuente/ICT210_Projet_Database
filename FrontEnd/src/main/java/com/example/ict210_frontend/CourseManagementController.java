package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseManagementController implements Initializable {

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                DBUtils.small_window(actionEvent,"add_course.fxml","MENU");
            }
        });

    }
}
