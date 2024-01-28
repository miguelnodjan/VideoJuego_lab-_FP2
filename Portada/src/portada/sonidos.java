/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package portada;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.applet.AudioClip;

/**
 *
 * @author migue
 */
public class sonidos {
    File fondo = new File("Kingdoms & Lords Soundtrack - Kingdoms.mp3");
    String sfondo = "file:///" + fondo.getAbsolutePath();
    MediaPlayer mediaPlayer;
    
    public void sonidoFondo(){
        sfondo = sfondo.replaceAll("\\ ", "/");
        Media musicFile = new Media(sfondo);
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.play();
    }
}
