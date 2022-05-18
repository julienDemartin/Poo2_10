package be.helha.domaine;

public class History {
	private String cpteBeneficiare;
	private Double montant;
	
	public History(String cpteBeneficiare, Double montant) {
		super();
		this.cpteBeneficiare = cpteBeneficiare;
		this.montant = montant;
	}
	
	public History(History history) {
		super();
		this.cpteBeneficiare = history.cpteBeneficiare;
		this.montant = history.montant;
	}

	public String getCpteBeneficiare() {
		return cpteBeneficiare;
	}

	public void setCpteBeneficiare(String cpteBeneficiare) {
		this.cpteBeneficiare = cpteBeneficiare;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return "History [cpteBeneficiare=" + cpteBeneficiare + ", montant=" + montant + "]";
	}	
}
