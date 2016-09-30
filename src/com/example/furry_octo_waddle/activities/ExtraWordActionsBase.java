package com.example.furry_octo_waddle.activities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Language;
import com.example.furry_octo_waddle.sql_manager.Language.Languages_List;
import com.example.furry_octo_waddle.sql_manager.alphabet.Languages_ISO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExtraWordActionsBase extends WordActions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean EXTRA = true;
	protected static Language MY_LANGUAGE = null;
	protected static Language TRANSLATED_LANGUAGE = null;
	protected static Languages_List MY_LANGUAGES = new Languages_List();
	//For now  : TODO accept multiple languages
	protected static Languages_List LANGUAGES_DISPLAYED = new Languages_List();

	
	protected Language lang_obj = null;
	protected String[] args = {"","","","","",""};
	
	public ExtraWordActionsBase(BaseActivity ba){
		super(ba);
		args[0] = LANGUAGES_DISPLAYED.getCode();
		args[4] = MY_LANGUAGE.getCode(); 
		word_obj =  new Extra_Word_Translation(args);
		//this.ba = ba;
	}



	protected boolean save_current_word(){
		String word = editWord.getText().toString();
		String wordTrad = editWordTrans.getText().toString();
		
		
		if(Word_Translation.formatString(word).length()!=0 || Word_Translation.formatString(wordTrad).length()!=0) {
			//create the new object with the typed words
			//save the new object in the database
			// Will be better to put the languages in the inputs
			args[1] = word;
			args[5] = wordTrad;
			word_obj = new Extra_Word_Translation(args);
	
			// TODO cbd one function for thes cases
			if(word_obj.getId()>0)
				MainActivity.cbd.modifyWordbyId(word_obj);
			else
				MainActivity.cbd.writeWord(word_obj);
			return true;
		}return false;
	}
	
	
	protected void setCurrentWord(Word_Translation word){
		args = ((Extra_Word_Translation)word).getArgs();
		word_obj=word;
		changeLanguage(MainActivity.cbd.getLanguage(new Language(word_obj.getLanguage()),1).get(0));
	}

	@Override
	protected Word_Translation query(String word, String transWord){
		String[] args={LANGUAGES_DISPLAYED.getCode(),word, "%","%",MY_LANGUAGES.getCode(),transWord };
		return new Extra_Word_Translation(args);
	}
	
	@Override
	public Word_Translation query(int int1) {
		String[] args={TRANSLATED_LANGUAGE.getCode(),"%", "%","%",MY_LANGUAGE.getCode(),"%"};
		return new Extra_Word_Translation(String.valueOf(int1),args);
	}
	

	protected void save_current_language(){
		if(lang_obj.getId() ==0 )
			MainActivity.cbd.writeLanguage(lang_obj);
			//C'est donc une nouvelle langue
		
		else {
			//REcuperer dqns les edittext les nouvelles valeurs
			// Faire toutes les modif dans la base de donnees pour avoir la bonne langue
		}
	}
	
	protected Language query_l(int id){
		return new Language(String.valueOf(id),"%%","%%");
	}
	
	protected Language query_l(String regrex_lang_name, String regrex_lang_code){
		return new Language(regrex_lang_name,regrex_lang_code);
	}
	
	protected List<Language> getLanguagesFromTable(String regrex_lang_name, String regrex_lang_code, int nombre){
		return MainActivity.cbd.getLanguage(query_l(regrex_lang_name,regrex_lang_code), nombre);
	}
	
	protected Language getLanguageFromTable(Language language){
		List<Language> res =  MainActivity.cbd.getLanguage(query_l(1), -1);
		if(res.size()==1)
			return res.get(0);
		else{ 
			MainActivity.printDebug(1, "Renvoie liste trop longue");
			return nolangindb(); 
		}
	}
	
	private Language nolangindb() {	
		return new Language("No language in db");
	}

	protected  String checkforcodeLanguage(Language lang){
		String code = lang.getCode();
		String base = code.substring(code.length()-2, code.length());
		int id = lang.getId();
		for (int compteur = 0; compteur < lang.getName().length();compteur++){
			if(!codelanguagealreadyused(code,id))
				return code;
			else{
				code = base + lang.getName().charAt(0) + lang.getName().charAt(compteur);
			}
		}
		Random rand = new Random();
		while(codelanguagealreadyused(code,id)){
			code = base + (char)(rand.nextInt(26)+97)+ (char)(rand.nextInt(26)+97);
		}
		//Peut etre le changer directement....
		return code;
	}
	
	protected  boolean codelanguagealreadyused(String code,int id){
		List<Language> list = MainActivity.cbd.getLanguage(query_l("%%",code), -1);
		switch(list.size()){
		case 0 : return false;
		case 1 : if(list.get(0).getId()==id)
			return false;
		default :return true;
		}
	}
	protected void delete_current_language() {
		MainActivity.cbd.deleteLanguage(lang_obj);	
	}
	
	protected Word_Translation nowordindb() {
		args[1] = "No word in the database";
		args[5] = "";
		return new Extra_Word_Translation(args);
	}

	public static void changeMyLanguage(Language langue){
		MY_LANGUAGE = langue;
	}
	
	public static void changeLanguage(Language langue){
		TRANSLATED_LANGUAGE = langue;
	}
	
	public static void displayAnotherLanguage(Language langue){
		LANGUAGES_DISPLAYED.add(langue);
	}
	
	public static void removeLanguage(Language langue){
		LANGUAGES_DISPLAYED.remove(langue);
	}
	
	public static boolean isLanguageDisplayed(Language langue){
		return LANGUAGES_DISPLAYED.isDisplayed(langue);
	}
	
	public static boolean containsLanguage(Language langue){
		return LANGUAGES_DISPLAYED.contains(langue);
	}

	public static boolean isTLanguage(Language language) {
		return TRANSLATED_LANGUAGE.equals(language);
	}
	
	public static boolean isMyLanguage(Language language) {
		return MY_LANGUAGE.equals(language);
	}



	public static boolean isMyLanguageDisplayed(Language language) {
		return MY_LANGUAGES.isDisplayed(language);
	}



	public static boolean containsMyLanguage(Language language) {
		return MY_LANGUAGES.contains(language);
	}



	public static void removeMyLanguage(Language tag) {
		MY_LANGUAGES.remove(tag);
		
	}
	public static void displayAnotherMyLanguage(Language langue){
		MY_LANGUAGES.add(langue);
	}
	
}
