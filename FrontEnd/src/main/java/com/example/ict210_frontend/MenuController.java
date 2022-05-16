package com.example.ict210_frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button schedule_btn;
    @FXML
    private Button period_btn;

    @FXML private Button teacher_btn;

    @FXML private Button class_btn;

    @FXML private Button course_btn;

    @FXML private Button logout_btn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        teacher_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"teacherManagement.fxml","TEACHER",900);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        class_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"classManagement.fxml","CLASS MANAGEMENT",900);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        course_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"courseManagement.fxml","COURSE",900);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        period_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"periodManagement.fxml","PERIOS MANAGEMENT",900);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeover(actionEvent,"login.fxml","COURSE",600);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
