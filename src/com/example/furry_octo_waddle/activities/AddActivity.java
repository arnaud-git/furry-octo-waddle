package com.example.furry_octo_waddle.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;

public class AddActivity extends Activity{
	
	Button addButton;
	EditText editWord, editWordTrad;
	String word, wordTrad;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);

		editWord = (EditText) findViewById(R.id.editWord);
		editWordTrad = (EditText) findViewById(R.id.editWordTrad);
		
		addButton = (Button)findViewById(R.id.add_button);
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				word = editWord.getText().toString();
				wordTrad = editWordTrad.getText().toString();
				
				//create the new object with the typed words
				//save the new object in the database
				
				Toast.makeText(AddActivity.this, "\"" + word + "\""+ " saved", Toast.LENGTH_SHORT).show();
				
				editWord.getText().clear();
				editWordTrad.getText().clear();				
			}
		});
	}
}