package com.example.ict210_frontend;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddClassController implements Initializable {
    @FXML
    private JFXButton register_btn;

    @FXML private TextField id_txt;

    @FXML private TextField name_txt;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        register_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (id_txt.getText().trim().isEmpty() || name_txt.getText().trim().isEmpty()) {


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Please enter CLASS's ID and NAME");
                    alert.show();

                } else {
                    try {
                        DBUtils.AddTable2Column(actionEvent,Integer.parseInt(id_txt.getText()),name_txt.getText(),"salle","ID_SALLE","NOM_SALLE");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    id_txt.clear();
                    name_txt.clear();


                }
            }
        });
    }
}
