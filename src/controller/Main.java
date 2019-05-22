package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // localizacion del archivo fxml para la interface
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));

        primaryStage.setTitle("EditText Pro"); //titulo de la ventana de la aplicacion
        primaryStage.getIcons().add(new Image("/view/images/icon.png")); // icono de la ventana de la palicacion
        primaryStage.setScene(new Scene(root, 800, 550)); // tamanno de la ventana
        primaryStage.show(); // mostrar la ventana
    }

    public static void main(String[] args) {
        launch(args);
    }
}
