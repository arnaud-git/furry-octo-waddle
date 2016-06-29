package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;

import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class AddActivity extends ActionBarActivity{
	
	Button addButton;
	EditText editWord, editWordTrad;
	String word, wordTrad;
	Word_Translation word_obj;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);

		editWord = (EditText) findViewById(R.id.editWord);
		editWordTrad = (EditText) findViewById(R.id.editWordTrans);
		
		addButton = (Button)findViewById(R.id.add_button);
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				word = editWord.getText().toString();
				wordTrad = editWordTrad.getText().toString();
								
				//create the new object with the typed words
				//save the new object in the database
				// Will be better to put the languages in the inputs
				word_obj = new Word_Translation(word, wordTrad);
				
				MainActivity.cbd.writeWord(word_obj);
				
				Toast.makeText(AddActivity.this, "\"" + word_obj.getWord() + "\""+ " saved", Toast.LENGTH_SHORT).show();
				
				editWord.getText().clear();
				editWordTrad.getText().clear();				
			}
		});
		try{
			ActionBar actionBar = getSupportActionBar();
			actionBar.setSubtitle(R.string.string_add_words);
			actionBar.setHomeButtonEnabled(true);			
		}
		catch(Exception e){
			MainActivity.printDebug(2, e.getMessage());
		}
	
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MainActivity.printDebug(2, "Lamsg");
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
}