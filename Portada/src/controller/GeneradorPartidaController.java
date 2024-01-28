/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;

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
            enviarABaseDEDatos(choiceReino1.getValue(), choiceReino2.getValue(), choiceCampo.getValue());
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
            if (choiceCampo.getValue() == null || choiceReino1.getValue() == null || choiceReino2.getValue() == null) {
                advertencia1.setText("No pueden haber espacios en blanco");
                return;
            }
            enviaABaseDeDatos(choiceReino1.getValue(), choiceReino2.getValue(), choiceCampo.getValue(), mySpinner1.getValue(), mySpinner5.getValue(), mySpinner4.getValue(), mySpinner7.getValue(), mySpinner3.getValue(), mySpinner8.getValue(), mySpinner2.getValue(), mySpinner6.getValue());
            
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
            mySpinner5.setDisable(true);
            mySpinner6.setDisable(true);
            mySpinner7.setDisable(true);
            mySpinner8.setDisable(true);
        }
        else{
            mySpinner1.setDisable(false);
            mySpinner2.setDisable(false);
            mySpinner3.setDisable(false);
            mySpinner4.setDisable(false);
            mySpinner5.setDisable(false);
            mySpinner6.setDisable(false);
            mySpinner7.setDisable(false);
            mySpinner8.setDisable(false);
        }
       
    }
    
    public void enviarABaseDEDatos(String reino1, String reino2, String Campo){
        try {
                // Establecer conexión con la base de datos
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

                // Crear la consulta SQL para insertar el nuevo usuario
                String query = "INSERT INTO partidas(nombreReino1, nombreReino2, campo, cantidadArqueros1, cantidadArqueros2, cantidadCaballeros1, cantidadCaballeros2, cantidadLanceros1, cantidadLanceros2, cantidadEspadachines1, cantidadEspadachines2) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, reino1);
                    preparedStatement.setString(2, reino2);
                    preparedStatement.setString(3, Campo);
                    preparedStatement.setInt(4, (int)(Math.random()*2 +1));
                    preparedStatement.setInt(5, (int)(Math.random()*2 +1));
                    preparedStatement.setInt(6, (int)(Math.random()*3 +1));
                    preparedStatement.setInt(7, (int)(Math.random()*3 +1));
                    preparedStatement.setInt(8, (int)(Math.random()*2 +1));
                    preparedStatement.setInt(9, (int)(Math.random()*2 +1));
                    preparedStatement.setInt(10, (int)(Math.random()*3 +1));
                    preparedStatement.setInt(11, (int)(Math.random()*3 +1));
                    
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Registro correcto, disfruta del juego");
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                }

                // Cerrar la conexión
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(InterfazRegistroController.class.getName()).log(Level.SEVERE, null, ex);
                // Puedes agregar aquí la lógica para mostrar un mensaje de error
            }
    }
    public  void enviaABaseDeDatos(String reino1, String reino2, String Campo, int cantidadArqueros1, int cantidadArqueros2, int cantidadCaballeros1, int cantidadCaballeros2, int cantidadLanceros1, int cantidadlanceros2, int cantidadEspadachines1, int cantidadEspadachines2){
        try {
                // Establecer conexión con la base de datos
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

                // Crear la consulta SQL para insertar el nuevo usuario
                String query = "INSERT INTO partidas(nombreReino1, nombreReino2, campo, cantidadArqueros1, cantidadArqueros2, cantidadCaballeros1, cantidadCaballeros2, cantidadLanceros1, cantidadLanceros2, cantidadEspadachines1, cantidadEspadachines2) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, reino1);
                    preparedStatement.setString(2, reino2);
                    preparedStatement.setString(3, Campo);
                    preparedStatement.setInt(4, cantidadArqueros1);
                    preparedStatement.setInt(5, cantidadArqueros2);
                    preparedStatement.setInt(6, cantidadCaballeros1);
                    preparedStatement.setInt(7, cantidadCaballeros2);
                    preparedStatement.setInt(8, cantidadLanceros1);
                    preparedStatement.setInt(9, cantidadlanceros2);
                    preparedStatement.setInt(10, cantidadEspadachines1);
                    preparedStatement.setInt(11, cantidadEspadachines2);
                    
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Registro correcto, disfruta del juego");
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }
                }

                // Cerrar la conexión
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(InterfazRegistroController.class.getName()).log(Level.SEVERE, null, ex);
                // Puedes agregar aquí la lógica para mostrar un mensaje de error
            }
    }

}

