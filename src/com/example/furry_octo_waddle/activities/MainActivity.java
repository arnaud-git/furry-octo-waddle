package com.example.furry_octo_waddle.activities;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw;
import com.example.furry_octo_waddle.sql_manager.Controleur_bd;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

	public static final String APPLICATION_TAG_NAME ="furry_octo_waddle"; 
	public static final boolean DEBUG = true;
	public static final int DEBUG_LEVEL = 2;
	private static final boolean DEBUG_LEVEL_ACTIVATE = false;
	public static BD_rw cbd  = null;

	Button buttonLearn, buttonTest, buttonAdd, buttonTranslate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		cbd = new Controleur_bd(this);
		
		buttonLearn = (Button)findViewById(R.id.buttonLearn);
		buttonLearn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//buttonLearn sends the user on the activity Learn
				Intent i = new Intent(MainActivity.this,LearnActivity.class);
				startActivity(i);
			}
		});
		
		buttonTest = (Button)findViewById(R.id.buttonTest);
		buttonTest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//buttonTest sends the user on the activity Test
				Intent i = new Intent(MainActivity.this,TestActivity.class);
				startActivity(i);
			}
		});

		buttonAdd = (Button)findViewById(R.id.buttonAdd);
		buttonAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//buttonAdd sends the user on the activity Learn
				Intent i = new Intent(MainActivity.this,AddActivity.class);
				startActivity(i);
			}
		});
		
		buttonTranslate = (Button)findViewById(R.id.buttonTranslate);
		buttonTranslate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//buttonAdd sends the user on the activity Learn
				Intent i = new Intent(MainActivity.this,TranslateActivity.class);
				startActivity(i);
			}
		});
	}

	/** In debug mode print the message within msg 
	 *  @param level if  MainActivity.DEBUG_LEVEL_ACTIVATE, the message will be be printed id the level equals to MainActivity.DEBUG_LEVEL
	 *  @param msg message printed
	 *  */
	public final static void printDebug(int level,String msg){
		if(DEBUG){
			if(DEBUG_LEVEL_ACTIVATE){
				if(DEBUG_LEVEL == level){
					Log.d(APPLICATION_TAG_NAME, msg);
				}
			}else {
				Log.d(APPLICATION_TAG_NAME,"Level "+level+" : "+ msg);
			}
		}

	}

}
