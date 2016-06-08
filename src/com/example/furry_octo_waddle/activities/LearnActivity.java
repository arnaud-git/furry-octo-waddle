package com.example.furry_octo_waddle.activities;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.R.id;
import com.example.furry_octo_waddle.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LearnActivity extends Activity {
	
	TextView  newWord, newWordTrad;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        
        newWord = (TextView) findViewById(R.id.newWord);
        newWordTrad = (TextView) findViewById(R.id.newWordTrad);
        
        newWord.setText("ingrained");
        newWord.setText("enraciné");
    }
}
