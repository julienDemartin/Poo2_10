package be.helha.usecase;

import be.helha.domaine.Bundle;

public interface GestionBanque {
	void ajouterBiere(Bundle bundle);
	void rechercherBiere(Bundle bundle);
	void lister(Bundle bundle);
	void supprimerBiere(Bundle bundle);
	void modifierBiere(Bundle bundle);
}
