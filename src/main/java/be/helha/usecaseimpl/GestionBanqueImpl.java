package be.helha.usecaseimpl;

import be.helha.dao.CompteEnBanqueDao;
import be.helha.daoimpl.DaoFactory;
import be.helha.domaine.Bundle;
import be.helha.usecase.GestionBanque;

public class GestionBanqueImpl implements GestionBanque {
	private CompteEnBanqueDao compteAVue;

	public GestionBanqueImpl() {
		this.compteAVue = (CompteEnBanqueDao) DaoFactory.getInstance().getDaoImpl(CompteEnBanqueDao.class) ;
	
	}
	
	@Override
	public void ajouterBiere(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rechercherBiere(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lister(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerBiere(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierBiere(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	
}
