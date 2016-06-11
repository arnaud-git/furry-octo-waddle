package com.example.furry_octo_waddle.sql_manager;

import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import com.example.furry_octo_waddle.sql_manager.FeedReaderContract.FeedEntry;

//src  : https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow

public class Controleur_bd implements BD_rw{
	private MainActivity ma;
	private FeedReaderDbHelper mDbHelper;
	

	


	public Controleur_bd(ActionBarActivity ba) {
		// TODO Auto-generated constructor stub
		mDbHelper = new FeedReaderDbHelper(ba);
		MainActivity.printDebug(1,"controleur cree");
	}



	public void ecrireDansLaBase(Word_Translation word){
		//Changement de getContext
		//mDbHelper = new FeedReaderDbHelper(ma.getApplicationContext());
		// Gets the data repository in write mode
		//MainActivity.printDebug(1,"Tentative ecrire base ");
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
			//MainActivity.printDebug(1, "S'arrete la");
		long newRowId = db.insertOrThrow(
		         FeedEntry.TABLE_NAME,
		         null,
		         values);
		}catch (SQLException e){
			MainActivity.printDebug(1, e.getMessage());
		}
	}
	public List<Word_Translation> lireDepuisLaBase(String[] args,boolean random,int nombre){
		return getWordFromTable(new Word_Translation(args[2],args[3], args[0], args[1]),random,nombre);
	}
	
	@Override
	public List<Word_Translation> getWordFromTable(Word_Translation word,boolean random,int nombre){
		List<Word_Translation> list = new ArrayList<Word_Translation>();
		try{
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		MainActivity.printDebug(1, "S'arrete la");
		String[] projection = {
			FeedEntry._ID,
		    language_to_column_Correspondance(word.getLanguage()),
		    language_to_column_Correspondance(word.getTargeted_language()),
		    FeedEntry.COLUMN_NAME_TIMESTAMP
		    };

		// Define 'where' part of query.
		//MainActivity.printDebug(1, "S'arrete la");
		String selection = null;
		String sel =null;
		if(word.getId()==0){
			if (word.getWord().compareToIgnoreCase("*")!=0){
				selection = language_to_column_Correspondance(word.getLanguage()) + " LIKE ? "  ;
				sel = word.getWord();
			}if (word.getTraduction_of_word().compareToIgnoreCase("*")!=0){
				if(selection != null){
					selection = selection + " AND "+ language_to_column_Correspondance(word.getTargeted_language()) + " LIKE ? "  ;
					//MainActivity.printDebug(1, "PAsse");
					sel = sel +"\\"+word.getTraduction_of_word();
				}
				else{
					selection = language_to_column_Correspondance(word.getTargeted_language()) + " LIKE ? "  ;
					sel =word.getTraduction_of_word();
				}
			}
		}else{
			selection = FeedEntry._ID + " LIKE ? "  ;
			sel = String.valueOf(word.getId());
		}
		//MainActivity.printDebug(1, "S'arrete la");
				
		// How you want the results sorted in the resulting Cursor
		String sortOrder =
		    //FeedEntry.COLUMN_NAME_UPDATED + " DESC";
				null;
		if(random)
			sortOrder = " RANDOM() ";
		
		String nb = null;
		if(nombre >=0)
			nb =String.valueOf(nombre);	
		
		// Specify arguments in placeholder order.
		//MainActivity.printDebug(1, "Avant Split : "+ sel +" / "+selection); 
		String[] selectionArgs;
		if(sel!=null){
			selectionArgs = sel.split("\\\\");
		}
		else 
			selectionArgs = null;
		//MainActivity.printDebug(1, "apres Split"); 
		
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
		//MainActivity.printDebug(1, "S'arrete la");
		if(c.getCount()>0){
			//MainActivity.printDebug(1,"Recuperation de  " +c.getCount() +" trad.");
			while(c.moveToNext()){
				list.add(new Word_Translation(c.getString(1), c.getString(2)
					//Si d'autres colonnes pour d'autres langues
					,column_to_language_Correspondance(c.getColumnName(1))
					,column_to_language_Correspondance(c.getColumnName(2))
					,c.getString(0)
					,c.getString(3)
					));
			}
		}else{
			MainActivity.printDebug(1,"Rien dans la table "); 
		}
		}
		catch(Exception e){
			MainActivity.printDebug(1,e.getMessage()); 
		}
		return list;
	}
	
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
		int count = db.update(
			FeedEntry.TABLE_NAME,
		    values,
		    selection,
		    selectionArgs);
		}catch(Exception e){
			MainActivity.printDebug(1, e.getMessage());
		}
	}
	
	public void supprimerDeLaBase(Word_Translation word){
		
		//mDbHelper = new FeedReaderDbHelper(ma.getApplicationContext());
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		try{
		if(word.getId()==0){
			// Define 'where' part of query.
			String selection = language_to_column_Correspondance(word.getLanguage()) + " LIKE ?";
			String deletedWord = word.getWord();
			
			// Specify arguments in placeholder order.
			MainActivity.printDebug(1,"Boom"); 
			MainActivity.printDebug(1,String.valueOf(word.getTraduction_of_word().length())); 
			MainActivity.printDebug(1,String.valueOf(word.getTargeted_language().length())); 
			if(word.getTraduction_of_word().length()>0 && word.getTargeted_language().length()>0){
				selection = selection + " && "+language_to_column_Correspondance(word.getTargeted_language())+" LIKE ?";
				MainActivity.printDebug(1,"Cas 1"); 
				// Issue SQL statement.
				String[] selectionArgs = { deletedWord, word.getTraduction_of_word()};
				db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);
			}else{
				MainActivity.printDebug(1,"Cas 2"); 
				String[] selectionArgs = { deletedWord};
				
				// Issue SQL statement.
				db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);
			}	
		} else {
			String selection = FeedEntry._ID + " LIKE ?";
			String[] selectionArgs = {String.valueOf(word.getId())};
			db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);
		}
		}catch(Exception e){
			MainActivity.printDebug(1,e.getMessage()); 
		}
		MainActivity.printDebug(1,"fin"); 
	}

	@Override
	public List<Word_Translation> getRandomEnglishWordNotTranslated(int nombre) {
		String[] var = {"@fr","@en","","*"};
		List<Word_Translation> retour = lireDepuisLaBase(var, true, nombre);
		return retour;
	}

	@Override
	public List<Word_Translation> getRandomFrenchWordNotTranslated(int nombre) {
		String[] var = {"@fr","@en","*",""};
		List<Word_Translation> retour = lireDepuisLaBase(var, true, nombre);
		return retour;
	}

	@Override
	public List<Word_Translation> getRandomPairOfWords(int number) {
		String[] var = {"@fr","@en","*","*"};
		List<Word_Translation> retour = lireDepuisLaBase(var, true, number);
		return retour;
	}

	@Override
	public void writeEnglishWordWOTranslation(String englishWord) {
		// TODO Auto-generated method stub
		Word_Translation var = new Word_Translation(null,englishWord);
		ecrireDansLaBase(var);
	}

	@Override
	public void writeFrenchWordWOTranslation(String frenchWord) {
		// TODO Auto-generated method stub
		Word_Translation var = new Word_Translation(frenchWord,null);
		ecrireDansLaBase(var);
	}

	@Override
	public void writePairOfWords(String frenchWord, String englishWord) {
		// TODO Auto-generated method stub
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
		Word_Translation var = new Word_Translation(null,null,null,null,String.valueOf(index));
		supprimerDeLaBase(var);
	}
	
	@Override
	public void deleteWord(Word_Translation word){
		supprimerDeLaBase(word);
	}
	
	@Override
	public void modifyWordbyId(Word_Translation word){
		modifierDansLaBase(word);
	}
	
	public void test(){
		ecrireDansLaBase(new Word_Translation("salut", ""));
		ecrireDansLaBase(new Word_Translation("Bojour", "Hello"));
		ecrireDansLaBase(new Word_Translation("Hush", "Frite"));
		ecrireDansLaBase(new Word_Translation("enracine", "engrained"));
		ecrireDansLaBase(new Word_Translation("warrior", "guerrier"));
		ecrireDansLaBase(new Word_Translation("Poulet", "Hush"));
		ecrireDansLaBase(new Word_Translation("Moi", "me"));
		String[] args = {"@en","@fr","*",""};
		MainActivity.printDebug(1,"Apres ecriture "); 
		modifierDansLaBase(new Word_Translation("Pwahahaha","Mwahaha",Word_Translation.ENGLISH,Word_Translation.FRENCH,"336"));
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
	}
	
	public void resetTable(){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		MainActivity.printDebug(1,"Table reset"); 
		db.delete(FeedEntry.TABLE_NAME, null, null);
	}
	
	private void dropTable(){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		MainActivity.printDebug(1,"Table droped"); 
		try{
			db.execSQL("DROP TABLE " +FeedEntry.TABLE_NAME);
			}catch (SQLException e){
				MainActivity.printDebug(1,"Table droped "+e.getMessage()); 
			}
	}
	
	public final static String column_to_language_Correspondance(String columnName){
		if(columnName.equalsIgnoreCase(FeedEntry.COLUMN_NAME_ENGLISH_WORD))
				return Word_Translation.ENGLISH;
		else if (columnName.equalsIgnoreCase(FeedEntry.COLUMN_NAME_FRENCH_WORD))
				return Word_Translation.FRENCH;
		else return null;
	}
	
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
	}
}
