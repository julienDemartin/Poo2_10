package be.helha.domaine;

public class History {
	private String cpteReceveur;
	private Double montant;
	
	public History(String cpteReceveur, Double montant) {
		super();
		this.cpteReceveur = cpteReceveur;
		this.montant = montant;
	}
	
	public History(History history) {
		super();
		this.cpteReceveur = history.cpteReceveur;
		this.montant = history.montant;
	}

	public String getCpteReceveur() {
		return cpteReceveur;
	}

	public void setCpteReceveur(String cpteReceveur) {
		this.cpteReceveur = cpteReceveur;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "History [cpteReceveur=" + cpteReceveur + ", montant=" + montant + "]";
	}	
}
