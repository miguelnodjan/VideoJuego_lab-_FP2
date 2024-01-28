/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import model.UserDAO;
import portada.sonidos;
//import model.UserDAO;

/**
 *
 * @author migue
 */
public class interfazController implements Initializable {
    sonidos reproduce = new sonidos();
    UserDAO model = new UserDAO();
    @FXML
    private TextField usuarioIngresar;
    
    @FXML
    private PasswordField contraseñaIngresar;
    @FXML
    private Label labelError;
    @FXML
    private Button botonIngresar;
    @FXML
    private Button botonCrearCuenta;
    
    @FXML
    public void eventKey(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(usuarioIngresar)) {
            if (event.getText().equals(" ")) {
                event.consume();
                usuarioIngresar.setText("");
            }
        }

        if (evt.equals(contraseñaIngresar)) {
            if (event.getCharacter().equals(" ") || contraseñaIngresar.getText().length() > 7) {
                event.consume();

            }
        }
    }
    @FXML
    private void eventActionRegistrarse(ActionEvent event){
        try {
            loadStage("/view/interfazRegistro.fxml", event, "");
        } catch (IOException ex) {
            Logger.getLogger(interfazController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eventAction(ActionEvent event) throws IOException{
        
        String nombre = usuarioIngresar.getText().trim();
        String password = contraseñaIngresar.getText().trim();
        labelError.setText("");
        if (nombre.isEmpty() || password.isEmpty()) {
            labelError.setText("Por favor, complete todos los campos.");
            
            return;
        }

        if (nombre.contains(" ") || password.contains(" ") || password.length() > 8) {
            labelError.setText("Nombre sin espacios y contraseña de hasta 8 caracteres, sin espacios.");
            
            return;
        }

        // Si llegamos aquí, todo está bien
        
        String user  = usuarioIngresar.getText();
        String pass = contraseñaIngresar.getText();
        int state = model.login(user, pass);
        if (state !=-1) {
            if (state ==1) {
                JOptionPane.showMessageDialog(null, "Datos correctos, bienvenido a CrusadeOFKingDoms");
                //reproduce.sonidoFondo();
                 try {
                    loadStage("/view/menuJuego.fxml", event, user);
                } catch (IOException ex) {
                    Logger.getLogger(interfazController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                usuarioIngresar.setText("");
                contraseñaIngresar.setText("");
                JOptionPane.showMessageDialog(null, "Error al iniciar sesión, datos de acceso incorrectos","ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private void loadStage(String url, Event event, String user) throws IOException {
        Object eventSource = event.getSource();
        Node sourceAsNode = (Node) eventSource;
        Scene oldScene = sourceAsNode.getScene();
        Window window = oldScene.getWindow();
        Stage stage = (Stage) window;
        stage.hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        // Puedes acceder al controlador de la nueva ventana y pasar el valor de 'user'
        MenuJuegoController menuJuegoController = loader.getController();
        menuJuegoController.setUser(user);

        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }

    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }    
    
}
