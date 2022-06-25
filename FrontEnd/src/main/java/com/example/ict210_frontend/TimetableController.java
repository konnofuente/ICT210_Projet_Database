package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimetableController implements Initializable {

    @FXML
    private Button return_btn;

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
}
