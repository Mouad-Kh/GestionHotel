package entities;

public class Chambre {
	int id;
	int num;
	String type;
	int prix;
	public Chambre(int num, String type, int prix) {
		super();
		this.num = num;
		this.type = type;
		this.prix = prix;
	}
	public Chambre(int id, int num, String type, int prix) {
		super();
		this.id = id;
		this.num = num;
		this.type = type;
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "Chambre [id=" + id + ", num=" + num + ", type=" + type + ", prix=" + prix + "]";
	}
	
	

}
