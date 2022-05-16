package be.helha.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import be.helha.domaine.Bundle;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class ControleurListe implements Initializable {

	

	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	

	
	public void selection(MouseEvent mouseEvent) 
	{
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		
		
	}
}
