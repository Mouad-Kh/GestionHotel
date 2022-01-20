package entities;

import java.util.Date;

public class Reservation {
	int id;
	Date dated;
	Date datef;
	int prix;
	User client;
	Chambre chambre;
	public Reservation(Date dated, Date datef, int prix, User client, Chambre chambre) {
		super();
		this.dated = dated;
		this.datef = datef;
		this.prix = prix;
		this.client = client;
		this.chambre = chambre;
	}
	public Reservation(int id, Date dated, Date datef, int prix, User client, Chambre chambre) {
		super();
		this.id = id;
		this.dated = dated;
		this.datef = datef;
		this.prix = prix;
		this.client = client;
		this.chambre = chambre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDated() {
		return dated;
	}
	public void setDated(Date dated) {
		this.dated = dated;
	}
	public Date getDatef() {
		return datef;
	}
	public void setDatef(Date datef) {
		this.datef = datef;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public Chambre getChambre() {
		return chambre;
	}
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", dated=" + dated + ", datef=" + datef + ", prix=" + prix + ", client="
				+ client + ", chambre=" + chambre + "]";
	}
	
	
}