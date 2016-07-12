package com.example.furry_octo_waddle.activities;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends ActionBarActivity{

	protected WordActions action =  new WordActions(this);

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
		action.setViewByLayout();
	}

	protected void setViewByFragment(View v){
		action.setViewByFragment(v);
	}

	protected void delete_current_word() {
		//buttonDelete deletes the words of the current fragment from the database
		Toast toast = Toast.makeText(getApplicationContext(), "Word deleted ! \n ("+action.getCurrentWord().getWord()+")", Toast.LENGTH_LONG);
		toast.show();

		//Deletes in the db
		action.delete_current_word();	
	}

	protected void save_current_word(){
		boolean ret = action.save_current_word();
		if(ret)
			Toast.makeText(BaseActivity.this, "\"" + action.getCurrentWord().getWord() + "\""+ " saved", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(BaseActivity.this, "\" Bad entry \"", Toast.LENGTH_SHORT).show();
		
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
		showAllEditTexts();
	}

	protected void showAllEditTexts(){
		action.showAllEditTexts();
	}

	protected void showAllTextViews(){
		action.showAllTextViews();
	}


	protected void setCurrentWord(Word_Translation word){
		action.setCurrentWord(word);
	}

	protected void cancel_modification() {
		showWord();
	}

	private void updateWordinViews(){
		action.updateWordinViews();
	}

	protected void showWord(){
		updateWordinViews();
		showAllTextViews();	
	}

	protected void writeWord(){
		updateWordinViews();
		showAllEditTexts();
	}

	protected void setFragment(MyFragment fragment){
		setViewByFragment(fragment.getViewPos());
		setCurrentWord(fragment.getCurrentWord_T());

	}

	protected void showFragment(LearnFragment fragment){
		setFragment(fragment);
		showWord();
	}
	
	protected void showFragment(TestFragment fragment){
		setFragment(fragment);
		showWord();
	}
	
	protected void display_correct_word_views_TEST(){
		action.display_correct_word_views_TEST();
	}
}
