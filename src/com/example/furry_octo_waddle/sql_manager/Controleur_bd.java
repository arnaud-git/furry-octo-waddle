package com.example.furry_octo_waddle.sql_manager;

import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.support.v7.app.ActionBarActivity;
import com.example.furry_octo_waddle.sql_manager.FeedReaderContract.FeedEntry;

/** Parts of code were found on https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow
 */

public class Controleur_bd implements BD_rw{
	private FeedReaderDbHelper mDbHelper;	

	public Controleur_bd(ActionBarActivity ba) {
		mDbHelper = new FeedReaderDbHelper(ba);
		MainActivity.printDebug(1,"controleur cree");
	}

	/**@see writeWord(Word_Translation word)*/
	public void ecrireDansLaBase(Word_Translation word){
		// Gets the data repository in write mode
		SQLiteDatabase db = null;
		try{
			db = mDbHelper.getWritableDatabase();}
		catch (Exception e){
			MainActivity.printDebug(1, e.getMessage());
		}
		//MainActivity.printDebug(1, "S'arrete la");

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(language_to_column_Correspondance(word.getLanguage()), word.getWord());
		values.put(language_to_column_Correspondance(word.getTargeted_language()), word.getTraduction_of_word());

		// Insert the new row, returning the primary key value of the new row
		try{

			//TODO Remove ***OrThrow
			long newRowId = db.insertOrThrow(
					FeedEntry.TABLE_NAME,
					null,
					values);
			if(newRowId>=0)
				word.setId((int)newRowId);
			//else 	
			//Erreur
		}catch (SQLException e){
			MainActivity.printDebug(1, e.getMessage());
		}
	}
	public List<Word_Translation> lireDepuisLaBase(String[] args,Order random,int nombre){
		return getWordFromTable(new Word_Translation(args[2],args[3], args[0], args[1]),random,nombre);
	}

	@Override
	public List<Word_Translation> getWordFromTable(Word_Translation word,Order random,int nombre){
		List<Word_Translation> list = new ArrayList<Word_Translation>();
		MainActivity.printDebug(1,"GET");
		word.printWord();
		try{
			SQLiteDatabase db = mDbHelper.getReadableDatabase();

			// Define a projection that specifies which columns from the database
			// you will actually use after this query.
			//MainActivity.printDebug(1, "ICI");
			String[] projection = {
					FeedEntry._ID,
					language_to_column_Correspondance(word.getLanguage()),
					language_to_column_Correspondance(word.getTargeted_language()),
					FeedEntry.COLUMN_NAME_TIMESTAMP
			};

			// Define 'where' part of query.
			String selection = null;
			//String sel =null;
			List<String> sel = new ArrayList<String>();
			String mot = word.getWord(), trad = word.getTraduction_of_word() ;
			if(word.getId()==0){
				selection = language_to_column_Correspondance(word.getLanguage());
				if (mot.startsWith("~")){
					selection = selection + " NOT ";
					mot=mot.substring(1);
				}
				selection = selection +" LIKE ? AND "+language_to_column_Correspondance(word.getTargeted_language())  ;
				if (trad.startsWith("~")){
					selection = selection + " NOT ";
					trad=trad.substring(1);
				}
				selection = selection +  " LIKE ? "  ;
				sel.add(mot);
				sel.add(trad);
			}else{
				selection = FeedEntry._ID + " LIKE ? "  ;
				sel.add(String.valueOf(word.getId()));
			}

			// How you want the results sorted in the resulting Cursor
			String sortOrder = // language_to_column_Correspondance(word.getLanguage())+ " DESC";
					// Mettre l'ordre alphabetique
					null;
			switch(random){
			case RANDOM : 
				sortOrder = " RANDOM() ";
				break;
			case LANGUAGE_ASC :
				sortOrder = " "+language_to_column_Correspondance(word.getLanguage())+ " ASC ";
				break;
			case STAMP_DESC :
				sortOrder = " "+FeedEntry.COLUMN_NAME_TIMESTAMP+ " DESC ";
				break;
			case NULL : 
				sortOrder = null;
				break;
			default : 
				sortOrder = null;
				break;
			}
			//
			String nb = null;
			if(nombre >=0)
				nb =String.valueOf(nombre);	

			// Specify arguments in placeholder order.
			for (String toto :sel)	
				MainActivity.printDebug(1, toto);
			String[] selectionArgs = (String[]) sel.toArray(new String[sel.size()]);

			MainActivity.printDebug(1, "Sel :"+selection);
			MainActivity.printDebug(1, "Args :" +selectionArgs.length);

			//the query
			Cursor c = db.query(
					false,
					FeedEntry.TABLE_NAME,  // The table to query
					projection,                               // The columns to return
					selection,                                // The columns for the WHERE clause
					selectionArgs,                         // The values for the WHERE clause
					null,                                     // don't group the rows
					null,                                     // don't filter by row groups
					sortOrder,                                 // The sort order
					nb
					);

			if(c.getCount()>0){
				MainActivity.printDebug(1,"Recuperation de  " +c.getCount() +" trad.");
				while(c.moveToNext()){
					list.add(new Word_Translation(
							c.getString(0),//_ID
							c.getString(1),//WORD
							c.getString(2)//TRANS
							//Si d'autres colonnes pour d'autres langues
							,column_to_language_Correspondance(c.getColumnName(1))
							,column_to_language_Correspondance(c.getColumnName(2))
							,c.getString(3)//TIMESTAMP
							));
				}
			}else{
				MainActivity.printDebug(1,"Rien dans la table "); 
			}
		}
		catch(Exception e){
			MainActivity.printDebug(1,e.getMessage()); 
		}
		//MainActivity.printDebug(1,"Showtime");
		for (Word_Translation toto : list){
			MainActivity.printDebug(1,"Id = "+toto.getId()+" 1-"+toto.getWord()+" 2-"+toto.getTraduction_of_word());
		}
		return list;
	}

