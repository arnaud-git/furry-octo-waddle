package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.alphabet.Languages_ISO;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class AddActivity extends BaseActivity{


	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);
		setViewByLayout();
		String[] args = {Languages_ISO.ENGLISH,"","","",Languages_ISO.FRENCH,""};
		setCurrentWord(new Extra_Word_Translation(args));
		writeWord();
		try{
			

			// get the listview
	        expListView = (ExpandableListView) findViewById(R.id.left_drawer);
	 
	        // preparing list data
	        //prepareListData();
	 
	        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
	 
	        // setting list adapter
	        expListView.setAdapter(listAdapter);
		}catch(Exception e){
			MainActivity.printDebug(1, e.getMessage());
			e.printStackTrace();
		}
	}	

	@Override
	protected void save_current_word(){
		try{
		super.save_current_word();
		String[] args = {ExtraWordActionsBase.TRANSLATED_LANGUAGE.getCode(), "","","",ExtraWordActionsBase.MY_LANGUAGE.getCode(),""};
		setCurrentWord(new Extra_Word_Translation(args));
		writeWord();}
		catch(Exception e){
			e.printStackTrace();
			MainActivity.printDebug(0, e.getMessage());
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		boolean retour = super.onCreateOptionsMenu(menu);
		menu.findItem(R.id.editting_word).setVisible(false);
		menu.findItem(R.id.deleting_word).setVisible(false);
		return retour;
	}

	@Override
	protected void change_mode(){
		super.change_mode();
		super.recreate();
	}
}