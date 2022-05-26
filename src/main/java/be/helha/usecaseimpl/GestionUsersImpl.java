package be.helha.usecaseimpl;

import be.helha.dao.UserDao;
import be.helha.daoimpl.DaoFactory;
import be.helha.domaine.Bundle;
import be.helha.domaine.History;
import be.helha.domaine.User;
import be.helha.usecase.GestionUsers;

public class GestionUsersImpl implements GestionUsers {
	
	private UserDao userDao;

	public GestionUsersImpl() {
		this.userDao = (UserDao) DaoFactory.getInstance().getDaoImpl(UserDao.class);
	}

	@Override
	public void connecterUser(Bundle bundle)
	{
		User userDB = null;
		boolean operationReussie = false;
		String message;
		User user = (User) bundle.get(Bundle.USER);
		if (user == null) 
		{
			message = "Pas d'utilisateur!";
		} 
		else 
		   {
			String email = user.getEmail();
			String password = user.getPassword();
			userDB = this.userDao.getUser(email, password);
			if (userDB == null) {
				message = "Echec lors de l'identification.";
			} else 
			{
				message = "Identification réussie. Vous êtes connecté.";
				operationReussie = true;
				
				bundle.put(Bundle.USER, userDB);
			}
		}
		bundle.put(Bundle.OPERATION_REUSSIE, operationReussie);
		bundle.put(Bundle.MESSAGE, message);
	}
	
	@Override
	public void ajouterMontant(Bundle bundle) {
		boolean ajoutReussi = false;
		String message = "";
		History history = (History)bundle.get(Bundle.HISTORY);
		User userDonneur = (User)bundle.get(Bundle.USER);
		User userReceveur = userDao.getReceveur(history.getCpteReceveur());
		Double MontantMax = (userDonneur.getSolde() - userDonneur.getDecouvert());
		if (history.getCpteDonneur() == null || history.getCpteDonneur().isEmpty() || history.getCpteDonneur()!= userDonneur.getNumero()) {
			message = "La modification n'a pas pu être réalisé, il manque le numéro de compte du donneur";
		} else if (history.getCpteReceveur() == null || history.getCpteReceveur().isEmpty() || history.getCpteReceveur() == userDonneur.getNumero()) {
			message = "La modification n'a pas pu être réalisé, il manque le numéro de compte du receveur, ou le compte receveur et donneur sont les memes";
		} else if (history.getMontant() <= 0 || history.getMontant() > MontantMax || history.getMontant().isNaN()) {
			message = "La modification n'a pas pu être réalisé, le montant est négatif,nul, ou il dépasse votre limite";
		} else if (userReceveur.getNumero() == null || userReceveur.getNumero().isEmpty()) {
			message = "La modification n'a pas pu être réalisé, le compte receveur n'existe pas";
		} else {
			Double newSolde = (userReceveur.getSolde() + history.getMontant());
			ajoutReussi = this.userDao.ajouterMontant(history.getCpteReceveur(), newSolde);
			bundle.put(Bundle.OPERATION_REUSSIE, ajoutReussi);
		}
		bundle.put(Bundle.HISTORY, history);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void retirerMontant(Bundle bundle) {
		boolean ajoutReussi = false;
		String message = "";
		History history = (History)bundle.get(Bundle.HISTORY);
		User user = (User)bundle.get(Bundle.USER);
		Double MontantMax = (user.getSolde() - user.getDecouvert());
		if (history.getCpteDonneur() == null || history.getCpteDonneur().isEmpty() || history.getCpteDonneur()!= user.getNumero()) {
			message = "La modification n'a pas pu être réalisé, il manque le numéro de compte du donneur";
		} else if (history.getCpteReceveur() == null || history.getCpteReceveur().isEmpty() || history.getCpteReceveur() == user.getNumero()) {
			message = "La modification n'a pas pu être réalisé, il manque le numéro de compte du receveur, ou le compte receveur et donneur sont les memes";
		} else if (history.getMontant() <= 0 || history.getMontant() > MontantMax || history.getMontant().isNaN()) {
			message = "La modification n'a pas pu être réalisé, le montant est négatif,nul, ou il dépasse votre limite";
		} else {
			Double newSolde = user.getSolde()- history.getMontant();
			ajoutReussi = this.userDao.retirerMontant(history.getCpteDonneur(), newSolde);
			bundle.put(Bundle.OPERATION_REUSSIE, ajoutReussi);
		}
		bundle.put(Bundle.HISTORY, history);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void miseAjour(Bundle bundle) {
		User user = (User)bundle.get(Bundle.USER);
		user =this.userDao.getMaj(user.getNumero());
		bundle.put(Bundle.USER, user);
		
	}	
}