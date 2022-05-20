package be.helha.controleurs;

import be.helha.domaine.Bundle;
import be.helha.domaine.User;
import be.helha.usecase.GestionHistory;
import be.helha.usecase.GestionUsers;
import be.helha.usecaseimpl.GestionHistoryImpl;
import be.helha.usecaseimpl.GestionUsersImpl;

public class GestionnaireUseCases implements GestionUsers, GestionHistory {

	private static final GestionnaireUseCases INSTANCE = new GestionnaireUseCases();

	private User user; // null si pas identifié par le système
	private GestionUsers gestionUsers;
	private GestionHistory gestionHistory;
	

	public static GestionnaireUseCases getInstance()
	{
		return INSTANCE;
	}

	private GestionnaireUseCases() 
	{
		this.gestionUsers = new GestionUsersImpl();
		this.gestionHistory = new GestionHistoryImpl();
		this.user = null; // pas de user connecté
	}

	@Override
	public void connecterUser(Bundle bundle) 
	{
		if (user == null)
		{
			this.gestionUsers.connecterUser(bundle);
			if ((boolean) bundle.get(Bundle.OPERATION_REUSSIE))
			{
				this.user = (User) bundle.get(Bundle.USER);
				bundle.put(Bundle.MESSAGE, "Bienvenue " + user.getNom());
			}
		} else 
		{ // un utilisateur est déjà connecté
			bundle.put(Bundle.MESSAGE,
					"Opération impossible. Un utilisateur est déjà connecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		}
	}

	public void deconnecterUser(Bundle bundle)
	{
		if (user == null) 
		{ // pas de user identifié
			bundle.put(Bundle.MESSAGE,
					"Opération impossible. Pas d'utilisateur connecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		} else 
		{
			this.user = null; // utilisateur déconnecté
			bundle.put(Bundle.MESSAGE, "Vous avez été déconnecté.");
			bundle.put(Bundle.OPERATION_REUSSIE, true);
		}
	}

	@Override
	public void ajouterHistory(Bundle bundle) {
		if (user == null) {
			bundle.put(Bundle.MESSAGE, "Opération impossible, Pas d'utilisateur connecté");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		} else {
			this.gestionHistory.ajouterHistory(bundle);
		}
	}

	@Override
	public void lister(Bundle bundle) {
		if (user == null) {
			bundle.put(Bundle.MESSAGE, "Opération impossible, Pas d'utilisateur connecté");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		} else {
			this.gestionHistory.lister(bundle);
		}
	}

	

	
}
