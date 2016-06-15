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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;



public class LearnActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
	
	MyPageAdapter pageAdapter;
	ViewPager pager;
	List<Fragment> fragments;
	int visibleTv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_layout);

		fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);
		pager.setOnPageChangeListener(this);
		
		Button buttonModify = (Button) findViewById(R.id.buttonModify);
		buttonModify.setOnClickListener(new View.OnClickListener() {	
			
			@Override
			public void onClick(View arg0) {

				FragmentPagerAdapter adapter = (FragmentPagerAdapter)pager.getAdapter();
				LearnFragment currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());
				
				TextView tv = (TextView) currentLF.getView().findViewById(R.id.word);
				TextView tvTrad = (TextView) currentLF.getView().findViewById(R.id.wordTrad);
				EditText et = (EditText) currentLF.getView().findViewById(R.id.hidden_edit_word);
				EditText etTrad = (EditText) currentLF.getView().findViewById(R.id.hidden_edit_word_trad);
				
				convert(tv, tvTrad, et, etTrad, et.getVisibility());
			}
		});
		
	}
	
	protected static void convert(TextView tv, TextView tvTrad, EditText et,
			EditText etTrad, int tvVisibility) {
		// TODO Auto-generated method stub
			tv.setVisibility(1-tvVisibility);
			et.setText(tv.getText().toString());
			et.setVisibility(1-tvVisibility);
			tvTrad.setVisibility(tvVisibility);
			etTrad.setText(tvTrad.getText().toString());
			etTrad.setVisibility(tvVisibility);
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