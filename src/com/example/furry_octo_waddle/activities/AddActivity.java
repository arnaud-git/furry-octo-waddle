package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class AddActivity extends ActionBarActivity{
	
	Button addButton;
	EditText editWord, editWordTrans;
	String word, wordTrad;
	Word_Translation word_obj;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);
		view_of_the_word(1);
		//editWord = (EditText) findViewById(R.id.editWord);
		//editWordTrans = (EditText) findViewById(R.id.editWordTrans);
		
		addButton = (Button)findViewById(R.id.add_button);
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				save_current_word();		
			}
		});		
	}
	
	private void view_of_the_word(int i){
		editWord = (EditText) findViewById(R.id.editWord);
		editWordTrans = (EditText) findViewById(R.id.editWordTrans);
		TextView tvWord = (TextView) findViewById(R.id.tvWord);
		TextView tvWordTrans = (TextView) findViewById(R.id.tvWordTrans);
		editWord.setVisibility(View.VISIBLE);
		editWordTrans.setVisibility(View.VISIBLE);
		tvWord.setVisibility(View.GONE);
		tvWordTrans.setVisibility(View.GONE);
	}
	
	private void save_current_word(){
		word = editWord.getText().toString();
		wordTrad = editWordTrans.getText().toString();

		//create the new object with the typed words
		//save the new object in the database
		// Will be better to put the languages in the inputs
		word_obj = new Word_Translation(word, wordTrad);

		MainActivity.cbd.writeWord(word_obj);

		Toast.makeText(AddActivity.this, "\"" + word_obj.getWord() + "\""+ " saved", Toast.LENGTH_SHORT).show();
		editWord.setText("");
		editWordTrans.setText("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.context_menu, menu);
	    menu.findItem(R.id.editting_word).setVisible(false);
	    menu.findItem(R.id.deleting_word).setVisible(false);
	    /*MenuItem searchItem = menu.findItem(R.id.action_search);
	    SearchView searchView =
	            (SearchView) MenuItemCompat.getActionView(searchItem);*/

	    // Configure the search info and add any event listeners...

	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.saving_word:
	            save_current_word();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}