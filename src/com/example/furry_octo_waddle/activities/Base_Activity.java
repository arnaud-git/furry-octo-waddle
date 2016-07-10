package com.example.furry_octo_waddle.activities;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Base_Activity extends ActionBarActivity{

	protected Word_Translation word_obj =  new Word_Translation("", "");
	protected TextView tvWord;
	protected TextView tvWordTrans;
	protected EditText editWord;
	protected EditText editWordTrans;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.editting_word:
			modify_current_word();
			return true;
		case R.id.saving_word:
			save_current_word();
			return true;
		case R.id.deleting_word:
			confirm_deletion();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void setViewByLayout(){
		editWord = (EditText) findViewById(R.id.editWord);
		editWordTrans = (EditText) findViewById(R.id.editWordTrans);
		tvWord = (TextView) findViewById(R.id.tvWord);
		tvWordTrans = (TextView) findViewById(R.id.tvWordTrans);
	}

	protected void setViewByFragment(View v){
			editWord = (EditText) v.findViewById(R.id.editWord);
			editWordTrans = (EditText) v.findViewById(R.id.editWordTrans);
			tvWord = (TextView) v.findViewById(R.id.tvWord);
			tvWordTrans = (TextView) v.findViewById(R.id.tvWordTrans);
	}

	protected void delete_current_word() {
		//buttonDelete deletes the words of the current fragment from the database
		Toast toast = Toast.makeText(getApplicationContext(), "Word deleted ! \n ("+word_obj.getWord()+")", Toast.LENGTH_LONG);
		toast.show();

		//Deletes in the db
		MainActivity.cbd.deleteWord(word_obj);	
	}

	protected void save_current_word(){
		String word = editWord.getText().toString();
		String wordTrad = editWordTrans.getText().toString();

		//create the new object with the typed words
		//save the new object in the database
		// Will be better to put the languages in the inputs
		word_obj = new Word_Translation(word_obj.getId(),word, wordTrad);

		// TODO cbd one function for thes cases
		if(word_obj.getId()>0)
			MainActivity.cbd.modifyWordbyId(word_obj);
		else
			MainActivity.cbd.writeWord(word_obj);
		Toast.makeText(Base_Activity.this, "\"" + word_obj.getWord() + "\""+ " saved", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.context_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	protected void confirm_deletion() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete this word?").setPositiveButton("Yes", dialogClickListener)
		.setNegativeButton("No", dialogClickListener).show();
	}


	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which){
			case DialogInterface.BUTTON_POSITIVE:
				//Yes button clicked
				delete_current_word();
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				//No button clicked
				break;
			}
		}
	};
	
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

	protected void cancel_modification() {
		showWord();
	}
	
	private void updateWordinViews(){
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
}
