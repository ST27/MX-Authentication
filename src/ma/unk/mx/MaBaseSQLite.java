package ma.unk.mx;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
 
public class MaBaseSQLite extends SQLiteOpenHelper {
 
	private static final String TABLE_COMPTEUR = "table_compteur";
	private static final String COL_ID = "ID";
	private static final String COL_VALEUR = "VALEUR";

	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_COMPTEUR + " ("
	+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_VALEUR + " INTEGER);";
 
	public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		//on cr�� la table � partir de la requ�te �crite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai d�cid� de supprimer la table et de la recr�er
		//comme �a lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + TABLE_COMPTEUR + ";");
		onCreate(db);
	}
 
}
