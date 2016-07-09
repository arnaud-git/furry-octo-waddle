package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;


public class TestActivity extends ActionBarActivity {

	static ViewPager pager;
	MyPageAdapter pageAdapter;
	List<Fragment> fragments;

	FragmentPagerAdapter adapter;
	TestFragment currentLF;

	EditText editTestWord;
	
	static int numWordsFound;
	static boolean answerClicked = false;


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.test_layout);

		fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);
		
		numWordsFound = 0;
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
	
	public static int getNumWordsFound(){
		return numWordsFound;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.context_menu, menu);
	    menu.findItem(R.id.editting_word).setVisible(false);
	    menu.findItem(R.id.deleting_word).setVisible(false);
	    menu.findItem(R.id.saving_word).setVisible(false);
	    menu.findItem(R.id.answer).setVisible(true);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.answer:
	        	show_word_and_swipe();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	Runnable r = new Runnable() {
	    @Override
	    public void run(){
			currentLF.displayNewTestFragment(); //call the method of TestFragment that is responsible for the view modification
	    }
	};
	
	public void show_word_and_swipe() {
		answerClicked = true;
		adapter = (FragmentPagerAdapter)pager.getAdapter();
		currentLF = (TestFragment) adapter.getItem(pager.getCurrentItem());
		
		currentLF.getEditTestWord().setVisibility(View.INVISIBLE);
		
		TextView textWordTrans = currentLF.getTextWordTrans();
		textWordTrans.setVisibility(View.VISIBLE);
		textWordTrans.setText(currentLF.getCurrentWords()[1]);
		textWordTrans.setTextColor(Color.parseColor("#FF0033"));
		
		Handler h = new Handler();
		h.postDelayed(r, 1000); //include a delay of 1sec before displaying a new fragment
		
		answerClicked = false;
	}
	
	public static void updateScore(boolean found) {
		if(found)
			numWordsFound++;
	}
	
	public static boolean getAnswerClicked() {
		return answerClicked;
	}
}
