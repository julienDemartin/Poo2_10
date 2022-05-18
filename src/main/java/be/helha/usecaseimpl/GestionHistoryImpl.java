package be.helha.usecaseimpl;

import be.helha.dao.HistoryDao;
import be.helha.daoimpl.DaoFactory;
import be.helha.domaine.Bundle;
import be.helha.usecase.GestionHistory;

public class GestionHistoryImpl implements GestionHistory {
	private HistoryDao historyDao;
	
	public GestionHistoryImpl() {
		this.historyDao = (HistoryDao) DaoFactory.getInstance().getDaoImpl(HistoryDao.class);
	}
	
	@Override
	public void ajouterHistory(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lister(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

}
