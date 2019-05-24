package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.IndexRange;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.model.Paragraph;
import org.fxmisc.richtext.model.StyledDocument;


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
    ColorPicker colorPicker;
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
    InlineCssTextArea textArea;







    @FXML
    private void handle_abrirArchivo(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo de texto");
        File file =fileChooser.showOpenDialog(ventana.getScene().getWindow());
        if(file!=null) {
        }


    }


    @FXML
    private void handle_anteriorButton(ActionEvent event){
        actionClickUndoReundo=true;
        btn_siguiente.setDisable(false);

        try {
            memento= (TextMemento) textCaretaker.getPreviousMemento();
            textArea.replaceText(memento.getText());
        }catch (Exception e){
            btn_anterior.setDisable(true);
            System.out.println(e);
        }

    }
    @FXML
    private void handle_siguienteButton(ActionEvent event){
        actionClickUndoReundo=true;
        btn_anterior.setDisable(false);
        try{
            memento= (TextMemento) textCaretaker.getNextMemento();
            textArea.replaceText(memento.getText());
        }catch (Exception e){
            btn_siguiente.setDisable(true);

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
        textArea.replaceText(newText);
    }


    @FXML
    private void handle_cortarButton(ActionEvent event){
        content.clear();
        content.putString(textArea.getSelectedText());
        textArea.replaceText(textArea.getText().replace(textArea.getSelectedText(),""));

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setWrapText(true);
        memento = new TextMemento("");
        PrototypeFactoryMemento.addPrototype(memento);
        textCaretaker.addMemento(memento);


        colorPicker.setValue(Color.color(0,0,0));

        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                IndexRange selection = textArea.getSelection();
                textArea.setStyle(selection.getStart(),selection.getEnd(),"-fx-fill:#" +
                        Integer.toHexString(colorPicker.getValue().hashCode()));
            }

        });


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
                    crearMemento(newText);
                }

            }

        });

    }


    private void crearMemento(String text){
        memento= (TextMemento) PrototypeFactoryMemento.getPrototype(1).deepClone();
        memento.setText(text);
        textCaretaker.addMemento(memento);
    }


}
