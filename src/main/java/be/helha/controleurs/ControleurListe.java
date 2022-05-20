package be.helha.controleurs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import be.helha.domaine.Bundle;
import be.helha.domaine.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControleurListe implements Initializable {

	@FXML
	private TableView<History> tableView;
	@FXML
	private TableColumn<History, String> colCpteDonneur, colCpteReceveur;
	@FXML
	private TableColumn<History, Double> colMontant;
	
	private GestionnaireUseCases gestionnaire = GestionnaireUseCases.getInstance();
	private ObservableList<History> tvObservableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bundle bundle = new Bundle();
		gestionnaire.lister(bundle);
		@SuppressWarnings("unchecked")
		List<History> listeHistory = (List<History>) bundle.get(Bundle.LISTE);
		tvObservableList.addAll(listeHistory);
		colCpteDonneur.setCellValueFactory(new PropertyValueFactory<History, String>("cpteDonneur"));
		colCpteReceveur.setCellValueFactory(new PropertyValueFactory<History, String>("cpteReceveur"));
		colMontant.setCellValueFactory(new PropertyValueFactory<History, Double>("montant"));
		this.tableView.setItems(tvObservableList);
	}
}
