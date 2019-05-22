package model;

import javafx.scene.control.Alert;

public class AlertDialogs {

    public void dialogoAlerta(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
