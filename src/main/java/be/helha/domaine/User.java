package be.helha.domaine;

public class User {
	private String email;
	private String nom;
	private String password;
	private Banque cpteCourant;
	
	public User() {
		super();
		this.email = "";
		this.nom="";
		this.password = "";
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", nom=" + nom + ", motDePasse="
				+ password + ", CompteCourant=" + cpteCourant +"]";
	}

	public Banque getCpteCourant() {
		return cpteCourant;
	}

	public void setCpteCourant(Banque cpteCourant) {
		this.cpteCourant = cpteCourant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