	/** @see modifyWordbyId(Word_Translation word) */
	public void modifierDansLaBase(Word_Translation word){
		//mDbHelper = new FeedReaderDbHelper(ma.getApplicationContext());
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		// New value for one column
		ContentValues values = new ContentValues();
		values.put(language_to_column_Correspondance(word.getLanguage()), word.getWord());
		values.put(language_to_column_Correspondance(word.getTargeted_language()), word.getTraduction_of_word());
		values.put(FeedEntry.COLUMN_NAME_TIMESTAMP, FeedEntry.COLUMN_TIMESTAMP_DEFAULT);

		// Which row to update, based on the ID
		String selection = FeedEntry._ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(word.getId()) };
		try{
			//reutrn the number of row affected
			int count = db.update(
					FeedEntry.TABLE_NAME,
					values,
					selection,
					selectionArgs);
		}catch(Exception e){
			MainActivity.printDebug(1, e.getMessage());
		}
	}

	/** @see deleteWord(Word_Translation word)*/
	public void supprimerDeLaBase(Word_Translation word){

		//mDbHelper = new FeedReaderDbHelper(ma.getApplicationContext());
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		try{
			String selection = null;
			List<String> sel =new ArrayList<String>();
			String mot = word.getWord(), trad = word.getTraduction_of_word() ;
			if(word.getId()==0){
				selection = language_to_column_Correspondance(word.getLanguage());
				if (mot.startsWith("~")){
					selection = selection + " NOT ";
					mot = mot.substring(1);
				}
				selection = selection +" LIKE ? && "+ language_to_column_Correspondance(word.getTargeted_language())  ;
				if (trad.startsWith("~")){
					selection = selection + " NOT ";
					trad = trad.substring(1);
				}
				selection = selection  + " LIKE ? "  ;
				sel.add(mot);
				sel.add(trad);
			}else{
				selection = FeedEntry._ID + " LIKE ? "  ;
				sel.add(String.valueOf(word.getId()));
			}
			for (String toto :sel)	
				MainActivity.printDebug(1, toto);
			String[] selectionArgs = (String[]) sel.toArray(new String[sel.size()]);
			db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);

		}catch(Exception e){
			MainActivity.printDebug(1,e.getMessage()); 
		}
		MainActivity.printDebug(1,"fin"); 
	}

	@Override
	public List<Word_Translation> getRandomEnglishWordNotTranslated(int nombre) {
		String[] var = {Word_Translation.FRENCH,Word_Translation.ENGLISH,"","%"};
		List<Word_Translation> retour = lireDepuisLaBase(var, Order.RANDOM, nombre);
		return retour;
	}

	@Override
	public List<Word_Translation> getRandomFrenchWordNotTranslated(int nombre) {
		String[] var = {Word_Translation.FRENCH,Word_Translation.ENGLISH,"%",""};
		List<Word_Translation> retour = lireDepuisLaBase(var, Order.RANDOM, nombre);
		return retour;
	}

	@Override
	public List<Word_Translation> getRandomPairOfWords(int number) {
		String[] var = {Word_Translation.FRENCH,Word_Translation.ENGLISH,"%","%"};
		List<Word_Translation> retour = lireDepuisLaBase(var, Order.RANDOM, number);
		return retour;
	}

	@Override
	public void writeEnglishWordWOTranslation(String englishWord) {
		Word_Translation var = new Word_Translation(null,englishWord);
		ecrireDansLaBase(var);
	}

	@Override
	public void writeFrenchWordWOTranslation(String frenchWord) {
		Word_Translation var = new Word_Translation(frenchWord,null);
		ecrireDansLaBase(var);
	}

	@Override
	public void writePairOfWords(String frenchWord, String englishWord) {
		Word_Translation var = new Word_Translation(frenchWord,englishWord);
		ecrireDansLaBase(var);

	}

	@Override
	public void deleteFrenchWord(String frenchWord) {
		Word_Translation var = new Word_Translation(frenchWord,null);
		supprimerDeLaBase(var);
	}

	@Override
	public void deleteEnglishWord(String englishWord) {
		Word_Translation var = new Word_Translation(englishWord,null,Word_Translation.ENGLISH,null);
		//var.printWord();
		supprimerDeLaBase(var);
	}

	@Override
	public void deleteWordbyIndex(int index){
		Word_Translation var = new Word_Translation(String.valueOf(index),null,null,null,null);
		supprimerDeLaBase(var);
	}

	@Override
	public void deleteWord(Word_Translation word){
		word.printWord();
		MainActivity.printDebug(1, "DELETE");
		supprimerDeLaBase(word);
		showTable();
	}

	@Override
	public void modifyWordbyId(Word_Translation word){
		//TODO Suppress 4 lines below but modifierDans****
		word.printWord();
		modifierDansLaBase(word);
		MainActivity.printDebug(1, "MODIFY");
		showTable();
	}

	/*public void test(){
		ecrireDansLaBase(new Word_Translation("salut", ""));
		ecrireDansLaBase(new Word_Translation("Bojour", "Hello"));
		ecrireDansLaBase(new Word_Translation("Hush", "Frite"));
		ecrireDansLaBase(new Word_Translation("enracine", "engrained"));
		ecrireDansLaBase(new Word_Translation("warrior", "guerrier"));
		ecrireDansLaBase(new Word_Translation("Poulet", "Hush"));
		ecrireDansLaBase(new Word_Translation("Moi", "me"));
		String[] args = {"@en","@fr","*",""};
		MainActivity.printDebug(1,"Apres ecriture "); 
		modifierDansLaBase(new Word_Translation("336","Pwahahaha","Mwahaha",Word_Translation.ENGLISH,Word_Translation.FRENCH));
		MainActivity.printDebug(1,"Premier "); 
		List<Word_Translation> retour = lireDepuisLaBase(args, false, -1);

		if(retour !=null){
			for (Word_Translation mot : retour){
				mot.printWord();
			}
		}
		MainActivity.printDebug(1,"Deuxieme "); 
		String[] args1 = {"@en","@fr","","*"};
		retour = lireDepuisLaBase(args1, true, -1);

		if(retour !=null){
			for (Word_Translation mot : retour){
				mot.printWord();
			}
		}
		//resetTable();
		//dropTable();
	}*/

	public void resetTable(){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		MainActivity.printDebug(1,"Table reset"); 
		db.delete(FeedEntry.TABLE_NAME, null, null);
	}

	/** Deletes the table from the database*/
	@SuppressWarnings("unused")
	private void dropTable(){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		MainActivity.printDebug(1,"Table droped"); 
		try{
			db.execSQL("DROP TABLE " +FeedEntry.TABLE_NAME);
		}catch (SQLException e){
			MainActivity.printDebug(1,"Table droped "+e.getMessage()); 
		}
	}

	/**@return the language related to the name of the column in the the table  */
	public final static String column_to_language_Correspondance(String columnName){
		if(columnName.equalsIgnoreCase(FeedEntry.COLUMN_NAME_ENGLISH_WORD))
			return Word_Translation.ENGLISH;
		else if (columnName.equalsIgnoreCase(FeedEntry.COLUMN_NAME_FRENCH_WORD))
			return Word_Translation.FRENCH;
		else return null;
	}

	/**@return the name of the column in the the table related to the language */
	public final static String language_to_column_Correspondance(String LanguageName){
		if(LanguageName.equalsIgnoreCase( Word_Translation.ENGLISH))
			return FeedEntry.COLUMN_NAME_ENGLISH_WORD;
		else if (LanguageName.equalsIgnoreCase(Word_Translation.FRENCH))
			return FeedEntry.COLUMN_NAME_FRENCH_WORD;
		else return null;
	}


	@Override
	public void writeWord(Word_Translation word) {
		if (word.getWord().length()!=0)
			ecrireDansLaBase(word);
		MainActivity.printDebug(1, "write");
		showTable();
	}

	@Override
	public void showTable() {

		List<Word_Translation> res = getWordFromTable(new Word_Translation("%", "%"), Order.RANDOM, -1);
		for (Word_Translation toto : res){
			MainActivity.printDebug(1,"Id = "+toto.getId()+" 1-"+toto.getWord()+" 2-"+toto.getTraduction_of_word());
		}

	}
}
