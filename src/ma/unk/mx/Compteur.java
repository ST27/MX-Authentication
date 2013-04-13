package ma.unk.mx;
public class Compteur {
 
	private int id;
	private int valeur;
 
	public  Compteur() {}
		// TODO Auto-generated constructor stub
	
	public Compteur(int valeur){
		this.valeur = valeur;
	}
	
	
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 

	public String toString(){return "ID : "+id +"Valeur : "+valeur;}


	public int getValeur() {
		return valeur;
	}


	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
}
