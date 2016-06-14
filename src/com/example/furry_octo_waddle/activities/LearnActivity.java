package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
	MyPageAdapter pageAdapter;
	ViewPager pager;
	List<Fragment> fragments;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_layout);

		fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);
		pager.setOnPageChangeListener(this);
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

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		//called when the user changes the view
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
	}
	
	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();
		
		List<Word_Translation> words = MainActivity.cbd.getWordFromTable(new Word_Translation("*", "*"),true, -1);
		if(words.isEmpty()){
			//Database is empty
			fList.add(LearnFragment.newInstance(new Word_Translation("No word in the database", null)));
		}
		else{
			for( Word_Translation word_obj : words){
				fList.add(LearnFragment.newInstance(word_obj));
			}
		}

		return fList;
	}
}