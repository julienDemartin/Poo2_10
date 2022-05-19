package be.helha.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import be.helha.domaine.Bundle;
import be.helha.domaine.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleurLogin implements Initializable {

	@FXML
	private TextField tfEmail, tfPassword;

	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfEmail.setText("toto@gmail.com");
		tfPassword.setText("1234");
	}

	public void trtBoutonConnecter() 
	{
		Bundle bundle = new Bundle();
		User user = new User();
		user.setEmail(tfEmail.getText());
		user.setPassword(tfPassword.getText());
		
		bundle.put(Bundle.USER,(User) user);
		
		if((boolean)bundle.get(Bundle.OPERATION_REUSSIE)== true)
		{
			
			this.gestionnaire.connecterUser(bundle);
			tfEmail.getScene().getWindow().hide();
		}
		if((boolean)bundle.get(Bundle.OPERATION_REUSSIE)==false)
		{
			Alert alert = new Alert(AlertType.ERROR);
			bundle.put(Bundle.OPERATION_REUSSIE, false);
			alert.setTitle("Identificatioon");
			alert.setHeaderText("");
			String message ="Echec lors de l'id";
			alert.setContentText(message);
			bundle.put(Bundle.OPERATION_REUSSIE, false);
			alert.showAndWait();
		}
	}

}
