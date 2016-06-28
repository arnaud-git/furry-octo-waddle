package com.example.furry_octo_waddle.activities;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateActivity extends ListActivity {

	Button addButton;
	EditText editWord, editWordTrad;
	String word, wordTrad,s_query;
	Word_Translation word_obj;
	CheckBox checkBox ;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.translate_layout);
		listWords();
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		word_obj = (Word_Translation) getListAdapter().getItem(position);
		writeWord();
	}

	private void listWords(){
		findViewById(R.id.modif_word).setVisibility(View.GONE);
		TextView search_text = (TextView) findViewById(R.id.search);
		checkBox = (CheckBox) findViewById(R.id.search_all);
		search_text.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {}

			@Override
			public void afterTextChanged(Editable s) {
				query_bd(s.toString());				
			}

		});
		query_bd("");
	}

	public void onCheckboxClicked(View view) {
	    // Is the view now checked?
	    //boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    switch(view.getId()) {
	        case R.id.search_all:
	        	query_bd(s_query);
	            break;
	        default: return;
	    }
	}
	
	private void query_bd(String s){
		Word_Translation query;
		s_query=s;
		if (checkBox.isChecked()){
		query = new Word_Translation(s+"%", "%");
		}else{
			query = new Word_Translation(s+"%", "");
		}
		
		List<Word_Translation> list = null;
		if(s.length()>0){
			list = MainActivity.cbd.getWordFromTable(query, Order.LANGUAGE_ASC, -1);
		}else{
			list = MainActivity.cbd.getWordFromTable(query, Order.STAMP_DESC, -1);
		}
		Words_Array_Adapter adapter = new Words_Array_Adapter(this,
				android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
	}
	private void writeWord(){
		findViewById(R.id.modif_word).setVisibility(View.VISIBLE);
		findViewById(R.id.listlay).setVisibility(View.GONE);
		editWord = (EditText) findViewById(R.id.editWord);
		editWordTrad = (EditText) findViewById(R.id.editWordTrans);

		word_obj.printWord();
		MainActivity.printDebug(2, word_obj.getWord());
		editWord.setText(word_obj.getWord());
		editWordTrad.setText(word_obj.getTraduction_of_word());
		addButton = (Button)findViewById(R.id.add_button);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				word = editWord.getText().toString();
				wordTrad = editWordTrad.getText().toString();

				//create the new object with the typed words
				//save the new object in the database
				// Will be better to put the languages in the inputs
				word_obj = new Word_Translation(word, wordTrad,word_obj.getId());

				MainActivity.cbd.modifyWordbyId(word_obj);

				Toast.makeText(TranslateActivity.this, "\"" + word_obj.getWord() + "\""+ " saved", Toast.LENGTH_SHORT).show();
				editWord.getText().clear();
				editWordTrad.getText().clear();		
				listWords();
			}
		});
	}
}
