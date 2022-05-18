package be.helha.daoimpl;

import java.util.List;
import be.helha.dao.HistoryDao;
import be.helha.domaine.History;

public class HistoryDaoImpl implements HistoryDao {
	private static final String AJOUT = "INSERT INTO History (cpteBeneficiaire, montant) VALUES (?,?)";
	private static final String LISTER = "SELECT * FROM History h ORDER BY h.";
	
	public HistoryDaoImpl() {
		
	}

	@Override
	public boolean ajouterHistory(History history) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<History> listerHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
