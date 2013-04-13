package ma.unk.mx;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class CompteurBDD {
 
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "compteur.db";
 
	private static final String TABLE_COMPTEUR = "table_compteur";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_VALEUR = "VALEUR";
	@SuppressWarnings("unused")
	private static final int NUM_COL_VALEUR = 1;
	private SQLiteDatabase bdd;
 
	private MaBaseSQLite maBaseSQLite;
 
	public CompteurBDD(Context context){
		//On cr�er la BDD et sa table
		maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en �criture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'acc�s � la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertLivre(Compteur compteur){
		//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		values.put(COL_VALEUR, compteur.getValeur());
          
		//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		//on ins�re l'objet dans la BDD via le ContentValues
		return bdd.insert(TABLE_COMPTEUR, null, values);
	}
 
	public int updateLivre(int id, Compteur compteur){
		//La mise � jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple pr�ciser quelle livre on doit mettre � jour gr�ce � l'ID
		
		ContentValues values = new ContentValues();
		values.put(COL_VALEUR, compteur.getValeur());
		
		
		return bdd.update(TABLE_COMPTEUR, values, COL_ID + " = " +id, null);
	}
 
	public int removeLivreWithID(int id){
		//Suppression d'un livre de la BDD gr�ce � l'ID
		return bdd.delete(TABLE_COMPTEUR, COL_ID + " = " +id, null);
	}
 
	public Compteur getLivreWithID(int ID){
		//R�cup�re dans un Cursor les valeur correspondant � un livre contenu dans la BDD (ici on s�lectionne le livre gr�ce � son titre)
		Cursor c = bdd.query(TABLE_COMPTEUR, new String[] {COL_ID},  " LIKE " + ID + ";" , null, null, null, null);
		return cursorToCompteur(c);
	}
 
	//Cette m�thode permet de convertir un cursor en un livre
	private Compteur cursorToCompteur(Cursor c){
		//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier �l�ment
		c.moveToFirst();
		//On cr�� un livre
		Compteur livre = new Compteur();
		//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
		livre.setId(c.getInt(NUM_COL_ID));
		//On ferme le cursor
		c.close();
 
		//On retourne le livre
		return livre;
	}
}
