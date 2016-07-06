package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

	ViewPager pager;
	//FragmentStatePagerAdapter adapter;
	MyPageAdapter pageAdapter;
	List<Fragment> fragments;

	static Button buttonDelete;
	static Button buttonCancel;
	static Button buttonSave;
	static Button buttonModify;
	
	TextView tv, tvTrans;
	EditText et, etTrans;
	LearnFragment currentLF;

	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		// Called when the action mode is created; startActionMode() was called
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// Inflate a menu resource providing context menu items
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.context_menu, menu);
			showActionMode(mode, menu);
			return true;
		}

		// Called each time the action mode is shown. Always called after onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			showActionMode(mode, menu);
			return true; // Return false if nothing is done
		}

		// Called when the user selects a contextual menu item
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			MainActivity.printDebug(25,"Item id "+ item.getItemId());
			switch (item.getItemId()) {
			case R.id.editting_word:
				modify_current_word();
				//mode.finish(); // Action picked, so close the CAB
				return true;
			case R.id.deleting_word:
				delete_current_word();
				mode.finish(); // Action picked, so close the CAB
				return true;
			case R.id.saving_word:
				save_current_word();
				mode.finish(); // Action picked, so close the CAB
				return true;
			default:
				return false;
			}
		}

		// Called when the user exits the action mode
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			cancel_modification();
			mActionMode = null;
		}
	};
	protected ActionMode mActionMode =null;

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

		//Get the first current LF
		currentLF = (LearnFragment) pageAdapter.getItem(pager.getCurrentItem());

		//detect the scrolling to reinitialize the buttons and the views of the current Activity
		pager.setOnPageChangeListener(this);
		
		buttonDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {			
				delete_current_word();				
			}
		});

		buttonModify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				modify_current_word();

			}
		});

		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cancel_modification();
			}
		});

		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				save_current_word();			
			}
		});	

	}
	
	
	protected void modify_current_word() {
		//get the current TextViews and EditTexts
		TextView[] tv = getCurrentTv(currentLF);
		EditText[] et = getCurrentEt(currentLF);
		currentLF.setCurrentStatus(true);
		//sets the EditTexts to invisible and the TextViews to visible
		modifyTextViewsVisibility(tv[0], 4, tv[1], 4, et[0], 0, et[1], 0);
		//sets the buttons 'Cancel and Save' to invisible and 'Delete and Modify' to visible
		modifyButtonsVisibility(buttonDelete, 4, buttonCancel, 0, buttonSave, 0, buttonModify, 4);

		//copy the words from the TextViews in the EditTexts
		et[0].setText(tv[0].getText().toString());
		et[1].setText(tv[1].getText().toString());
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
	}


	protected void cancel_modification() {
		
		//get the current TextViews and EditTexts
		TextView[] tvWords = getCurrentTv(currentLF);
		EditText[] etWords = getCurrentEt(currentLF);
		currentLF.setCurrentStatus(false);
		//sets the TextViews to invisible and the EditTexts to visible
		modifyTextViewsVisibility(tvWords[0], 0, tvWords[1], 0, etWords[0], 4, etWords[1], 4);
		//sets the buttons 'Delete and Modify' to invisible and 'Cancel and Save' to visible
		modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
	}


	protected void save_current_word() {

		int pos = pager.getCurrentItem();
		//get the current TextViews and EditTexts
		TextView[] tv = getCurrentTv(currentLF);
		EditText[] et = getCurrentEt(currentLF);

		//sets the EditTexts to invisible and the TextViews to visible
		modifyTextViewsVisibility(tv[0], 0, tv[1], 0, et[0], 4, et[1], 4);
		//sets the buttons 'Delete and Modify' to invisible and 'Cancel and Save' to visible
		modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);

		//Modifies in the db
		int id_word=currentLF.getCurrentWord_T().getId();
		Word_Translation word_T= new Word_Translation(et[0].getText().toString(),et[1].getText().toString(),id_word);
		MainActivity.cbd.modifyWordbyId(word_T);

		//Refreshes pager
		currentLF.setCurrentWord_T(word_T);
		currentLF.setCurrentStatus(false);
		fragments.set(pos, currentLF);
		pageAdapter.notifyDataSetChanged();
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
	}


	protected void delete_current_word() {
		//buttonDelete deletes the words of the current fragment from the database
		Toast toast = Toast.makeText(getApplicationContext(), "Ask if sure and delete the word from the DB", Toast.LENGTH_LONG);
		toast.show();

		//Deletes in the db
		MainActivity.cbd.deleteWordbyIndex(currentLF.getCurrentWord_T().getId());

		//Refreshes pager
		currentLF.setCurrentStatus(false);
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
		int pos = pager.getCurrentItem();
		fragments.remove(pos);
		pageAdapter.notifyDataSetChanged();
	}

	protected void setListenerActionMode(View v){
		v.setOnLongClickListener(new View.OnLongClickListener() {
			// Called when the user long-clicks on someView
			public boolean onLongClick(View view) {
				if (mActionMode != null) {
					return false;
				}
				// Start the CAB using the ActionMode.Callback defined above
				mActionMode = startSupportActionMode(mActionModeCallback);
				view.setSelected(true);
				return true;
			}
		});
	}
	
	protected boolean showActionMode(ActionMode mode, Menu menu){
		if(currentLF.getCurrentStatus()){
			mode.getMenu().findItem(R.id.editting_word).setVisible(false);
			mode.getMenu().findItem(R.id.saving_word).setVisible(true);
		}
		else {
			mode.getMenu().findItem(R.id.saving_word).setVisible(false);
			mode.getMenu().findItem(R.id.editting_word).setVisible(true);
		}
		return true;

	}

	/**Set the visibility of the EditTexts and the TextViews to the given arguments*/
	protected static void modifyTextViewsVisibility(TextView tv, int tvV, TextView tvTrans,
			int tvTV, EditText et, int etV, EditText etTrans, int etTV) {
		tv.setVisibility(tvV);
		tvTrans.setVisibility(tvTV);
		et.setVisibility(etV);
		etTrans.setVisibility(etTV);
	}

	/**Set the visibility of the buttons to the given arguments*/
	protected static void modifyButtonsVisibility(Button buttonDelete, int buttonDeleteV,
			Button buttonCancel, int buttonCancelV, Button buttonSave, int buttonSaveV, Button buttonModify, int buttonModifyV){
		buttonDelete.setVisibility(buttonDeleteV);
		buttonCancel.setVisibility(buttonCancelV);
		buttonSave.setVisibility(buttonSaveV);
		buttonModify.setVisibility(buttonModifyV);
	}

	/**@returns the TextViews of the current activity*/
	protected TextView[] getCurrentTv(LearnFragment currentLF) {
		tv = (TextView) currentLF.getView().findViewById(R.id.tvWord);
		tvTrans = (TextView) currentLF.getView().findViewById(R.id.tvWordTrans);
		TextView[] ans = {tv,tvTrans};
		return ans;
	}

	/**@returns the TextViews of the current activity*/
	protected EditText[] getCurrentEt(LearnFragment currentLF) {
		et = (EditText) currentLF.getView().findViewById(R.id.editWord);
		etTrans = (EditText) currentLF.getView().findViewById(R.id.editWordTrans);
		EditText[] ans = {et,etTrans};
		return ans;
	}

	@Override
	/**Called when the user changes the view, reinitializes the views (TextView,EditText,Button) of "scrolled Activity"*/
	public void onPageScrollStateChanged(int arg0) {
		//adapter = (FragmentStatePagerAdapter)pager.getAdapter();
		currentLF = (LearnFragment) pageAdapter.getItem(pager.getCurrentItem());
		TextView[] tv = getCurrentTv(currentLF);
		EditText[] et = getCurrentEt(currentLF);
		modifyTextViewsVisibility(tv[0], 0, tv[1], 0, et[0], 4, et[1], 4);
		modifyButtonsVisibility(buttonDelete, 0, buttonCancel, 4, buttonSave, 4, buttonModify, 0);
	
		//If the user swipe the view, the application ends the ActionMode
		if(mActionMode != null)
			mActionMode.finish();
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
	}

	/**@returns a list of Fragment (word and translation from the DB)*/
	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();

		List<Word_Translation> words = MainActivity.cbd.getWordFromTable(new Word_Translation("%", "%"),Order.RANDOM, -1);
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
	
	/**@returns a list of Button with Delete, Cancel, Save and Modify*/
	public static ArrayList<Button> getButtons() {
		ArrayList<Button> buttonList = new ArrayList<Button>();
		
		buttonList.add(buttonDelete);
		buttonList.add(buttonCancel);
		buttonList.add(buttonSave);
		buttonList.add(buttonModify);
		
		return buttonList;
	}


	private class MyPageAdapter extends FragmentStatePagerAdapter {
		private List<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}
		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		public void setItem(int position, Fragment newFragment) {
			this.fragments.set(position, newFragment);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}
	}
}