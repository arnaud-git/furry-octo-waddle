package com.example.furry_octo_waddle.sql_manager;

/** Parts of code were found on https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow
 */
import android.content.Context;
import android.database.sqlite.*;
import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.FeedReaderContract.FeedEntry;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
	private static final String TEXT_TYPE = " TEXT";
	private static final String INSENSITIVE_CASE = " COLLATE NOCASE";
	private static final String COMMA_SEP = ",";
	private static final String DEFAULT = " DEFAULT";
	private static final String SQL_CREATE_EXTRA_ENTRIES =
			"CREATE TABLE IF NOT EXISTS  " + FeedEntry.EXTENDED_TABLE_NAME + " (" +
					FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_NAME_WORD + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
					FeedEntry.COLUMN_NAME_WORD_LANG + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
					FeedEntry.COLUMN_NAME_WORD_ROMANIZATION + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
					FeedEntry.COLUMN_NAME_WORD_PRONUNCIATION + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
					FeedEntry.COLUMN_NAME_TRANS_LANG + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
					FeedEntry.COLUMN_NAME_TRANSLATION + TEXT_TYPE + INSENSITIVE_CASE +
					COMMA_SEP + FeedEntry.COLUMN_NAME_TIMESTAMP + TEXT_TYPE +
					DEFAULT + FeedEntry.COLUMN_TIMESTAMP_DEFAULT+
					" )";

	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE IF NOT EXISTS  " + FeedEntry.TABLE_NAME + " (" +
					FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_NAME_FRENCH_WORD + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
					FeedEntry.COLUMN_NAME_ENGLISH_WORD + TEXT_TYPE + INSENSITIVE_CASE +
					COMMA_SEP + FeedEntry.COLUMN_NAME_TIMESTAMP + TEXT_TYPE +
					DEFAULT + FeedEntry.COLUMN_TIMESTAMP_DEFAULT+
					" )";

	private static final String SQL_CREATE_LANG_ENTRIES =
			"CREATE TABLE IF NOT EXISTS  " + FeedEntry.LANG_TABLE_NAME + " (" +
					FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_NAME_LANGUAGE_CODE + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
					FeedEntry.COLUMN_NAME_LANGUAGE_NAME+ TEXT_TYPE + INSENSITIVE_CASE +
					" )";

	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

	private static final String SQL_DELETE_EXTRA_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.EXTENDED_TABLE_NAME;

	private static final String SQL_DELETE_LANG_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.LANG_TABLE_NAME;


	/** If you change the database schema, you must increment the database version.*/
	public static final int DATABASE_VERSION = 10;
	public static final String DATABASE_NAME = "FeedReader.db";




	public FeedReaderDbHelper(Context context) {   	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		MainActivity.printDebug(1, "Tentative construction FeedReader");
	}

	public void onCreate(SQLiteDatabase db) {
		MainActivity.printDebug(1, "Tentative creation table");
		db.execSQL(SQL_CREATE_ENTRIES);
		try{
			db.execSQL(SQL_CREATE_EXTRA_ENTRIES);
			db.execSQL(SQL_CREATE_LANG_ENTRIES);
		}catch(Exception e){
			MainActivity.printDebug(1, e.getMessage());
		}
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
		// to simply to discard the data and start over
		MainActivity.printDebug(1, "Upgrade "+oldVersion+"  ->   "+newVersion);
		if(oldVersion<10){
			db.execSQL(SQL_CREATE_ENTRIES);
			db.execSQL(SQL_CREATE_EXTRA_ENTRIES);
			MainActivity.updated =false;
			//fillExtraTable(db);
		}
		onCreate(db);
	}
	/*private void fillExtraTable(SQLiteDatabase db) {
    	db.execSQL("UPDATE "+FeedEntry.EXTENDED_TABLE_NAME+"  SET "+
    			FeedEntry.COLUMN_NAME_WORD +" = " +FeedEntry.TABLE_NAME+"."+FeedEntry.COLUMN_NAME_FRENCH_WORD+COMMA_SEP +
    		    FeedEntry.COLUMN_NAME_WORD_LANG + " = '" +Word_Translation.ENGLISH+"'"+COMMA_SEP +
    		    FeedEntry.COLUMN_NAME_WORD_ROMANIZATION + " = '' " +COMMA_SEP +
    		    FeedEntry.COLUMN_NAME_WORD_PRONUNCIATION +" = '' "+COMMA_SEP +
    		    FeedEntry.COLUMN_NAME_TRANS_LANG +" = '" + Word_Translation.FRENCH+"'"+COMMA_SEP +
    		    FeedEntry.COLUMN_NAME_TRANSLATION + " = " +FeedEntry.TABLE_NAME+"."+FeedEntry.COLUMN_NAME_ENGLISH_WORD+COMMA_SEP +
    		    FeedEntry.COLUMN_NAME_TIMESTAMP + "= " +FeedEntry.TABLE_NAME+"."+FeedEntry.COLUMN_NAME_TIMESTAMP+" "+
    		    "WHERE "+FeedEntry._ID+" = "+FeedEntry.TABLE_NAME+"."+FeedEntry._ID);	
	}*/

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MainActivity.printDebug(1, "Downgrade "+oldVersion+"  ->   "+newVersion);
		if(newVersion<10){
			db.execSQL(SQL_DELETE_EXTRA_ENTRIES);
			db.execSQL(SQL_DELETE_LANG_ENTRIES);
		}
	}
}
