package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;


public class TestActivity extends ActionBarActivity {

	static ViewPager pager;
	MyPageAdapter pageAdapter;
	List<Fragment> fragments;

	FragmentPagerAdapter adapter;
	TestFragment currentLF;

	Button answerButton;
	EditText editTestWord;
	
	static boolean answerClicked = false;
	static int numWordsFound;


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.test_layout);

		fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);
		
		setAnswerClicked(false);

		pager.setOnTouchListener(new View.OnTouchListener() 
		{         
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{ 
				return true; 
			}
		});		
		answerButton = (Button) findViewById(R.id.answer_button);
		answerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				adapter = (FragmentPagerAdapter)pager.getAdapter();
				currentLF = (TestFragment) adapter.getItem(pager.getCurrentItem());
				editTestWord = currentLF.getEditTestWord();
				editTestWord.setText("");
				editTestWord.setHint(currentLF.getCurrentWords()[1]);
				
				setAnswerClicked(true);

				editTestWord.setHintTextColor(Color.parseColor("#FFCCCC"));
				editTestWord.setTextColor(Color.parseColor("#FF0033"));
				
				pager.setCurrentItem(pager.getAdapter().getCount() - 1);
			}
		});
	}

	private class MyPageAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}
		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}

	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();

		List<Word_Translation> words = MainActivity.cbd.getWordFromTable(new Word_Translation("~", "~"),Order.RANDOM, -1);
		if(words.isEmpty()){
			//Database is empty
			fList.add(TestFragment.newInstance(new Word_Translation("No word in the database", null)));
			//TODO make an action
		}
		else{
			for( Word_Translation word_obj : words){
				fList.add(TestFragment.newInstance(word_obj));
			}
		}

		return fList;
	}

	public static ViewPager getPager() {
		return pager;
	}
	
	public static void setAnswerClicked(boolean clickedOrNot) {
		answerClicked = clickedOrNot;
	}
	
	public static boolean getAnswerClicked() {
		return answerClicked;
	}
	
	public static void incrementNumWordsFound(){
		numWordsFound++;
	}
	
	public static int getNumWordsFound(){
		return numWordsFound;
	}
}
