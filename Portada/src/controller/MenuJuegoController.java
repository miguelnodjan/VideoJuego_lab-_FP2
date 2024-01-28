/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class MenuJuegoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private File directory;
    private ArrayList<File> songs;
    private File[] files;
    private Media media;
    private MediaPlayer mediaPlayer;
    private String user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        songs = new ArrayList<File>();
        initializeMedia();
        mediaPlayer.setOnEndOfMedia(() -> {
        mediaPlayer.seek(mediaPlayer.getStartTime()); 
        mediaPlayer.play(); 
    });
    }
    @FXML
    private CheckBox checkBoxPantallaCompleta;
    @FXML
    private CheckBox checkBoxMusica;
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
            media = new Media(songs.get(2).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } else {
            // Manejar el caso cuando no hay canciones disponibles
            System.out.println("No se encontraron canciones en el directorio 'Audios'");
        }
    }
    @FXML
    public void crearNuevaPartida(ActionEvent event){
        try {
            loadStage("/view/generadorPartida.fxml", event);
        } catch (IOException ex) {
            Logger.getLogger(interfazController.class.getName()).log(Level.SEVERE, null, ex);
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
    public void reproducirMusica(ActionEvent event){
        if (checkBoxMusica.isSelected()) {
            mediaPlayer.play();
        }
        else{
            mediaPlayer.stop();
        }
    }
    @FXML 
    public void salirJuego(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();

        
        stage.close();
    }
            
    @FXML
    public void playMedia(ActionEvent event) {
        // Inicia la reproducción
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
    

    public void setUser(String user) {
        this.user = user;
    }
    private void loadStage(String url, Event event) throws IOException {
        Object eventSource = event.getSource();
        Node sourceAsNode = (Node) eventSource;
        Scene oldScene = sourceAsNode.getScene();
        Window window = oldScene.getWindow();
        Stage stage = (Stage) window;
        stage.hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
}

