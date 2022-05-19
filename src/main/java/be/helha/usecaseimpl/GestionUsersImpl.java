package be.helha.usecaseimpl;

import be.helha.dao.UserDao;
import be.helha.daoimpl.DaoFactory;
import be.helha.domaine.Bundle;
import be.helha.domaine.User;
import be.helha.usecase.GestionUsers;

public class GestionUsersImpl implements GestionUsers {
	
	private UserDao userDao;

	public GestionUsersImpl() {
		this.userDao = (UserDao) DaoFactory.getInstance().getDaoImpl(UserDao.class);
	}

	@Override
	public void connecterUser(Bundle bundle) {
		User userDB = null;
		boolean operationReussie = false;
		String message;
		User user = (User) bundle.get(Bundle.USER);
		if (user == null) {
			message = "Pas d'utilisateur!";
		} else {
			String email = user.getEmail();
			String password = user.getPassword();
			userDB = this.userDao.getUser(email, password);
			if (userDB == null) {
				message = "Echec lors de l'identification.";
			} else {
				message = "Identification réussie. Vous êtes connecté.";
				operationReussie = true;
				
				bundle.put(Bundle.USER, userDB);
			}
		}
		bundle.put(Bundle.OPERATION_REUSSIE, operationReussie);
		bundle.put(Bundle.MESSAGE, message);
	}	
}