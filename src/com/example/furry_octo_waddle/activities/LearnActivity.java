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
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

	ViewPager pager;
	FragmentPagerAdapter adapter;
	MyPageAdapter pageAdapter;
	List<Fragment> fragments;

	Button buttonDelete, buttonCancel, buttonSave, buttonModify;
	TextView tv, tvTrans;
	EditText et, etTrans;
	LearnFragment currentLF;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_layout);

		buttonDelete = (Button) findViewById(R.id.buttonDelete);
		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonModify = (Button) findViewById(R.id.buttonModify);

		//gets a list of Fragment from the DB
		fragments = getFragments();
		//sets the pageAdapter with the list of Fragment
		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		//gets pager
		pager = (ViewPager)findViewById(R.id.viewpager);
		//links the pager to the pageAdapter
		pager.setAdapter(pageAdapter);
		//detect the scrolling to reinitialize the buttons and the views of the current Activity
		pager.setOnPageChangeListener(this);

		buttonDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//buttonDelete deletes the words of the current fragment from the database
				Toast toast = Toast.makeText(getApplicationContext(), "Ask if sure and delete the word from the DB", Toast.LENGTH_LONG);
				toast.show();
				//refresh the pagerAdapter and the words displayed
			}
		});

		buttonModify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				//get the Fragment displayed when the user clicks
				adapter = (FragmentPagerAdapter)pager.getAdapter();
				currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());

				//get the current TextViews and EditTexts
				TextView[] tv = getCurrentTv(currentLF);
				EditText[] et = getCurrentEt(currentLF);

				//sets the EditTexts to invisible and the TextViews to visible
				modifyTextViewsVisibility(tv[0], 4, tv[1], 4, et[0], 0, et[1], 0);
				//sets the buttons 'Cancel and Save' to invisible and 'Delete and Modify' to visible
				modifyButtonsVisibility(buttonDelete, 4, buttonCancel, 0, buttonSave, 0, buttonModify, 4);

				//copy the words from the TextViews in the EditTexts
				et[0].setText(tv[0].getText().toString());
				et[1].setText(tv[1].getText().toString());

			}
		});

		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				//gets the Fragment displayed when the user clicks
				adapter = (FragmentPagerAdapter)pager.getAdapter();
				currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());

				//get the current TextViews and EditTexts
				TextView[] tvWords = getCurrentTv(currentLF);
				EditText[] etWords = getCurrentEt(currentLF);

				//sets the TextViews to invisible and the EditTexts to visible
				modifyTextViewsVisibility(tvWords[0], 0, tvWords[1], 0, etWords[0], 4, etWords[1], 4);
				//sets the buttons 'Delete and Modify' to invisible and 'Cancel and Save' to visible
				modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);
			}
		});

		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				//gets the Fragment displayed when the user clicks
				adapter = (FragmentPagerAdapter)pager.getAdapter();
				currentLF = (LearnFragment) adapter.getItem(pager.getCurrentItem());

				//get the current TextViews and EditTexts
				TextView[] tv = getCurrentTv(currentLF);
				EditText[] et = getCurrentEt(currentLF);

				//sets the EditTexts to invisible and the TextViews to visible
				modifyTextViewsVisibility(tv[0], 0, tv[1], 0, et[0], 4, et[1], 4);
				//sets the buttons 'Delete and Modify' to invisible and 'Cancel and Save' to visible
				modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);

				//modify the word in the DB
				//pager.notifyDataSetChanged(); --> refreshing the list of fragment..
				//..but the order and the position have to remain the same
				
				//copy the words from the EditTexts in the TextViews
				/*********HAS TO BE MODIFIED: DISPLAY THE MODIFIED WORDS IN THE DB********/
				tv[0].setText(et[0].getText().toString());
				tv[1].setText(et[1].getText().toString());
			}
		});

	}

	//Set the visibility of the EditTexts and the TextViews to the given arguments
	protected static void modifyTextViewsVisibility(TextView tv, int tvV, TextView tvTrans,
								int tvTV, EditText et, int etV, EditText etTrans, int etTV) {
		tv.setVisibility(tvV);
		tvTrans.setVisibility(tvTV);
		et.setVisibility(etV);
		etTrans.setVisibility(etTV);
	}

	//Set the visibility of the buttons to the given arguments
	protected static void modifyButtonsVisibility(Button buttonDelete, int buttonDeleteV,
								Button buttonCancel, int buttonCancelV, Button buttonSave, int buttonSaveV, Button buttonModify, int buttonModifyV){
		buttonDelete.setVisibility(buttonDeleteV);
		buttonCancel.setVisibility(buttonCancelV);
		buttonSave.setVisibility(buttonSaveV);
		buttonModify.setVisibility(buttonModifyV);
	}

	//returns the TextViews of the current activity
	protected TextView[] getCurrentTv(LearnFragment currentLF) {
		tv = (TextView) currentLF.getView().findViewById(R.id.tvWord);
		tvTrans = (TextView) currentLF.getView().findViewById(R.id.tvWordTrans);
		TextView[] ans = {tv,tvTrans};
		return ans;
	}

	//returns the TextViews of the current activity
	protected EditText[] getCurrentEt(LearnFragment currentLF) {
		et = (EditText) currentLF.getView().findViewById(R.id.etWord);
		etTrans = (EditText) currentLF.getView().findViewById(R.id.etWordTrans);
		EditText[] ans = {et,etTrans};
		return ans;
	}

	@Override
	//Called when the user changes the view, reinitializes the views (TextView,EditText,Button) of "scrolled Activity"
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
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

	//returns a list of Fragment (word and translation from the DB)
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