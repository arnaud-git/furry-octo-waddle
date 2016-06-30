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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class ModifyActivity extends ActionBarActivity{
	
	Button addButton;
	EditText editWord, editWordTrans;
	String word, wordTrad;
	Word_Translation word_obj;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_layout);
		view_of_the_word(1);
		writeWord();
		addbutton_update();
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
	
	private void writeWord(){
		List<Word_Translation> list= MainActivity.cbd.getWordFromTable(new Word_Translation("%", "%", getIntent().getExtras().getInt("word_obj")),Order.NULL,1);
		if(list.size()>0)
			word_obj=list.get(0);
		MainActivity.printDebug(2, word_obj.getWord());
		view_of_the_word(1);
		editWord = (EditText) findViewById(R.id.editWord);
		editWordTrans = (EditText) findViewById(R.id.editWordTrans);

		word_obj.printWord();
		MainActivity.printDebug(2, word_obj.getWord());
		editWord.setText(word_obj.getWord());
		editWordTrans.setText(word_obj.getTraduction_of_word());
		editWordTrans.requestFocus();
		//editWordTrans.setSelection(editWordTrans.getText().length());
		
	}
	
	private void addbutton_update(){
		addButton = (Button)findViewById(R.id.add_button);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				word = editWord.getText().toString();
				wordTrad = editWordTrans.getText().toString();

				//create the new object with the typed words
				//save the new object in the database
				// Will be better to put the languages in the inputs
				word_obj = new Word_Translation(word, wordTrad,word_obj.getId());

				MainActivity.cbd.modifyWordbyId(word_obj);

				Toast.makeText(ModifyActivity.this, "\"" + word_obj.getWord() + "\""+ " saved", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}
}