package be.helha.controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import be.helha.daoimpl.UserDaoImpl;
import be.helha.domaine.Bundle;
import be.helha.domaine.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleurPrincipal implements Initializable {

	private static ControleurPrincipal singleton;
	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private UserDaoImpl userDao;
	private Bundle bundle = new Bundle();
	private User user = new User();

	public static ControleurPrincipal getInstance() {
		return singleton;
	}

	@FXML
	private TextField tfNom, tfNumero, tfMessage;
	@FXML
	private Button btAjouter, btSupprimer, btModifier, btLister, btRechercher, btConnecter;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfMessage.setText("Veuillez vous connecter");
		if (singleton == null)
			singleton = this; // on mémorise afin d'y accéder par la suite
	}


	public void trtBoutonConnecter() 
	{
		
		Parent root;
		try
		{
		
			if(btConnecter.getText()=="Déconnecter")
			{
				btConnecter.setText("Connecter");
				btAjouter.setDisable(true);
				btLister.setDisable(true);
				btModifier.setDisable(true);
				btRechercher.setDisable(true);
				btSupprimer.setDisable(true);
				gestionnaire.deconnecterUser(bundle);
				this.user= (User)bundle.get(Bundle.USER);
				tfMessage.setText(this.user.getNom()+" à été déconnecter");
				
			}
			else	
			{
				tfMessage.setText("");
				root = FXMLLoader.load(getClass().getResource("/be/helha/vues/VueLogin.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Connexion");
				stage.getIcons().add(new Image("/be/helha/application/banque.jpg"));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.showAndWait();
				if((boolean)bundle.get(Bundle.OPERATION_REUSSIE)==true)
				{
					//garnirBundle();
					btConnecter.setText("Déconnecter");
					btAjouter.setDisable(false);
					btLister.setDisable(false);
					btModifier.setDisable(false);
					btRechercher.setDisable(false);
					btSupprimer.setDisable(false);
					gestionnaire.connecterUser(bundle);
					this.user = (User)bundle.get(Bundle.USER);
					tfMessage.setText(this.user.getNumero());
				}
			}
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	private void garnirBundle() {
        User user = new User();
        
        user = userDao.getUser((String)bundle.get(user.getEmail()), (String)bundle.get(user.getPassword()));
        bundle.put(Bundle.USER, user);
    }
	public void ajouter()
	{
		
	}
	public void supprimer()
	{
		
	}
	public void rechercher()
	{
		
	}
	public void modifier()
	{
		
	}
	public void lister()
	{
		
	}
	

	

	private void majMessage() {
		String message = (String) bundle.get(Bundle.MESSAGE);
		this.tfMessage.setText(message);
	}


	public Bundle getBundle() {
		return bundle;
	}


	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
	
	

	

	

	

	

}
