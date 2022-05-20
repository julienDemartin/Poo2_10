package be.helha.usecaseimpl;

import java.util.List;

import be.helha.dao.HistoryDao;
import be.helha.daoimpl.DaoFactory;
import be.helha.domaine.Bundle;
import be.helha.domaine.History;
import be.helha.domaine.User;
import be.helha.usecase.GestionHistory;

public class GestionHistoryImpl implements GestionHistory {
	private HistoryDao historyDao;
	private User user = new User();
	
	public GestionHistoryImpl() {
		this.historyDao = (HistoryDao) DaoFactory.getInstance().getDaoImpl(HistoryDao.class);
	}
	
	@Override
	public void ajouterHistory(Bundle bundle) {
		boolean ajoutReussi = false;
		String message = "";
		History history = (History) bundle.get(Bundle.HISTORY);
		if (history.getCpteDonneur() == null || history.getCpteDonneur().isEmpty()) {
			message = "L'ajout n'a pas pu être réalisé. Il manque un compte donneur";
		}else if (history.getCpteReceveur() == null || history.getCpteReceveur().isEmpty()) {
			message = "L'ajout n'a pas pu être réalisé. Il manque un compte receveur";
		}else if (history.getMontant() <= 0 || history.getMontant().isNaN()) {
			message = "L'ajout n'a pas pu être réalisé. Il manque un montant";
		}else {
			ajoutReussi = this.historyDao.ajouterHistory(history);
			if(ajoutReussi) {
				message = "Ajout réalisé avec succès";
			}else {
				message = "L'ajout n'a pas pu être réalisé";
			}
		}
		bundle.put(Bundle.OPERATION_REUSSIE, ajoutReussi);
		bundle.put(Bundle.HISTORY, history);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void lister(Bundle bundle) {
		boolean listeOk = true;
		String message = "";
		this.user = (User)bundle.get(Bundle.USER);
		List<History> listeHistory = null;
		listeHistory = this.historyDao.listerHistory(this.user.getNumero());
		if (listeHistory==null) {
			listeOk = false;
		} else if (listeHistory.isEmpty())
			message = "liste vide";
		else if (listeHistory.size() == 1)
			message = "Il y a un virement";
		else 
			message = "Il y a " + listeHistory.size() + " virements";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, listeHistory);
	}

}
