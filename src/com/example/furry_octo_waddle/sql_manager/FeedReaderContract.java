package com.example.furry_octo_waddle.sql_manager;
import android.provider.BaseColumns;

/** Parts of code were found on https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow
*/
public final class FeedReaderContract {
	/** To prevent someone from accidentally instantiating the contract class,
	 *	give it an empty constructor.
	 */
	public FeedReaderContract() {}

	/** Inner class that defines the table contents */
	public static abstract class FeedEntry implements BaseColumns {
		public static final String TABLE_NAME = "translation_table";
		public static final String EXTENDED_TABLE_NAME = "extra_translation_table";
		public static final String COLUMN_NAME_ENGLISH_WORD = "english_word";
		public static final String COLUMN_NAME_FRENCH_WORD = "french_word";
		public static final String COLUMN_NAME_TIMESTAMP = "creation_time";
		public static final String COLUMN_TIMESTAMP_DEFAULT = " (datetime('now','localtime'))";
	}
}