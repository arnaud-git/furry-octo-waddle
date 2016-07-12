package com.example.furry_octo_waddle.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class ModifyActivity extends BaseActivity{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_layout);
		setViewByLayout();
		List<Word_Translation> list= action.getWordFromTable(new Word_Translation("%", "%", getIntent().getExtras().getInt(Word_Translation.WORD_ID)),Order.NULL,1);
		if(list.size()>0)
			setCurrentWord(list.get(0));
		writeWord();
		
	}
	
	@Override
	protected void save_current_word(){
		super.save_current_word();
		finish();
	}
	
	@Override
	protected void delete_current_word(){
		super.save_current_word();
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		boolean retour = super.onCreateOptionsMenu(menu);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_clear_mtrl_alpha);
		menu.findItem(R.id.editting_word).setVisible(false);
		//menu.findItem(R.id.deleting_word).setVisible(false);
		return retour;
	}
}