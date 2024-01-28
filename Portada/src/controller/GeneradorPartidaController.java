/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class GeneradorPartidaController implements Initializable{

	@FXML
	private Spinner<Integer> mySpinner1;
	
	@FXML
        private Spinner<Integer> mySpinner2;    
	
        int currentValue;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
		
		valueFactory.setValue(1);
		
		mySpinner1.setValueFactory(valueFactory);
			
		mySpinner2.setValueFactory(valueFactory);
			
	}
}

