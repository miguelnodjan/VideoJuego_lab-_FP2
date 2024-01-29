package controller;

import Tablero.Arquero;
import Tablero.Caballero;
import Tablero.Espadachin;
import Tablero.InterfazSwing;
import Tablero.Lancero;
import Tablero.Soldado;
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
import java.sql.ResultSet;
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

public class GeneradorPartidaController implements Initializable {

    private File directory;
    private ArrayList<File> songs;
    private File[] files;
    private Media media;
    private MediaPlayer mediaPlayer;
    private String[] campos = { "Bosque", "Campo", "Montaña", "Desierto" };
    private String[] reinos = { "Inglaterra", "Francia", "Castilla-Aragon", "Moros", "SacroImperioRomano" };

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
    private Spinner[] spinnerPropios = { mySpinner1, mySpinner2, mySpinner3, mySpinner4 };
    private Spinner[] spinnerContrarios = { mySpinner5, mySpinner6, mySpinner7, mySpinner8 };

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
    public void iniciarJuego(ActionEvent event) {
        advertencia1.setText("");
        advertencia2.setText("");
        if (checkBoxAuto1.isSelected()) {
            enviarABaseDEDatos(choiceReino1.getValue(), choiceReino2.getValue(), choiceCampo.getValue());
        } else {
            if (choiceReino1.getValue().equals(choiceReino2.getValue())) {
                advertencia1.setText("No pueden ser del mismo Reino");
                advertencia2.setText("No pueden ser del mismo Reino");
                return;
            }
            if (mySpinner1.getValue() + mySpinner2.getValue() + mySpinner3.getValue() + mySpinner4.getValue() > 10) {
                advertencia1.setText("Sobrepasaste el límite de soldados por reino");
                return;
            }
            if (choiceCampo.getValue() == null || choiceReino1.getValue() == null || choiceReino2.getValue() == null) {
                advertencia1.setText("No pueden haber espacios en blanco");
                return;
            }
            enviaABaseDeDatos(choiceReino1.getValue(), choiceReino2.getValue(), choiceCampo.getValue(),
                    mySpinner1.getValue(), mySpinner5.getValue(), mySpinner4.getValue(), mySpinner7.getValue(),
                    mySpinner3.getValue(), mySpinner8.getValue(), mySpinner2.getValue(), mySpinner6.getValue());
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
    public void reproducirMusica(ActionEvent event) {
        if (checkBoxMusica.isSelected()) {
            mediaPlayer.play();
        } else {
            mediaPlayer.stop();
        }
    }

    @FXML
    public void ponerPantallacompleta(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        if (checkBoxPantallaCompleta.isSelected()) {
            stage.setFullScreen(true);
        } else {
            stage.setFullScreen(false);
        }
    }

    @FXML
    public void generarAutomaticamente1(ActionEvent event) {
        if (checkBoxAuto1.isSelected()) {
            desactivarSpinners();
        } else {
            activarSpinners();
        }
    }

    private void desactivarSpinners() {
        for (Spinner spinner : spinnerPropios) {
            spinner.setDisable(true);
        }
        for (Spinner spinner : spinnerContrarios) {
            spinner.setDisable(true);
        }
    }

    private void activarSpinners() {
        for (Spinner spinner : spinnerPropios) {
            spinner.setDisable(false);
        }
        for (Spinner spinner : spinnerContrarios) {
            spinner.setDisable(false);
        }
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

    public void enviarABaseDEDatos(String reino1, String reino2, String Campo) {
        try {
            // Establecer conexión con la base de datos
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "");

            // Crear la consulta SQL para insertar el nuevo usuario
            String query = "INSERT INTO partidas(nombreReino1, nombreReino2, campo, cantidadArqueros1, cantidadArqueros2, cantidadCaballeros1, cantidadCaballeros2, cantidadLanceros1, cantidadLanceros2, cantidadEspadachines1, cantidadEspadachines2) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, reino1);
                preparedStatement.setString(2, reino2);
                preparedStatement.setString(3, Campo);
                preparedStatement.setInt(4, (int) (Math.random() * 2 + 1));
                preparedStatement.setInt(5, (int) (Math.random() * 2 + 1));
                preparedStatement.setInt(6, (int) (Math.random() * 3 + 1));
                preparedStatement.setInt(7, (int) (Math.random() * 3 + 1));
                preparedStatement.setInt(8, (int) (Math.random() * 2 + 1));
                preparedStatement.setInt(9, (int) (Math.random() * 2 + 1));
                preparedStatement.setInt(10, (int) (Math.random() * 3 + 1));
                preparedStatement.setInt(11, (int) (Math.random() * 3 + 1));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
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

    public void enviaABaseDeDatos(String reino1, String reino2, String Campo, int cantidadArqueros1,
            int cantidadArqueros2, int cantidadCaballeros1, int cantidadCaballeros2, int cantidadLanceros1,
            int cantidadlanceros2, int cantidadEspadachines1, int cantidadEspadachines2) {
        try {
            // Establecer conexión con la base de datos
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "");

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
                    try {
                        // Establecer conexión con la base de datos
                        Connection connection1 = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                                "root", "");

                        
                        String query1 = "SELECT * FROM partidas ORDER BY id DESC LIMIT 1";

                        try (PreparedStatement preparedStatement1 = connection1.prepareStatement(query1)) {
                            ResultSet resultSet = preparedStatement1.executeQuery();

                            if (resultSet.next()) {
                                
                                String nombreReino1a = resultSet.getString("nombreReino1");
                                String nombreReino2a = resultSet.getString("nombreReino2");
                                String campo = resultSet.getString("campo");
                                int cantidadArqueros1a = resultSet.getInt("cantidadArqueros1");
                                int cantidadArqueros2a = resultSet.getInt("cantidadArqueros2");
                                int cantidadCaballeros1a = resultSet.getInt("cantidadCaballeros1");
                                int cantidadCaballeros2a = resultSet.getInt("cantidadCaballeros2");
                                int cantidadLanceros1a = resultSet.getInt("cantidadLanceros1");
                                int cantidadLanceros2a = resultSet.getInt("cantidadLanceros2");
                                int cantidadEspadachines1a = resultSet.getInt("cantidadEspadachines1");
                                int cantidadEspadachines2a = resultSet.getInt("cantidadEspadachines2");
                                
                                int cantidadSoldados1 = cantidadArqueros1a + cantidadCaballeros1a + cantidadEspadachines1a + cantidadLanceros1a;
                                int cantidadSoldados2 = cantidadArqueros2a + cantidadCaballeros2a + cantidadEspadachines2a + cantidadLanceros2a;

                                Soldado[] soldados1 = new Soldado[cantidadSoldados1];
                                Soldado[] soldados2 = new Soldado[cantidadSoldados2];

                                añadirSoldados("arquero", cantidadArqueros1a, soldados1, nombreReino1a);
                                añadirSoldados("arquero", cantidadArqueros2a, soldados2, nombreReino2a); // Aquí corrige el nombre de la variable
                                añadirSoldados("caballero", cantidadCaballeros1a, soldados1, nombreReino1a);
                                añadirSoldados("caballero", cantidadCaballeros2a, soldados2, nombreReino2a);
                                añadirSoldados("lancero", cantidadLanceros1a, soldados1, nombreReino1a);
                                añadirSoldados("lancero", cantidadLanceros2a, soldados2, nombreReino2a);
                                añadirSoldados("espadachin", cantidadEspadachines1a, soldados1, nombreReino1a);
                                añadirSoldados("espadachin", cantidadEspadachines2a, soldados2, nombreReino2a);

                                new InterfazSwing(campo, reino1, reino2, soldados1, soldados2);
                            } else {
                                System.out.println("No hay partidas registradas");
                            }
                        }

                        
                        connection1.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(InterfazRegistroController.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }

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
