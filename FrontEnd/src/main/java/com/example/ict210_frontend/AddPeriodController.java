package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
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


        register_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (id_course.getText().trim().isEmpty() || id_sceance.getText().trim().isEmpty() || id_ue.getText().trim().isEmpty() || id_salle.getText().trim().isEmpty() ) {


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please Enter all information in other to register A Peroid");
                    alert.show();

                 }
                else {

                    try {
                        DBUtils.AddPeroid(actionEvent,Integer.parseInt(id_course.getText()),Integer.parseInt(id_sceance.getText()),Integer.parseInt(id_ue.getText()),Integer.parseInt(id_salle.getText()),jour.getValue());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

//                    id_course.clear();
//                    id_salle.clear();
//                    id_course.clear();
//                    id_ue.clear();
//                    id_sceance.clear();

                }
            }
        });

    }
}
