package com.example.furry_octo_waddle.sql_manager;

//src  : https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.FeedReaderContract.FeedEntry;



public class FeedReaderDbHelper extends SQLiteOpenHelper {
    
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String INSENSITIVE_CASE = " COLLATE NOCASE";
	private static final String COMMA_SEP = ",";
	private static final String DEFAULT = " DEFAULT";
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE IF NOT EXISTS  " + FeedEntry.TABLE_NAME + " (" +
	    FeedEntry._ID + " INTEGER PRIMARY KEY," +
	    FeedEntry.COLUMN_NAME_FRENCH_WORD + TEXT_TYPE + INSENSITIVE_CASE + COMMA_SEP +
	    FeedEntry.COLUMN_NAME_ENGLISH_WORD + TEXT_TYPE + INSENSITIVE_CASE +
	    COMMA_SEP + FeedEntry.COLUMN_NAME_TIMESTAMP + TEXT_TYPE +
	     DEFAULT + FeedEntry.COLUMN_TIMESTAMP_DEFAULT+
	    " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
	
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "FeedReader.db";
    
    
    
    
    public FeedReaderDbHelper(Context context) {   	
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        MainActivity.printDebug(1, "Tentative construction FeedReader");
    }
    
    public void onCreate(SQLiteDatabase db) {
    	MainActivity.printDebug(1, "Tentative creation table");
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
    	MainActivity.printDebug(1, "Tentative suppresion table");
    	//try{
        db.execSQL(SQL_DELETE_ENTRIES);
        //}
    	//catch(SQLException e){
    	//	MainActivity.printDebug(1, e.getMessage());
    	//}
    	
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
