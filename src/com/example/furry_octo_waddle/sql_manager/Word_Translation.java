package com.example.furry_octo_waddle.sql_manager;

import com.example.furry_octo_waddle.activities.MainActivity;

import android.util.Log;

public class Word_Translation {
	/** identifiant in the database*/
	public static final String WORD_ID = "word_id";
	private int id = 0 ;

	/** 1- The word 
	 * 
	 * OR
	 * 
	 * 2- Pattern Expression for query
	 * The percent sign represents zero, one, or multiple numbers or characters. 
	 * The underscore represents a single number or character. 
	 * The tild at the beginning of the word indicates to make the query on words NOT LIKE the pattern expression
	 * (eg ~ad% -> NOT LIKE ad%) 
	 * These symbols can be used in combinations.
	 * */
	private String word;

	private String traduction_of_word;

	/**Language of word*/
	private String language;

	/**Language of traduction of the word*/
	private String targeted_language;

	//TODO Peut-etre les changer en int ou avec un enum
	//The different languages 
	public static final String ENGLISH = "@en";
	public static final String FRENCH = "@fr";

	/**Last modification of the word*/
	private String last_modification_time;

	public Word_Translation(String w,String t,String l, String t_l){
		setWord(w);
		setTraduction(t);
		setLanguage(l);
		setTargeted_Language(t_l);
		checkfornull();
	}

	public Word_Translation(int l,String w,String t){
		setWord(w);
		setTraduction(t);
		setId(l);
		setLanguage(FRENCH);
		setTargeted_Language(ENGLISH);
		checkfornull();
	}

	public Word_Translation(String index,String w,String t,String l, String t_l,String time){
		setWord(w);
		setTraduction(t);
		setLanguage(l);
		setTargeted_Language(t_l);
		setId(Integer.parseInt(index));
		setTime(time);
		checkfornull();
	}


	public Word_Translation(String index,String w,String t,String l, String t_l){
		setWord(w);
		setTraduction(t);
		setLanguage(l);
		setTargeted_Language(t_l);
		setId(Integer.parseInt(index));
		checkfornull();

	}
	public Word_Translation(String frenchWord,String englishWord){
		setWord(frenchWord);
		setTraduction(englishWord);
		setLanguage(FRENCH);
		setTargeted_Language(ENGLISH);
		checkfornull();
	}

	private void setTargeted_Language(String t_l) {
		if(t_l!=null)
			this.targeted_language =t_l;
		else
			this.targeted_language = "";
	}

	private void setLanguage(String l) {
		if(l!=null)
			this.language =l;
		else
			this.language="";
	}

	private void setTraduction(String t) {
		if (t!=null)
			this.traduction_of_word = formatString(t);
		else 
			this.traduction_of_word = "";

	}

	private void setWord(String w) {
		if(w!=null)
			this.word =formatString(w);
		else 
			this.word = "";

	}

	/**@return String with only at most one consecutive escape character*/
	public static final String formatString(String word){
		String computeWord = word ; 
		computeWord = computeWord.replaceAll("\\s{2,}", " ");
		if(computeWord.startsWith(" ")){
			computeWord = computeWord.substring(1);
		}
		if(computeWord.endsWith(" ")){
			computeWord = computeWord.substring(0, computeWord.length()-1);
		}
		
		return computeWord;
	}

	public static final String escapePunctuation(String word){
		String[] punct11 ={";",":","\\x21","\\x3f","\\x2d"};
		String[] punct10 = {"\\x28"};
		String[] punct01 ={",",".","\\x29"};
		String[] punct00 ={};
		String res = word;
		for(String toto : punct11){
			res = res.replaceAll(" *"+toto+" *", " "+toto+" ");
		}for(String toto : punct10){
			res = res.replaceAll(" *"+toto+" *", " "+toto);
		}
		for(String toto : punct01){
			res = res.replaceAll(" *"+toto+" *", toto+" ");
		}
		for(String toto : punct00){
			res = res.replaceAll(" *"+toto+" *", toto);
		}
		return res;
	}

	public final String getWord() {
		return word;
	}

	public final String getTraduction_of_word() {
		return traduction_of_word;
	}

	public final String getLanguage() {
		return language;
	}

	public final String getTargeted_language() {
		return targeted_language;
	}


	public void setId(int id){
		this.id=id;
	}
	public void setTime(String time){
		this.last_modification_time=time;
	}
	public int getId(){
		return id;
	}

	public void printWord(){
		Log.d(MainActivity.APPLICATION_TAG_NAME, "ID : "+id+" Word "+language+" : " +
				word+" -> "+targeted_language+ " : " + traduction_of_word+"\t"+last_modification_time);
	}

	private void checkfornull(){
		if(word.length()==0 && traduction_of_word.length()>0){
			word = traduction_of_word;
			traduction_of_word = "";
			String tempo_lang = language;
			language = targeted_language;
			targeted_language = tempo_lang;
		}
	}

	public static final boolean matches(String toto, String op){
		String word1 = formatString(toto);
		String word2 = formatString(op);
		MainActivity.printDebug(1, escapePunctuation(word1));
		MainActivity.printDebug(1, escapePunctuation(word2));
		return word1.equalsIgnoreCase(word2);
	}
}
