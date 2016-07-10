package com.example.furry_octo_waddle.activities;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TranslateActivity extends ActionBarActivity{

	Button addButton;
	EditText editWord, editWordTrans;
	String word, wordTrad,s_query;
	Word_Translation word_obj;
	CheckBox checkBox ;
	private ListView lstView;
	private boolean modification;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.translate_layout);
		listWords();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		query_bd("");
	}


	private void listWords(){
		findViewById(R.id.listlay).setVisibility(View.VISIBLE);
		TextView search_text = (TextView) findViewById(R.id.search);
		search_text.setSelectAllOnFocus(true);
		checkBox = (CheckBox) findViewById(R.id.search_all);
		lstView = (ListView)findViewById(android.R.id.list);
		lstView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				word_obj = (Word_Translation) lstView.getAdapter().getItem(position);
				Intent i = new Intent(TranslateActivity.this,ModifyActivity.class);
				i.putExtra(Word_Translation.WORD_ID, word_obj.getId());
				startActivity(i);
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
	
	
	
	private class Words_Array_Adapter extends ArrayAdapter<Word_Translation> {
		private final Context context;
		private final  List<Word_Translation> values;

		public Words_Array_Adapter(Context context, int resource, List<Word_Translation> objects) {
			super(context, resource, objects);
			values = objects;
			this.context = context;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.row_layout, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.label);
			TextView textView_t= (TextView) rowView.findViewById(R.id.label_t);
			textView.setText(values.get(position).getWord());
			textView_t.setText(values.get(position).getTraduction_of_word());
			return rowView;
		}
	}
}
