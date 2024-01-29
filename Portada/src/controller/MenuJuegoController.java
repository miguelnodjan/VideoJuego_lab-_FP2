/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Tablero.Arquero;
import Tablero.Caballero;
import Tablero.Espadachin;
import Tablero.InterfazSwing;
import Tablero.Lancero;
import Tablero.Soldado;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.JOptionPane;

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
    public void continuarPartida(ActionEvent event){
        try {
            // Establecer conexión con la base de datos
            Connection connection1 = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "");


            String query1 = "SELECT * FROM partidas ORDER BY id DESC LIMIT 1";

            try (PreparedStatement preparedStatement1 = connection1.prepareStatement(query1)) {
                ResultSet resultSet = preparedStatement1.executeQuery();

                if (resultSet.next()) {

                    String nombreReino1 = resultSet.getString("nombreReino1");
                    String nombreReino2 = resultSet.getString("nombreReino2");
                    String campo = resultSet.getString("campo");
                    int cantidadArqueros1 = resultSet.getInt("cantidadArqueros1");
                    int cantidadArqueros2 = resultSet.getInt("cantidadArqueros2");
                    int cantidadCaballeros1 = resultSet.getInt("cantidadCaballeros1");
                    int cantidadCaballeros2 = resultSet.getInt("cantidadCaballeros2");
                    int cantidadLanceros1 = resultSet.getInt("cantidadLanceros1");
                    int cantidadLanceros2 = resultSet.getInt("cantidadLanceros2");
                    int cantidadEspadachines1 = resultSet.getInt("cantidadEspadachines1");
                    int cantidadEspadachines2 = resultSet.getInt("cantidadEspadachines2");
                    
                    int cantidadSoldados1 = cantidadArqueros1 + cantidadCaballeros1 + cantidadEspadachines1 + cantidadLanceros1;
                    int cantidadSoldados2 = cantidadArqueros2 + cantidadCaballeros2 + cantidadEspadachines2 + cantidadLanceros2;
                    Soldado[] soldados1 = new Soldado[cantidadSoldados1];
                    Soldado[] soldados2 = new Soldado[cantidadSoldados2];
                    añadirSoldados("arquero", cantidadArqueros1, soldados1, nombreReino1);
                    añadirSoldados("arquero", cantidadArqueros2, soldados2, nombreReino2);
                    añadirSoldados("caballero", cantidadCaballeros1, soldados1, nombreReino1);
                    añadirSoldados("caballero", cantidadCaballeros2, soldados2, nombreReino2);
                    añadirSoldados("lancero", cantidadLanceros1, soldados1, nombreReino1);
                    añadirSoldados("lancero", cantidadLanceros2, soldados2, nombreReino2);
                    añadirSoldados("espadachin", cantidadEspadachines1, soldados1, nombreReino1);
                    añadirSoldados("espadachin", cantidadEspadachines2, soldados2, nombreReino2);
                    mediaPlayer.stop();
                    media = new Media(songs.get(0).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();

                    new InterfazSwing(campo, nombreReino1, nombreReino2, soldados1, soldados2);
                    
                } else {
                    System.out.println("No hay partidas registradas");
                }
            }


            connection1.close();

        } catch (SQLException ex) {
            Logger.getLogger(InterfazRegistroController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    @FXML
    public void crearNuevaPartida(ActionEvent event){
        try {
            mediaPlayer.stop();
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
    public void añadirSoldados(String tipoSoldado, int cantidad, Soldado[] ejercito, String reino){
        int controlador = ejercito.length;
        for (int i = 0; i < cantidad; i++) {
            switch (tipoSoldado) {
            case "caballero":
                for (int j = 0; j < controlador; j++) {
                    if (ejercito[j] == null) {
                        ejercito[j] =new Caballero();
                        ejercito[j].setNombre(reino + " Caballero" + (j+1));
                        break;
                    }
                }
                break;
            case "arquero":
                for (int j = 0; j < controlador; j++) {
                    if (ejercito[j] == null) {
                        ejercito[j] =new Arquero();
                        ejercito[j].setNombre(reino + " Arquero" + (j+1));
                        break;
                    }
                }
                break;
            case "espadachin":
                for (int j = 0; j < controlador; j++) {
                    if (ejercito[j] == null) {
                        ejercito[j] =new Espadachin();
                        ejercito[j].setNombre(reino + " Espadachin" + (j+1));
                        break;
                    }
                }
                break;
            case "lancero":
                for (int j = 0; j < controlador; j++) {
                    if (ejercito[j] == null) {
                        ejercito[j] =new Lancero();
                        ejercito[j].setNombre(reino + " Lancero" + (j+1));
                        break;
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
        }
        
    }
}

