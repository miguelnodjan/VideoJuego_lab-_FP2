package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

public class VentanaAjustesController {

    @FXML
    private Slider volumenSlider;

    private MediaPlayer mediaPlayer;

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

        // Configurar el valor inicial del slider con el volumen actual del MediaPlayer
        volumenSlider.setValue(mediaPlayer.getVolume() * 100);
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        // Obtener el valor del slider y ajustar el volumen del MediaPlayer
        double volumen = volumenSlider.getValue() / 100;
        mediaPlayer.setVolume(volumen);

        // Cerrar la ventana de ajustes
        Stage stage = (Stage) volumenSlider.getScene().getWindow();
        stage.close();
    }
}
