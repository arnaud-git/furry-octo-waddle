package com.example.furry_octo_waddle.sql_manager.alphabet;

import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.activities.MainActivity;
import com.example.furry_octo_waddle.sql_manager.Language;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class Languages_ISO {

	//TODO Peut-etre les changer en int ou avec un enum
	//The different languages 
	public static final String ENGLISH = "@en";
	public static final String FRENCH = "@fr";
	public static final String JAPANESE = "@ja";
	public static final String HANGEUL = "@kr";
	public static final String GREEk = "@gr";
	public static final String HANGEUL_ALPHA = "&";
	public static final String HIRAGANA_ALPHA = "hi&";
	public static final String KATAKANA_ALPHA = "ka&";
	public static final String ANCIENT_GREEK_ALPHA = "&";
	
	private static final List<Language> list = new ArrayList<Language>();

	public static void save_Languages(){
		
	list.add(new Language(ENGLISH,"ENGLISH"));  											//0
		list.add(new Language(FRENCH,"FRENCH"));											//1
		list.add(new Language(JAPANESE,"JAPANESE"));										//2
		list.add(new Language(list.get(2),HIRAGANA_ALPHA,"HIRAGANA_ALPHA"));				//3
		list.add(new Language(list.get(2),KATAKANA_ALPHA,"KATAKANA_ALPHA"));				//4
		list.add(new Language(HANGEUL,"HANGEUL"));											//5
		list.add(new Language(list.get(5),HANGEUL_ALPHA,"HANGEUL_ALPHA"));					//6
		list.add(new Language(GREEk,"GREEk"));												//7
		list.add(new Language(list.get(7),ANCIENT_GREEK_ALPHA,"ANCIENT_GREEK_ALPHA"));		//8

		for(Language lang : list){
			MainActivity.cbd.writeLanguage(lang);
		}
	}
}
