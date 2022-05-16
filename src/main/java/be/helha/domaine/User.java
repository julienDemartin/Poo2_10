package be.helha.domaine;

public class User {
	private String email;
	private String nom;
	private String password;
	private String numero;
	private double solde;
	private double decouvert;
	
	public User() 
	{
	
	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", nom=" + nom + ", password=" + password + ", numero=" + numero + ", solde="
				+ solde + ", decouvert=" + decouvert + "]";
	}

	

	

	

}
