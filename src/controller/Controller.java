package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.AlertDialogs;
import model.PrototypeFactoryMemento;
import model.TextCaretaker;
import model.TextMemento;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Controller implements Initializable {
    private TextCaretaker textCaretaker= new TextCaretaker();
    private TextMemento memento;
    private boolean actionClickUndoReundo=false;

    final Clipboard clipboard= Clipboard.getSystemClipboard();
    final ClipboardContent content=  new ClipboardContent();





    // botones
    @FXML
    Button btn_archivo;
    @FXML
    Button btn_abrirArchivo;
    @FXML
    Button btn_guardar;
    @FXML
    Button btn_guardarComo;
    @FXML
    Button btn_color;
    @FXML
    Button btn_anterior;
    @FXML
    Button btn_siguiente;
    @FXML
    Button btn_cortar;
    @FXML
    Button btn_copiar;
    @FXML
    Button btn_pegar;

    // Anchor Panel
    @FXML
    AnchorPane ventana;

    // TextArea
    @FXML
    TextArea textArea;


    @FXML
    private void handle_abrirArchivo(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo de texto");
        File file =fileChooser.showOpenDialog(ventana.getScene().getWindow());
        if(file!=null) {
            textArea.setText(String.valueOf(file.toURI().toURL()));
        }
    }


    @FXML
    private void handle_anteriorButton(ActionEvent event){
        actionClickUndoReundo=true;
        try {
            memento= (TextMemento) textCaretaker.getPreviousMemento();
            textArea.setText(memento.getText());
        }catch (Exception e){
            AlertDialogs alerta= new AlertDialogs();
            alerta.dialogoAlerta("No hay más hacia atrás");
        }

    }
    @FXML
    private void handle_siguienteButton(ActionEvent event){
        actionClickUndoReundo=true;
        try{
            memento= (TextMemento) textCaretaker.getNextMemento();
            textArea.setText(memento.getText());
        }catch (Exception e){
            // quiere decir que ya se no hoy mementos siguientes
        }

    }

    @FXML
    private void handle_copiarButton(ActionEvent event){
        String copyText = textArea.getSelectedText();
        content.putString(copyText);
        clipboard.setContent(content);
    }

    @FXML
    private void handle_pegarButton(ActionEvent event){
        int from = textArea.getCaretPosition();
        String textoActual= textArea.getText();
        String newText=textArea.getText(0,from)+content.getString()+textArea.getText(from,textoActual.length());
        textArea.setText(newText);
    }


    @FXML
    private void handle_cortarButton(ActionEvent event){
        content.clear();
        content.putString(textArea.getSelectedText());
        textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setWrapText(true);
        memento = new TextMemento("");
        PrototypeFactoryMemento.addPrototype(memento);
        textCaretaker.addMemento(memento);



        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ((event.getCode() == KeyCode.Z || event.getCode() == KeyCode.Y)
                        && event.isShortcutDown()) {
                    event.consume();
                }
            }
        });


        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldText, String newText) {
                if (!actionClickUndoReundo){
                    memento = (TextMemento) PrototypeFactoryMemento.getPrototype(1);
                    memento.setText(textArea.getText());
                    textCaretaker.addMemento(memento);
                    //System.out.println(newText);
                }
                actionClickUndoReundo=false;
                System.out.println(textCaretaker.getMementoLinkedList().size());
            }

        });


    }
}
