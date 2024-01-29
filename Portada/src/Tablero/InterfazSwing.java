package Tablero;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class InterfazSwing {
    private File directory;
    private ArrayList<File> songs;
    private File[] files;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Soldado[] ejercito1;
    private Soldado[] ejercito2;
    private String campo;
    private String reino1;
    private String reino2;
    private JFrame frame = new JFrame("Videojuego");
    private JButton boton = new JButton("Nuevo Juego");
    
    public InterfazSwing(String campo, String reino1, String reino2, Soldado[] ejercito1, Soldado[] ejercito2){
        songs = new ArrayList<File>();
        initializeMedia();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        });
        this.campo = campo;
        this.reino1 = reino1;
        this.reino2 = reino2;
        this.ejercito1 = ejercito1;
        this.ejercito2 = ejercito2;
        ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame ventana2 = new JFrame();
            ventana2.setSize(580,500);
            JLabel mapa = new JLabel("Registro correcto, disfruta del juego");
            JButton boton2 = new JButton("Continuar");
            boton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Ejercito.FilayColumna(ejercito1);
                    Ejercito.FilayColumna(ejercito1, ejercito2);
                    Mapa.SeleccionarMapa(ejercito1, reino1, campo);
                    Mapa.SeleccionarMapa(ejercito1, reino2, campo);
                    Tablero tabla = new Tablero(ejercito1, ejercito2);
                    tabla.setVisible(true);

                    JFrame ventana3 = new JFrame();
                    ventana3.setSize(400, 400);
                    ventana3.setVisible(true);

                    JButton mostrar1Button = new JButton("Soldados ejercito 1");
                    mostrar1Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Ejercito.mostrarSoldados(ejercito1);
                        }
                    });


                    JButton mostrar2Button = new JButton("Soldados ejercito 2");
                    mostrar2Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Ejercito.mostrarSoldados(ejercito2);
                        }
                    });



                    JButton resumenButton = new JButton("Resumen 1 ejercito");

                    resumenButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame resumenFrame = new JFrame("Resumen del Ejército");
                            JPanel panel = new JPanel();
                            Ejercito.MostrarResumen(reino1, ejercito1, panel);
                            JPanel panel2 = new JPanel();
                            Ejercito.SoldadoMayorVida(ejercito1, panel2);
                            JPanel mainPanel = new JPanel(new FlowLayout());
                            mainPanel.add(panel);
                            mainPanel.add(panel2);

                            resumenFrame.getContentPane().add(mainPanel);
                            resumenFrame.setSize(700, 200   );
                            resumenFrame.setVisible(true);
                        }
                    });


                    JButton resumen2Button = new JButton("Resumen 2 ejercito");
                    resumen2Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame resumen2Frame = new JFrame("Resumen del Ejército");
                            JPanel panel2 = new JPanel();
                            Ejercito.MostrarResumen(reino2, ejercito2, panel2);
                            JPanel panel3 = new JPanel();
                            Ejercito.SoldadoMayorVida(ejercito2, panel3);
                            JPanel mainPanel = new JPanel(new FlowLayout());
                            mainPanel.add(panel2);
                            mainPanel.add(panel3);

                            resumen2Frame.getContentPane().add(mainPanel);
                            resumen2Frame.setSize(700, 200);
                            resumen2Frame.setVisible(true);
                        }
                    });

                    mostrar1Button.setBounds(50, 50, 300, 30);
                    mostrar2Button.setBounds(50, 100, 300, 30);
                    resumenButton.setBounds(50, 150, 300, 30);
                    resumen2Button.setBounds(50, 200, 300, 30);


                    ventana3.getContentPane().setLayout(null);  
                    ventana3.getContentPane().add(mostrar1Button);
                    ventana3.getContentPane().add(resumen2Button);
                    ventana3.getContentPane().add(resumenButton);
                    ventana3.getContentPane().add(mostrar2Button);



                }
            });




            mapa.setBounds(50, 50, 300, 30);

            boton2.setBounds(250, 400, 150, 30);

            ventana2.setVisible(true);
            ventana2.getContentPane().setLayout(null);
            ventana2.getContentPane().add(mapa);
            ventana2.getContentPane().add(boton2);


        }
    };
    boton.setBounds(50, 50, 150, 30);

    boton.addActionListener(actionListener);

    frame.getContentPane().setLayout(null); 
    frame.getContentPane().add(boton);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setSize(300, 200);

    frame.setVisible(true);
    }
 
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
            media = new Media(songs.get(5).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } else {
            // Manejar el caso cuando no hay canciones disponibles
            System.out.println("No se encontraron canciones en el directorio 'Audios'");
        }
    }
}


