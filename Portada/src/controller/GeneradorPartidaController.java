/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GeneradorPartidaController implements Initializable{
    private File directory;
    private ArrayList<File> songs;
    private File[] files;
    private Media media;
    private MediaPlayer mediaPlayer;
    private String[] campos= {"Bosque", "Campo", "Montaña", "Desierto"};
    private String[] reinos = {"Inglaterra", "Francia", "Castilla-Aragon", "Moros", "SacroImperioRomano"};
    
    @FXML
    private Label advertencia1;
    @FXML
    private Label advertencia2;
    @FXML
    private ChoiceBox<String> choiceReino2;
    @FXML
    private ChoiceBox<String> choiceReino1;
    @FXML
    private ChoiceBox<String> choiceCampo;
    @FXML
    private CheckBox checkBoxAuto1;
    @FXML
    private CheckBox checkBoxAuto2;
    @FXML
    private Spinner<Integer> mySpinner1;

    @FXML
    private Spinner<Integer> mySpinner2;
    @FXML
    private Spinner<Integer> mySpinner3;

    @FXML
    private Spinner<Integer> mySpinner4;
    @FXML
    private Spinner<Integer> mySpinner5;

    @FXML
    private Spinner<Integer> mySpinner6;
    @FXML
    private Spinner<Integer> mySpinner7;

    @FXML
    private Spinner<Integer> mySpinner8;

    int currentValue;
    private Spinner[] spinnerPropios = {mySpinner1, mySpinner2, mySpinner3, mySpinner4};
    private Spinner[] spinnerContrarios = {mySpinner5, mySpinner6, mySpinner7, mySpinner8};
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        songs = new ArrayList<File>();
        initializeMedia();
        mediaPlayer.setOnEndOfMedia(() -> {
        mediaPlayer.seek(mediaPlayer.getStartTime()); 
        mediaPlayer.play(); 
        });
        choiceCampo.getItems().addAll(campos);
        choiceReino1.getItems().addAll(reinos);
        choiceReino2.getItems().addAll(reinos);


    }
    @FXML
    private CheckBox checkBoxMusica;
    @FXML
    public void iniciarJuego(ActionEvent event){
        advertencia1.setText("");
        advertencia2.setText("");
        if (checkBoxAuto1.isSelected()) {
            int cantidadArqueros = (int)(Math.random()*2 +1);
            int cantidadCaballeros = (int)(Math.random()*3 +1);
            int cantidadEspadachines = (int)(Math.random()*3 +1);
            int cantidadLanceros = (int)(Math.random()*2 +1);
        }
        else{
            if (choiceReino1.getValue().equals(choiceReino2.getValue())) {
                advertencia1.setText("No pueden ser del mismo Reino");
                advertencia2.setText("No pueden ser del mismo Reino");
                return;
            }
            if (mySpinner1.getValue() +  mySpinner2.getValue() + mySpinner3.getValue()+ mySpinner4.getValue() > 10){
                advertencia1.setText("Sobrepasaste el límite de soldados por reino");
                return;
             }
            if (choiceCampo.getValue() == null) {
                advertencia1.setText("Selecciona un campo");
                return;
            }
            int cantidadArqueros = mySpinner1.getValue();
            int cantidadCaballeros = mySpinner4.getValue();
            int cantidadEspadachines = mySpinner2.getValue();
            int cantidadLanceros = mySpinner3.getValue();
        }
        if (checkBoxAuto2.isSelected()) {
            
        }
        else{
            if (choiceReino1.getValue().equals(choiceReino2.getValue())) {
                advertencia1.setText("No pueden ser del mismo Reino");
                advertencia2.setText("No pueden ser del mismo Reino");
                return;
            }
            if (mySpinner5.getValue() +  mySpinner6.getValue() + mySpinner7.getValue()+ mySpinner8.getValue() > 10){
                advertencia1.setText("Sobrepasaste el límite de soldados por reino");
                return;
             }
            if (choiceCampo.getValue() == null) {
                advertencia1.setText("Selecciona un campo");
                return;
            }
        }
    }
    @FXML
    private CheckBox checkBoxPantallaCompleta;
    private void initializeMedia() {
        // Configura el directorio y obtiene la lista de archivos
        directory = new File("src/Audios");
        files = directory.listFiles();

        if (files != null) {
            // Agrega archivos a la lista de canciones
            for (File file : files) {
                songs.add(file);
            }
        }

        // Inicializa la primera canción
        if (!songs.isEmpty()) {
            media = new Media(songs.get(3).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } else {
            // Manejar el caso cuando no hay canciones disponibles
            System.out.println("No se encontraron canciones en el directorio 'Audios'");
        }
    }
    @FXML
    public void reproducirMusica(ActionEvent event){
        if (checkBoxMusica.isSelected()) {
            mediaPlayer.play();
        }
        else{
            mediaPlayer.stop();
        }
    }
    @FXML
    public void ponerPantallacompleta(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        if (checkBoxPantallaCompleta.isSelected()) {
            
            stage.setFullScreen(true);
        }
        else{
            stage.setFullScreen(false);
        }
    }
    @FXML
    public void generarAutomaticamente1(ActionEvent event){
        if (checkBoxAuto1.isSelected()) {
            mySpinner1.setDisable(true);
            mySpinner2.setDisable(true);
            mySpinner3.setDisable(true);
            mySpinner4.setDisable(true);
        }
        else{
            mySpinner1.setDisable(false);
            mySpinner2.setDisable(false);
            mySpinner3.setDisable(false);
            mySpinner4.setDisable(false);
        }
       
    }
    @FXML
    public void generarAutomaticamente2(ActionEvent event){
        if (checkBoxAuto2.isSelected()) {
            mySpinner5.setDisable(true);
            mySpinner6.setDisable(true);
            mySpinner7.setDisable(true);
            mySpinner8.setDisable(true);
        }
        else{
             mySpinner5.setDisable(false);
            mySpinner6.setDisable(false);
            mySpinner7.setDisable(false);
            mySpinner8.setDisable(false);
        }
    }
    

}

