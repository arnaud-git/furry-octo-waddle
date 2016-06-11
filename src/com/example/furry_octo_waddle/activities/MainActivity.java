package com.example.furry_octo_waddle.activities;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.R.id;
import com.example.furry_octo_waddle.R.layout;
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
	private static final boolean DEBUG_LEVEL_ACTIVATE = true;
	Button buttonLearn, buttonTest;
	public static BD_rw cbd  = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		
		
		buttonLearn = (Button)findViewById(R.id.buttonLearn);
		cbd = new Controleur_bd(this);
		buttonLearn.setOnClickListener(new View.OnClickListener() {
	
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,LearnActivity.class);
                startActivity(i);
                //printDebug(1,"Lancement activite : "+i);
                //cbd.test();
			}
		});
		
		buttonTest = (Button)findViewById(R.id.buttonAdd);
		buttonTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
			}
		});
	}
	
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
