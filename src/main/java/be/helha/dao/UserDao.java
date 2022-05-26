package be.helha.dao;

import be.helha.domaine.User;

public interface UserDao extends Dao {
	User getUser(String email, String password);
	User getReceveur(String numero);
	User getMaj(String numero);
	boolean ajouterMontant(String cptereceveur, Double montant);
	boolean retirerMontant(String cptedonneur, Double montant);
}
