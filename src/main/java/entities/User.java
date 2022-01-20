package entities;

public class User {
	int id ;
	String nom;
	String prenom;
	String email;
	String password;
	String etat;
	String role;
	public User(String nom, String prenom, String email, String password, String etat, String role) {
		super();
		
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.etat = etat;
		this.role = role;
	}
	public User(int id, String nom, String prenom, String email, String password, String etat, String role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.etat = etat;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password
				+ ", etat=" + etat + ", role=" + role + "]";
	}
	
	
	

	
}
