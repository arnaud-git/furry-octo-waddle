package com.example.furry_octo_waddle.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;

public class TestResultsActivity extends ActionBarActivity{
	
	TextView finalScoreTest, finalCommentTest;
	String finalComment;
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.final_score_test_layout);
		int score = (Integer) getIntent().getSerializableExtra("score");
		
		finalScoreTest = (TextView) findViewById(R.id.finalScoreTest);
		finalCommentTest = (TextView) findViewById(R.id.finalCommentTest);
		
		if(score < 25)
			finalComment = "Learn your vocabulary!";
		else if(score >= 25 && score < 50)
			finalComment = "You can do better!";
		else if(score >= 50 && score < 75)
			finalComment = "Not bad but try again!";
		else if(score >= 75 && score < 90)
			finalComment = "Good job!";
		else if(score >= 90 && score < 100)
			finalComment = "Excellent!";
		else
			finalComment = "Perfect! Add more vocabulary!";
		
		finalCommentTest.setText(finalComment);
		finalScoreTest.setText(score + "%");
	}
}
