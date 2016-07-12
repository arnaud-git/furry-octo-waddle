package com.example.furry_octo_waddle.activities;

import java.io.Serializable;
import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;

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

public class WordActions implements Serializable{

	protected Word_Translation word_obj =  new Word_Translation("", "");
	protected TextView tvWord;
	protected TextView tvWordTrans;
	protected EditText editWord;
	protected EditText editWordTrans;
	BaseActivity ba;

	public WordActions(BaseActivity ba){
		this.ba = ba;
	}



	protected void setViewByLayout(){
		editWord = (EditText) ba.findViewById(R.id.editWord);
		editWordTrans = (EditText) ba.findViewById(R.id.editWordTrans);
		tvWord = (TextView) ba.findViewById(R.id.tvWord);
		tvWordTrans = (TextView) ba.findViewById(R.id.tvWordTrans);
	}

	protected void setViewByFragment(View v){
			editWord = (EditText) v.findViewById(R.id.editWord);
			editWordTrans = (EditText) v.findViewById(R.id.editWordTrans);
			tvWord = (TextView) v.findViewById(R.id.tvWord);
			tvWordTrans = (TextView) v.findViewById(R.id.tvWordTrans);
	}

	protected void delete_current_word() {
		MainActivity.cbd.deleteWord(word_obj);	
	}

	protected boolean save_current_word(){
		String word = editWord.getText().toString();
		String wordTrad = editWordTrans.getText().toString();
		
		if(word != "" || wordTrad != "") {
			//create the new object with the typed words
			//save the new object in the database
			// Will be better to put the languages in the inputs
			word_obj = new Word_Translation(word_obj.getId(),word, wordTrad);
	
			// TODO cbd one function for thes cases
			if(word_obj.getId()>0)
				MainActivity.cbd.modifyWordbyId(word_obj);
			else
				MainActivity.cbd.writeWord(word_obj);
			return true;
		}return false;
	}
	
	protected void modify_current_word() {

		//get the current TextViews and EditTexts
		showAllEditTexts();
		//copy the words from the TextViews in the EditTexts
		editWord.setText(word_obj.getWord());
		editWordTrans.setText(word_obj.getTraduction_of_word());
	}

	protected void showAllEditTexts(){
		editWord.setVisibility(View.VISIBLE);
		editWordTrans.setVisibility(View.VISIBLE);		
		tvWord.setVisibility(View.GONE);
		tvWordTrans.setVisibility(View.GONE);
	}
	
	protected void showAllTextViews(){
		tvWord.setVisibility(View.VISIBLE);
		tvWordTrans.setVisibility(View.VISIBLE);		
		editWord.setVisibility(View.GONE);
		editWordTrans.setVisibility(View.GONE);
	}
	
	
	protected void setCurrentWord(Word_Translation word){
		word_obj=word;
	}

	protected Word_Translation getCurrentWord(){
		return word_obj;
	}
	
	protected void cancel_modification() {
		showWord();
	}
	
	protected void updateWordinViews(){
		tvWord.setText(word_obj.getWord());
		tvWordTrans.setText(word_obj.getTraduction_of_word());
		editWord.setText(word_obj.getWord());
		editWordTrans.setText(word_obj.getTraduction_of_word());
	}
	
	protected void showWord(){
		updateWordinViews();
		showAllTextViews();	
		}

	protected void writeWord(){
		updateWordinViews();
		showAllEditTexts();
		}

	protected void setFragment(LearnFragment fragment){
		setViewByFragment(fragment.getViewPos());
		setCurrentWord(fragment.getCurrentWord_T());
		showWord();
	}
	
	protected List<Word_Translation> getWordFromTable(Word_Translation word,Order orderby, int nombre){
		return MainActivity.cbd.getWordFromTable(word, orderby, nombre);
	}
	
	
	protected void display_correct_word_views_TEST (){
		updateWordinViews();
		editWordTrans.setText("");
		editWordTrans.setHint("...");
		editWord.setVisibility(View.INVISIBLE);
		editWordTrans.setVisibility(View.VISIBLE);
		editWordTrans.requestFocus();
		editWordTrans.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
				if(Word_Translation.matches(editWordTrans.getText().toString(),word_obj.getTraduction_of_word()) && !TestActivity.getAnswerClicked()) {
					
					//MainActivity.printDebug(25, pager.getCurrentItem() +" / "+pager.getAdapter().getCount());
					TestActivity.incrementScore();
					((TestActivity)ba).displayNewTestFragment();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {			
			}
			
		});
		tvWord.setVisibility(View.VISIBLE);
		tvWordTrans.setVisibility(View.INVISIBLE);
	}



	public void show_answer() {
		editWordTrans.setVisibility(View.VISIBLE);
		editWordTrans.setText(word_obj.getTraduction_of_word());
		editWordTrans.setTextColor(Color.parseColor("#FF0033"));
		MainActivity.printDebug(100, R.id.editWordTrans+" vs "+ba.getCurrentFocus().getId());
	}
}
