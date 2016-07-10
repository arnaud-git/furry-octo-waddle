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
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class AddActivity extends Base_Activity{
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);
		setViewByLayout();
		writeWord();	
	}	
	
	@Override
	protected void save_current_word(){
		super.save_current_word();
		setCurrentWord(new Word_Translation("",""));
		writeWord();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		boolean retour = super.onCreateOptionsMenu(menu);
		menu.findItem(R.id.editting_word).setVisible(false);
		menu.findItem(R.id.deleting_word).setVisible(false);
		return retour;
	}

}