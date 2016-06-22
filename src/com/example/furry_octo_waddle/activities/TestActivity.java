package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class TestActivity extends FragmentActivity {

	static ViewPager pager;
	MyPageAdapter pageAdapter;
	List<Fragment> fragments;
	
	public void onCreate(Bundle savedInstanceState) {
		
		Log.d("cc","zaboul12");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		Log.d("cc","zaboul1");

		fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		Log.d("cc","zaboul2");
		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);


		Log.d("cc","zaboul3");
		//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
	    pager.setOnTouchListener(new View.OnTouchListener() 
	    {         
	        @Override
	        public boolean onTouch(View v, MotionEvent event)
	        { 
	        	return true; 
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

		List<Word_Translation> words = MainActivity.cbd.getWordFromTable(new Word_Translation("~", "~"),true, -1);
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
}
