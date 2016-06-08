package com.example.furry_octo_waddle.activities;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.R.id;
import com.example.furry_octo_waddle.R.layout;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {
	
	Button buttonLearn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonLearn = (Button)findViewById(R.id.buttonLearn);
		buttonLearn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,LearnActivity.class);
                startActivity(i);
			}
		});
	}

}
