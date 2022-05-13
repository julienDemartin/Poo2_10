package be.helha.dao;

import be.helha.domaine.Banque;
import be.helha.domaine.User;

public interface CompteEnBanqueDao extends Dao {
	//void deposer(double montant) throws Exception;
	//void retirer(double montant) throws Exception;
	Boolean effectuerVirement(User emetteur, Banque cpteDestination, double montant) throws Exception;

}
