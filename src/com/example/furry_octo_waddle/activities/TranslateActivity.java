package com.example.furry_octo_waddle.activities;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateActivity extends ActionBarActivity{

	Button addButton;
	EditText editWord, editWordTrad;
	String word, wordTrad,s_query;
	Word_Translation word_obj;
	CheckBox checkBox ;
	private ListView lstView;
	private boolean modification;


	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.translate_layout);
		listWords();
	}


	private void listWords(){
		findViewById(R.id.modif_word).setVisibility(View.GONE);
		findViewById(R.id.listlay).setVisibility(View.VISIBLE);
		TextView search_text = (TextView) findViewById(R.id.search);
		search_text.setSelectAllOnFocus(true);
		checkBox = (CheckBox) findViewById(R.id.search_all);
		lstView = (ListView)findViewById(android.R.id.list);
		lstView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				word_obj = (Word_Translation) lstView.getAdapter().getItem(position);
				writeWord();			
			}
		});
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
		lstView.setAdapter(adapter);
	}
	private void writeWord(){
		modification =true;
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

	@Override  
	public boolean onOptionsItemSelected(MenuItem item) {  
		switch (item.getItemId()) {  
		case android.R.id.home:
			MainActivity.printDebug(2, "modification : " +modification);
			if(modification){
				listWords();
				return true;
			}else{
				MainActivity.printDebug(2, "finish: " +modification);
				finish();  
			}
			break;  
		}  
		return false;  
	}  
}
