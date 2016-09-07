package com.example.furry_octo_waddle.activities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	protected String[] args = {"","","","","",""};
	protected static Language MY_LANGUAGE = null;
	protected static Language TRANSLATED_LANGUAGE = null;
	
	//For now  : TODO accept multiple languages
	protected static Languages_List LANGUAGES_DISPLAYED = new Languages_List();

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
		String[] args={LANGUAGES_DISPLAYED.getCode(),word, "%","%",MY_LANGUAGE.getCode(),transWord };
		return new Extra_Word_Translation(args);
	}
	
	@Override
	public Word_Translation query(int int1) {
		String[] args={TRANSLATED_LANGUAGE.getCode(),"%", "%","%",MY_LANGUAGE.getCode(),"%"};
		return new Extra_Word_Translation(String.valueOf(int1),args);
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
	
	
}
