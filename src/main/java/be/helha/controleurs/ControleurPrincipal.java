package be.helha.controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import be.helha.daoimpl.UserDaoImpl;
import be.helha.domaine.Bundle;
import be.helha.domaine.History;
import be.helha.domaine.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleurPrincipal implements Initializable {

	private static ControleurPrincipal singleton;
	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private UserDaoImpl userDao;
	private Bundle bundle = new Bundle();
	private User user = new User();
	private History history = new History();

	public static ControleurPrincipal getInstance() {
		return singleton;
	}

	@FXML
	private TextField tfNom, tfNumero, tfMessage;
	@FXML
	private Button  btLister, btConnecter,btVirement;
	@FXML Text tfName,tfEmail,tfSolde,tfDecouvert;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfMessage.setText("Veuillez vous connecter");
		tfName.setText("");
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
				
				btLister.setDisable(true);
				btVirement.setDisable(true);
				gestionnaire.deconnecterUser(bundle);
				this.user= (User)bundle.get(Bundle.USER);
				tfMessage.setText(this.user.getNom()+" a été déconnecté");
				tfName.setText("");
				tfEmail.setText("");
				tfSolde.setText("");
				tfDecouvert.setText("");
			}
			else	
			{
				
				tfMessage.setText("");
				root = FXMLLoader.load(getClass().getResource("/be/helha/vues/VueLogin.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Connexion");
				stage.getIcons().add(new Image("/be/helha/application/CPHH.jpg"));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.showAndWait();
				if((boolean)bundle.get(Bundle.OPERATION_REUSSIE)==true)
				{
					
					btConnecter.setText("Déconnecter");
					
					btLister.setDisable(false);
					btVirement.setDisable(false);
					
					gestionnaire.connecterUser(bundle);
					this.user = (User)bundle.get(Bundle.USER);
					tfMessage.setText(this.user.getNom()+" est bien connecté");
					tfName.setText(this.user.getNom());
					tfEmail.setText(this.user.getEmail());
					String convertString;
					double convertDouble;
					convertDouble = this.user.getSolde();
					convertString =  this.convertDoubleToString(convertDouble);
					tfSolde.setText(convertString);
					convertDouble = this.user.getDecouvert();
					convertString=  this.convertDoubleToString(convertDouble);
					tfDecouvert.setText(convertString);
				}
			}
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public Double convertStringToDouble(String string)
	{
		double num;
		num = Double.parseDouble(string);
		
		return num;
	}
	public String convertDoubleToString(double unDouble)
	{
		String string;
		string = String.valueOf(unDouble);
		return string;
	}
	private void garnirBundle() {
        User user = new User();
        
        user = userDao.getUser((String)bundle.get(user.getEmail()), (String)bundle.get(user.getPassword()));
        bundle.put(Bundle.USER, user);
    }
	
	public void lister(){
		Parent root;
		try {
			tfMessage.setText("");
			root = FXMLLoader.load(getClass().getResource("/be/helha/vues/vueListe.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Historique des virements");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void effectuerVirement()
	{
		//todo
	}

	private void majMessage() {
		String message = (String) bundle.get(Bundle.MESSAGE);
		this.tfMessage.setText(message);
	}


	public Bundle getBundle() {
		return bundle;
	}


	public void setBundle(Bundle bundle)
	{
		this.bundle = bundle;
	}
	
	

	

	

	

	

}
