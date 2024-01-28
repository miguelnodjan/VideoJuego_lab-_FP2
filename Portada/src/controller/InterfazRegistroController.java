/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class InterfazRegistroController implements Initializable {
    
    @FXML
    private TextField usuarioName;
    @FXML
    private Label labelAuxiliar;
    @FXML
    private PasswordField passwordName;

    @FXML
    private PasswordField passwordName2;

    @FXML
    private void registrarse(ActionEvent event) {
        labelAuxiliar.setText("");
        String usuario = usuarioName.getText();
        String password = passwordName.getText();
        String passwordRepeat = passwordName2.getText();
        
        if (usuario.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()) {
            labelAuxiliar.setText("Por favor, complete todos los campos.");
            
            return;
        }

        if (usuario.contains(" ") || password.contains(" ") || password.length() > 8 || passwordRepeat.contains(" ") || passwordRepeat.length() > 8) {
            labelAuxiliar.setText("Nombre sin espacios y contraseña de hasta 8 caracteres, sin espacios.");
            
            return;
        }
        
        if (password.equals(passwordRepeat)) {
            // Los passwords coinciden, proceder con el registro
            try {
                // Establecer conexión con la base de datos
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

                // Crear la consulta SQL para insertar el nuevo usuario
                String query = "INSERT INTO " + "usuarios" + " (user, pass) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, usuario);
                    preparedStatement.setString(2, password);

                    // Ejecutar la consulta
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Datos correctos, bienvenido a CrusadeOFKingDoms");
                        try {
                           loadStage("/view/menuJuego.fxml", event);
                       } catch (IOException ex) {
                           Logger.getLogger(interfazController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    } else {
                        labelAuxiliar.setText("Error al registrar");
                    }
                }

                // Cerrar la conexión
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(InterfazRegistroController.class.getName()).log(Level.SEVERE, null, ex);
                // Puedes agregar aquí la lógica para mostrar un mensaje de error
            }

        } else {
            labelAuxiliar.setText("Las contraseñas no coinciden");
        }
}
    @Override
public void initialize(URL url, ResourceBundle rb) {
    // TODO
}  
@FXML
private void ActionIniciarSesion(ActionEvent event){
    try {
            loadStage("/view/interfaz.fxml", event);
        } catch (IOException ex) {
            Logger.getLogger(interfazController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
private void loadStage(String url, Event event) throws IOException{
    Object eventSourse = event.getSource();
    Node sourceAsNode = (Node) eventSourse;
    Scene oldScene = sourceAsNode.getScene();
    Window window = oldScene.getWindow();
    Stage stage = (Stage) window;
    stage.hide();

    Parent root = FXMLLoader.load(getClass().getResource(url));
    Scene scene = new Scene(root);
    Stage newStage = new Stage();
    newStage.setScene(scene);
    newStage.show();

}
    
}
