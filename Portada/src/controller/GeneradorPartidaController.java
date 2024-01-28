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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GeneradorPartidaController implements Initializable{
    private File directory;
    private ArrayList<File> songs;
    private File[] files;
    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    private Spinner<Integer> mySpinner1;

    @FXML
    private Spinner<Integer> mySpinner2;    

    int currentValue;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        songs = new ArrayList<File>();
        initializeMedia();
        mediaPlayer.setOnEndOfMedia(() -> {
        mediaPlayer.seek(mediaPlayer.getStartTime()); 
        mediaPlayer.play(); 
        });
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        mySpinner1.setValueFactory(valueFactory);
        mySpinner2.setValueFactory(valueFactory);


    }
    @FXML
    private CheckBox checkBoxMusica;
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
    public void configurarSpinner(){
        
    }
}

