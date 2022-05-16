package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPeriodController implements Initializable {
    @FXML
    private JFXButton register_btn;

    @FXML
    private TextField id_course;
    @FXML
    private TextField id_sceance;
    @FXML
    private TextField id_ue;
    @FXML
    private TextField id_salle;
    @FXML
    private DatePicker jour;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
