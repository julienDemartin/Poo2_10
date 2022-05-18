package be.helha.domaine;

public class History {
	private int historyID;
	private String cpteDonneur;
	private String cpteReceveur;
	private Double montant;
	
	public History(int historyID, String cpteDonneur, String cpteReceveur, Double montant) {
		super();
		this.historyID = historyID;
		this.cpteDonneur = cpteDonneur;
		this.cpteReceveur = cpteReceveur;
		this.montant = montant;
	}

	public int getHistoryID() {
		return historyID;
	}

	public void setHistoryID(int historyID) {
		this.historyID = historyID;
	}

	public String getCpteDonneur() {
		return cpteDonneur;
	}

	public void setCpteDonneur(String cpteDonneur) {
		this.cpteDonneur = cpteDonneur;
	}

	public History(History history) {
		super();
		this.historyID = history.historyID;
		this.cpteDonneur = history.cpteDonneur;
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
