package be.helha.usecase;

import be.helha.domaine.Bundle;

public interface GestionUsers {
	void connecterUser(Bundle bundle);
	void ajouterMontant(Bundle bundle);
	void retirerMontant(Bundle bundle);
	void miseAjour(Bundle bundle);
}
