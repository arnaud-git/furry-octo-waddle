package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class AddActivity extends BaseActivity{


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			setContentView(R.layout.add_layout);
			setViewByLayout();
			writeWord();
		}catch(Exception e){
			MainActivity.printDebug(1, e.getMessage());
			e.printStackTrace();
		}
	}	

	@Override
	protected void save_current_word(){
		try{
		super.save_current_word();
		String[] args = {Word_Translation.ENGLISH,"","","",Word_Translation.FRENCH,""};
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
	protected void change_mode(MenuItem item){
		super.change_mode(item);
		super.recreate();
	}
}