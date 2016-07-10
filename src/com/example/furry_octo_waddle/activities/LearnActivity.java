package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnActivity extends Base_Activity {

	ViewPager pager;
	MyPageAdapter pageAdapter;
	List<Fragment> fragments;
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
				mode.finish(); // Action picked, so close the CAB
				confirm_deletion();
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
		pager.setOnPageChangeListener(pageChanged);
	}

	OnPageChangeListener pageChanged = new OnPageChangeListener() {

		@Override
		/**Called when the user changes the view, reinitializes the views (TextView,EditText,Button) of "scrolled Activity"*/
		public void onPageScrollStateChanged(int arg0) {
			currentLF = (LearnFragment) pageAdapter.getItem(pager.getCurrentItem());

			//If the user swipe the view, the application ends the ActionMode
			//setCurrentFragment(currentLF);
			if(mActionMode != null)
				mActionMode.finish();
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
		}

	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		setFragment(currentLF);
		invalidateOptionsMenu();
		switch (item.getItemId()){
		case android.R.id.home:
			if(currentLF.getCurrentStatus()){
				cancel_modification();
				return true;
			}else{
				return  super.onOptionsItemSelected(item);
				}
		default : return super.onOptionsItemSelected(item);
		}
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

	/**@returns a list of Fragment (word and translation from the DB)*/
	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();

		List<Word_Translation> words = MainActivity.cbd.getWordFromTable(new Word_Translation("%", "~"),Order.RANDOM, -1);
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

		@Override
		public int getCount() {
			return this.fragments.size();
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}
	}

	@Override
	protected void save_current_word() {
		super.save_current_word();
		currentLF.setCurrentStatus(false);
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
	}

	@Override
	protected void delete_current_word(){
		super.delete_current_word();
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
		int pos = pager.getCurrentItem();
		fragments.remove(pos);
		pageAdapter.notifyDataSetChanged();

	}

	@Override
	protected void modify_current_word() {
		super.modify_current_word();
		currentLF.setCurrentStatus(true);
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
	}
	
	@Override
	protected void cancel_modification() {
		super.cancel_modification();
		currentLF.setCurrentStatus(false);
		if (mActionMode != null) {
			mActionMode.invalidate();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		boolean retour = super.onCreateOptionsMenu(menu);
		if(currentLF.getCurrentStatus()){
			menu.findItem(R.id.editting_word).setVisible(false);
			menu.findItem(R.id.saving_word).setVisible(true);
		}
		else {
			menu.findItem(R.id.saving_word).setVisible(false);
			menu.findItem(R.id.editting_word).setVisible(true);
		}
		return retour;
	}
}

	