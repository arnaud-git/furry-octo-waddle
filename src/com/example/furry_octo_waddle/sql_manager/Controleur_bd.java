package com.example.furry_octo_waddle.sql_manager;

import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.support.v7.app.ActionBarActivity;
import android.text.style.UpdateAppearance;

import com.example.furry_octo_waddle.sql_manager.FeedReaderContract.FeedEntry;
import com.example.furry_octo_waddle.sql_manager.Word_Translation.Extra;
import com.example.furry_octo_waddle.sql_manager.alphabet.Greek_alphabet;
import com.example.furry_octo_waddle.sql_manager.alphabet.Hangeul_alphabet;
import com.example.furry_octo_waddle.sql_manager.alphabet.Hiragana_alphabet;
import com.example.furry_octo_waddle.sql_manager.alphabet.Katakana_alphabet;

/** Parts of code were found on https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow
 */

public class Controleur_bd implements BD_rw{
	private FeedReaderDbHelper mDbHelper;
	private boolean updated = true;

	public Controleur_bd(ActionBarActivity ba) {
		mDbHelper = new FeedReaderDbHelper(ba);
		MainActivity.printDebug(1,"controleur cree");
	}
	
	public void setUpdateVariable(boolean updated){
		this.updated = updated;
	}

	/**@see writeWord(Word_Translation word)*/
	public void ecrireDansLaBase(Word_Translation word){
		// Gets the data repository in write mode
		SQLiteDatabase db = null;
		try{
			db = mDbHelper.getReadableDatabase();}
		catch (Exception e){
			MainActivity.printDebug(1, e.getMessage());
		}
		//MainActivity.printDebug(1, "S'arrete la");

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		String[] args = projectionForQuery(word);
		String[] args_word = word.getArgs();
		for(int index = 1; index < args.length-1;index ++)
			values.put(args[index], args_word[index]);

		String table = null;
		if(word.getType()==Extra.NORMAL)
			table = FeedEntry.TABLE_NAME;
		if(word.getType()==Extra.EXTRA)
			table = FeedEntry.EXTENDED_TABLE_NAME;

		// Insert the new row, returning the primary key value of the new row
		try{

			//TODO Remove ***OrThrow
			long newRowId = db.insertOrThrow(
					table,
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
		return getWordFromTable(new Word_Translation(args[0],args[2], args[1], args[3]),random,nombre);
	}

	@Override	
	public List<Word_Translation> getWordFromTable(Word_Translation word,Order random,int nombre){
		List<Word_Translation> list = new ArrayList<Word_Translation>();
		MainActivity.printDebug(1,"GET");
		word.printWord();
		try{
			SQLiteDatabase db = mDbHelper.getReadableDatabase();


			//Define the table
			String table = null;
			if(word.getType()==Extra.NORMAL)
				table = FeedEntry.TABLE_NAME;
			if(word.getType()==Extra.EXTRA)
				table = FeedEntry.EXTENDED_TABLE_NAME;

			// Define a projection that specifies which columns from the database
			// you will actually use after this query.
			//MainActivity.printDebug(1, "ICI");
			String[] projection =  projectionForQuery(word);

			// Define 'where' part of query.
			String selection = "";
			//String sel =null;
			List<String> sel = new ArrayList<String>();
			String mot = word.getWord(), trad = word.getTraduction_of_word() ;
			if(word.getId()==0){
				String[] arguments = word.getArgs();
				for(int index = 1;index<arguments.length-1;index++){
					selection = selection + projection[index];
					if (arguments[index].startsWith("~")){
						selection = selection + " NOT ";
						arguments[index]=arguments[index].substring(1);
					}
					sel.add(arguments[index]);
					selection = selection +" LIKE ? ";
					if(index+1<arguments.length-1)
						selection= selection +" AND ";
				}
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
				if(word.getType()==Extra.NORMAL)
					sortOrder = " "+language_to_column_Correspondance(word.getLanguage())+ " ASC ";
				if(word.getType()==Extra.EXTRA)
					sortOrder = " "+FeedEntry.COLUMN_NAME_WORD+ " ASC ";
				break;
			case STAMP_DESC :
				sortOrder = " "+FeedEntry.COLUMN_NAME_TIMESTAMP+ " DESC ";
				break;
			case NULL : 
				sortOrder = null;
				break;
			case ID_ASC:
				sortOrder = " "+FeedEntry._ID+ " ASC ";
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
					table,  // The table to query
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
					if(word.getType()==Extra.NORMAL)
						list.add(new Word_Translation(c.getString(0),
								//Si d'autres colonnes pour d'autres langues
								column_to_language_Correspondance(c.getColumnName(1)),
								 c.getString(1),
								column_to_language_Correspondance(c.getColumnName(2)),
								c.getString(2),
								c.getString(3)
								));
					else
						if(word.getType()==Extra.EXTRA){
							String[] args= {c.getString(1),
									c.getString(2),
									c.getString(3),
									c.getString(4),
									c.getString(5),
									c.getString(6)
							};
							list.add(new Extra_Word_Translation(c.getString(0),args,c.getString(7)));
						}
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
		
		String table = null;
		if(word.getType()==Extra.NORMAL)
			table = FeedEntry.TABLE_NAME;
		if(word.getType()==Extra.EXTRA)
			table = FeedEntry.EXTENDED_TABLE_NAME;
		
		// New value for one column
		ContentValues values = new ContentValues();
		String[] args = projectionForQuery(word);
		String[] args_word = word.getArgs();
		for(int index = 1; index < args.length-1;index ++)
			values.put(args[index], args_word[index]);
		values.put(FeedEntry.COLUMN_NAME_TIMESTAMP, FeedEntry.COLUMN_TIMESTAMP_DEFAULT);

		// Which row to update, based on the ID
		String selection = FeedEntry._ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(word.getId()) };
		try{
			//reutrn the number of row affected
			int count = db.update(
					table,
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
			if(word.getId()==0){
				String[] arguments = word.getArgs();
				String[] projection = projectionForQuery(word);
				for(int index = 1;index<arguments.length-1;index++){
					selection = selection + projection[index];
					if (arguments[index].startsWith("~")){
						selection = selection + " NOT ";
						arguments[index]=arguments[index].substring(1);
					}
					sel.add(arguments[index]);
					selection = selection +" LIKE ? ";
					if(index+1<arguments.length-1)
						selection= selection +" AND ";
				}
			}
			else{
				selection = FeedEntry._ID + " LIKE ? "  ;
				sel.add(String.valueOf(word.getId()));
			}
			for (String toto :sel)	
				MainActivity.printDebug(1, toto);
			String[] selectionArgs = (String[]) sel.toArray(new String[sel.size()]);

			String table = null;
			if(word.getType()==Extra.NORMAL)
				table = FeedEntry.TABLE_NAME;
			if(word.getType()==Extra.EXTRA)
				table = FeedEntry.EXTENDED_TABLE_NAME;
			db.delete(table, selection, selectionArgs);

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
		Word_Translation var = new Word_Translation(Word_Translation.ENGLISH,englishWord,null,null);
		//var.printWord();
		supprimerDeLaBase(var);
	}

	@Override
	public void deleteWordbyIndex(int index, Extra type){
		Word_Translation var = new Word_Translation(String.valueOf(index),null,null,null,null);
		var.setType(type);
		supprimerDeLaBase(var);
	}

	@Override
	public void deleteWord(Word_Translation word){
		word.printWord();
		MainActivity.printDebug(1, "DELETE");
		supprimerDeLaBase(word);
		//showTable();
	}

	@Override
	public void modifyWordbyId(Word_Translation word){
		//TODO Suppress 4 lines below but modifierDans****
		word.printWord();
		modifierDansLaBase(word);
		MainActivity.printDebug(1, "MODIFY");
		//showTable();
	}

	/*public void test(){
		resetTable(Extra.EXTRA);
		String args[] = {"@en","yoyo","prout","iferi","@fr","ezidnezoinenz"};
		ecrireDansLaBase(new Extra_Word_Translation(args));
		ecrireDansLaBase(new Extra_Word_Translation(args));
		ecrireDansLaBase(new Extra_Word_Translation(args));
		ecrireDansLaBase(new Extra_Word_Translation(args));
		ecrireDansLaBase(new Extra_Word_Translation(args));
		ecrireDansLaBase(new Extra_Word_Translation(args));
		ecrireDansLaBase(new Extra_Word_Translation(args));
		ecrireDansLaBase(new Extra_Word_Translation(args));
		/*ecrireDansLaBase(new Word_Translation("Bojour", "Hello"));
		ecrireDansLaBase(new Word_Translation("Hush", "Frite"));
		ecrireDansLaBase(new Word_Translation("enracine", "engrained"));
		ecrireDansLaBase(new Word_Translation("warrior", "guerrier"));
		ecrireDansLaBase(new Word_Translation("Poulet", "Hush"));
		ecrireDansLaBase(new Word_Translation("Moi", "me"));
		//showTable();
		MainActivity.printDebug(1,"Apres ecriture "); 
		modifierDansLaBase(new Word_Translation("336","Pwahahaha","Mwahaha",Word_Translation.ENGLISH,Word_Translation.FRENCH));
		MainActivity.printDebug(1,"Premier "); 
		List<Word_Translation> retour = lireDepuisLaBase(args, false, -1);

		//resetTable();
		//dropTable();
	}*/

	public void resetTable(Extra extra){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		MainActivity.printDebug(1,"Table reset"); 
		if(extra==Extra.NORMAL)
			db.delete(FeedEntry.TABLE_NAME, null, null);
		if(extra==Extra.EXTRA){
			db.delete(FeedEntry.EXTENDED_TABLE_NAME, null, null);
			update9();
		}
		
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
		//MainActivity.printDebug(1, "write");
		//showTable();
	}

	@Override
	public void showTable() {
		MainActivity.printDebug(1,"Table:");
		List<Word_Translation> res = getWordFromTable(new Word_Translation("%", "%"), Order.RANDOM, -1);
		for (Word_Translation toto : res){
			MainActivity.printDebug(1,"Id = "+toto.getId()+" 1-"+toto.getWord()+" 2-"+toto.getTraduction_of_word());
		}
		
		MainActivity.printDebug(1,"EXtended_Table:");
		String[] args= {"%","%","%","%","%","%"};
		res = getWordFromTable(new Extra_Word_Translation(args), Order.LANGUAGE_ASC, -1);
		for (Word_Translation toto : res){
			toto.printWord();
		}

	}

	private String[] projectionForQuery(Word_Translation word){
		if(word.getType()==Extra.NORMAL){
			String[] res = {
					FeedEntry._ID,
					language_to_column_Correspondance(word.getLanguage()),
					language_to_column_Correspondance(word.getTargeted_language()),
					FeedEntry.COLUMN_NAME_TIMESTAMP
			};
			return res;
		}else{
			if(word.getType()==Extra.EXTRA){
				return FeedEntry.getColumns(Extra.EXTRA);
			}
		}
		return null;
	}
	
	protected void update9(){
		MainActivity.printDebug(1,"Update 9");
		List<Word_Translation> list = getWordFromTable(new Word_Translation("%","%"), Order.ID_ASC, -1);
		for(Word_Translation word : list){
			writeWord(new Extra_Word_Translation(word));
		}
		Greek_alphabet.save_Greek_alphabet();
		Hangeul_alphabet.save_Hangeul_alphabet();
		Hiragana_alphabet.save_Hiragana_alphabet();
		Katakana_alphabet.save_Katakana_alphabet();
	} 
}
