package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;



public class LearnActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

	MyPageAdapter pageAdapter;
	ViewPager pager;
	List<Fragment> fragments;

	Button buttonDelete, buttonCancel, buttonSave, buttonModify;
	TextView tv, tvTrad;
	EditText et, etTrad;
	FragmentPagerAdapter adapter;
	LearnFragment currentLF;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_layout);

		buttonDelete = (Button) findViewById(R.id.buttonDelete);
		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonModify = (Button) findViewById(R.id.buttonModify);

		fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);
		pager.setOnPageChangeListener(this);

		buttonDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Toast toast = Toast.makeText(getApplicationContext(), "Ask if sure and delete the word from the DB", Toast.LENGTH_LONG);
				toast.show();
			}
		});

		buttonModify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				adapter = (FragmentPagerAdapter)pager.getAdapter();
				currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());

				TextView[] tv = getCurrentTv(currentLF);
				EditText[] et = getCurrentEt(currentLF);

				modifyTextViewsVisibility(tv[0], 4, tv[1], 4, et[0], 0, et[1], 0);
				modifyButtonsVisibility(buttonDelete, 4, buttonCancel, 0, buttonSave, 0, buttonModify, 4);

				et[0].setText(tv[0].getText().toString());
				et[1].setText(tv[1].getText().toString());

			}
		});

		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				adapter = (FragmentPagerAdapter)pager.getAdapter();
				currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());

				//get the current TextViews and EditTexts
				TextView[] tv = getCurrentTv(currentLF);
				EditText[] et = getCurrentEt(currentLF);

				modifyTextViewsVisibility(tv[0], 0, tv[1], 0, et[0], 4, et[1], 4);
				modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);
			}
		});

		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				adapter = (FragmentPagerAdapter)pager.getAdapter();
				currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());

				TextView[] tv = getCurrentTv(currentLF);
				EditText[] et = getCurrentEt(currentLF);

				modifyTextViewsVisibility(tv[0], 0, tv[1], 0, et[0], 4, et[1], 4);
				modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);

				//modify the word in the DB
				//pager.notifyDataSetChanged(); --> refreshing the list of fragment..
				//..but the order and the position remain the same
				tv[0].setText(et[0].getText().toString());
				tv[1].setText(et[1].getText().toString());
			}
		});

	}

	protected static void modifyTextViewsVisibility(TextView tv, int tvV, TextView tvTrad, int tvTV, EditText et, int etV, EditText etTrad, int etTV) {
		// TODO Auto-generated method stub
		tv.setVisibility(tvV);
		tvTrad.setVisibility(tvTV);
		et.setVisibility(etV);
		etTrad.setVisibility(etTV);
	}

	protected static void modifyButtonsVisibility(Button buttonDelete, int buttonDeleteV, Button buttonCancel, int buttonCancelV, Button buttonSave, int buttonSaveV, Button buttonModify, int buttonModifyV){
		buttonDelete.setVisibility(buttonDeleteV);
		buttonCancel.setVisibility(buttonCancelV);
		buttonSave.setVisibility(buttonSaveV);
		buttonModify.setVisibility(buttonModifyV);
	}

	protected TextView[] getCurrentTv(LearnFragment currentLF) {

		tv = (TextView) currentLF.getView().findViewById(R.id.word);
		tvTrad = (TextView) currentLF.getView().findViewById(R.id.wordTrad);
		TextView[] ans = {tv,tvTrad};
		return ans;
	}

	protected EditText[] getCurrentEt(LearnFragment currentLF) {

		et = (EditText) currentLF.getView().findViewById(R.id.hidden_edit_word);
		etTrad = (EditText) currentLF.getView().findViewById(R.id.hidden_edit_word_trad);
		EditText[] ans = {et,etTrad};
		return ans;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		//called when the user changes the view
		adapter = (FragmentPagerAdapter)pager.getAdapter();
		currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());

		TextView[] tv = getCurrentTv(currentLF);
		EditText[] et = getCurrentEt(currentLF);

		modifyTextViewsVisibility(tv[0], 0, tv[1], 0, et[0], 4, et[1], 4);
		modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);		
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

		List<Word_Translation> words = MainActivity.cbd.getWordFromTable(new Word_Translation("%", "%"),true, -1);
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
}