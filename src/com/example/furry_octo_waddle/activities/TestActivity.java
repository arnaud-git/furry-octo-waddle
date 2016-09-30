package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.activities.BaseActivity.ExpandableListAdapter;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;


public class TestActivity extends BaseActivity {

	static ViewPager pager;
	MyPageAdapter pageAdapter;
	List<Fragment> fragments;
	TestFragment currentLF;

	EditText editTestWord;
	private TextView liveScoreTextView;
	private FragmentPagerAdapter adapter;
	
	static int numWordsFound;
	static boolean answerClicked = false;
	


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		modification_enable = false;
		setContentView(R.layout.test_layout);

		fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
		
		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);
		adapter = (FragmentPagerAdapter)pager.getAdapter();
		numWordsFound = 0;
		liveScoreTextView = (TextView) findViewById(R.id.liveScore);
		liveScoreTextView.setText("0/0");
		try{
			// get the listview
	        expListView = (ExpandableListView) findViewById(R.id.left_drawer);
	 
	        // preparing list data
	        prepareListData();
	 
	        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
	 
	        // setting list adapter
	        expListView.setAdapter(listAdapter);
		}catch(Exception e){
			MainActivity.printDebug(1, e.getMessage());
			e.printStackTrace();
		}
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

		List<Word_Translation> words = action.getWordFromTable("~", "~",Order.RANDOM, -1);
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
	    boolean retour =  super.onCreateOptionsMenu(menu);
	    menu.findItem(R.id.editting_word).setVisible(false);
	    menu.findItem(R.id.deleting_word).setVisible(false);
	    menu.findItem(R.id.saving_word).setVisible(false);
	    menu.findItem(R.id.answer).setVisible(true);
	    return retour;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		currentLF = (TestFragment) adapter.getItem(pager.getCurrentItem());
		setFragment(currentLF);
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.answer:
	        	show_answer();
	        	swipe();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	Runnable r = new Runnable() {
	    @Override
	    public void run(){
	    	 //call the method of TestFragment which is responsible for the display of the test content
			refreshTestContent();
	    }
	};
	
	public void show_answer(){
		answerClicked = true;
		MyPageAdapter adapter = (MyPageAdapter) pager.getAdapter();
		
		currentLF = (TestFragment) adapter.getItem(pager.getCurrentItem());
		action.show_answer();
	}
	
	public void swipe(){
		Handler h = new Handler();
		h.postDelayed(r, 1000); //include a delay of 1 sec before displaying a new fragment	
		answerClicked = false;
	}
	
	
	public static void incrementScore() {
			numWordsFound++;
	}
	
	public static boolean getAnswerClicked() {
		return answerClicked;
	}
	

	public String getLiveScore() {
		return String.valueOf(TestActivity.getNumWordsFound() + "/" + String.valueOf(pager.getCurrentItem()));
	}
	
	
	public void refreshTestContent(){
		
		if(pager.getCurrentItem() + 1 < pager.getAdapter().getCount()) {
			pager.setCurrentItem(pager.getCurrentItem() + 1);
			liveScoreTextView.setText(getLiveScore());	
		}
		
		//TODO print results
		else {
			Intent i = new Intent(this, TestResultsActivity.class);
			i.putExtra("score", (100*TestActivity.getNumWordsFound())/pager.getAdapter().getCount());
			//finish();
			startActivity(i);
		}
	}	
	
}
