package be.helha.usecase;

import be.helha.domaine.Bundle;

public interface GestionHistory {
	void ajouterHistory(Bundle bundle);
	void lister(Bundle bundle);
}
