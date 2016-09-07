package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Controleur_bd;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;
import com.example.furry_octo_waddle.sql_manager.Language;
import com.example.furry_octo_waddle.sql_manager.Language.Languages_List;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;
import com.example.furry_octo_waddle.sql_manager.alphabet.Languages_ISO;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends ActionBarActivity{

	protected ExpandableListView expListView;
	protected ExpandableListAdapter listAdapter;

	protected ExtraWordActionsBase action = null;//=  new ExtraWordActionsBase(this);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		prepareListData();
		newExtraWordActionsBase();
		}catch(Exception e){
			MainActivity.printDebug(32, "Creation base");
			e.printStackTrace();
		}

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.editting_word:
			modify_current_word();
			return true;
		case R.id.saving_word:
			save_current_word();
			return true;
		case R.id.deleting_word:
			confirm_deletion();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void newExtraWordActionsBase() {
		if(ExtraWordActionsBase.EXTRA){
			action = new ExtraWordActions(this);
			MainActivity.printDebug(10, "Essai");
		}
		else
			action = new ExtraWordActionsBase(this);
	}

	protected void change_mode(){
		ExtraWordActionsBase.EXTRA = !ExtraWordActionsBase.EXTRA;
		newExtraWordActionsBase();
		//super.recreate();
	}

	protected void setViewByLayout(){
		action.setViewByLayout();
	}

	protected void setViewByFragment(View v){
		action.setViewByFragment(v);
	}

	protected void delete_current_word() {
		//buttonDelete deletes the words of the current fragment from the database
		Toast toast = Toast.makeText(getApplicationContext(), "Word deleted ! \n ("+action.getCurrentWord().getWord()+")", Toast.LENGTH_LONG);
		toast.show();

		//Deletes in the db
		action.delete_current_word();	
	}

	protected void save_current_word(){
		boolean ret = action.save_current_word();
		if(ret)
			Toast.makeText(BaseActivity.this, "\"" + action.getCurrentWord().getWord() + "\""+ " saved", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(BaseActivity.this, "\" Bad entry \"", Toast.LENGTH_SHORT).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.context_menu, menu);
		//menu.findItem(R.id.extra_mode).setChecked(ExtraWordActionsBase.EXTRA);
		return super.onCreateOptionsMenu(menu);
	}

	protected void confirm_deletion() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete this word?").setPositiveButton("Yes", dialogClickListener)
		.setNegativeButton("No", dialogClickListener).show();
	}


	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which){
			case DialogInterface.BUTTON_POSITIVE:
				//Yes button clicked
				delete_current_word();
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				//No button clicked
				break;
			}
		}
	};
	protected ArrayList<String> listDataHeader;
	protected HashMap<String, List<Language>> listDataChild;

	protected void modify_current_word() {
		showAllEditTexts();
	}

	protected void showAllEditTexts(){
		action.showAllEditTexts();
	}

	protected void showAllTextViews(){
		action.showAllTextViews();
	}


	protected void setCurrentWord(Word_Translation word){
		action.setCurrentWord(word);
	}

	protected void cancel_modification() {
		showWord();
	}

	private void updateWordinViews(){
		action.updateWordinViews();
	}

	protected void showWord(){
		updateWordinViews();
		showAllTextViews();	
	}

	protected void writeWord(){
		updateWordinViews();
		showAllEditTexts();
	}

	protected void setFragment(MyFragment fragment){
		setViewByFragment(fragment.getViewPos());
		setCurrentWord(fragment.getCurrentWord_T());

	}

	protected void showFragment(LearnFragment fragment){
		setFragment(fragment);
		showWord();
	}

	protected void showFragment(TestFragment fragment){
		setFragment(fragment);
		showWord();
	}
	protected void changeLanguage(String string) {
		// TODO Auto-generated method stub

	}
	protected void changeTranslationLanguage(String string) {
		// TODO Auto-generated method stub

	}
	protected void changeLanguagesDisplayed(String string) {
		// TODO Auto-generated method stub

	}
	protected void display_correct_word_views_TEST(){
		action.display_correct_word_views_TEST();
	}
	protected void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<Language>>();

		// Adding child data
		listDataHeader.add(getString(R.string.string_extra_mode));
		listDataHeader.add(getString(R.string.string_mylanguage));
		listDataHeader.add(getString(R.string.string_wordlanguage));
		listDataHeader.add(getString(R.string.string_languages_displayed));

		// Adding child data
		//Languages_ISO.save_Languages();
		List<Language> languages = new ArrayList<Language>();
		languages = MainActivity.cbd.getLanguage(new Language("%%"), -1);
		/*MainActivity.printDebug(20, "Salut "+languages.size());
		for (Language l : languages)
			l.print();*/
		try{
			if(languages.size()==0){
				((Controleur_bd) MainActivity.cbd).update9();
				languages = MainActivity.cbd.getLanguage(new Language("%%"), -1);
			}
			if(ExtraWordActionsBase.TRANSLATED_LANGUAGE == null)
				ExtraWordActionsBase.changeLanguage(languages.get(0));
			
			if(ExtraWordActionsBase.MY_LANGUAGE == null)
				ExtraWordActionsBase.changeMyLanguage(languages.get(1));
			
			if(!ExtraWordActionsBase.isLanguageDisplayed(ExtraWordActionsBase.TRANSLATED_LANGUAGE) ){
				ExtraWordActionsBase.displayAnotherLanguage(ExtraWordActionsBase.TRANSLATED_LANGUAGE );
			}
			
		}catch(Exception e){
			MainActivity.printDebug(24, "Erreur");
			e.printStackTrace();
		}
		List<Language> extra = new ArrayList<Language>();
		listDataChild.put(listDataHeader.get(0), extra);// Header, Child data
		listDataChild.put(listDataHeader.get(1), languages); 
		listDataChild.put(listDataHeader.get(2), languages);
		listDataChild.put(listDataHeader.get(3), languages);
	}
	public class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context _context;
		private List<String> _listDataHeader; // header titles
		// child data in format of header title, child title
		private HashMap<String, List<Language>> _listDataChild;


		public ExpandableListAdapter(Context context, List<String> listDataHeader,
				HashMap<String, List<Language>> listChildData) {
			this._context = context;
			this._listDataHeader = listDataHeader;
			this._listDataChild = listChildData;
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition))
					.get(childPosititon);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView( int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			final Language childText =  ((Language) getChild(groupPosition, childPosition));
			final String parenttexttt = (String) getGroup(groupPosition);

			if(convertView==null){
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.drawer_non_exclusive_item, null);
			}
			CompoundButton r;
			r = (CompoundButton)convertView.findViewById(R.id.toto);
			r.setText(childText.getName());
			r.setTag(childText);
			setChecked(parenttexttt,r);
			//setChecked(parenttexttt,r,childposition);
			//String toto = ;

			r.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					setLanguagesbyCheck((CompoundButton) view,parenttexttt);
					notifyDataSetChanged();
				}


			});
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition))
					.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this._listDataHeader.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return this._listDataHeader.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String headerTitle = (String) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.drawer_group_item, null);
			}

			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.label_parent_item);
			lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle);

			TextView autrelblListHeader = (TextView) convertView
					.findViewById(R.id.label_parent_item_value);
			autrelblListHeader.setTypeface(null, Typeface.BOLD);
			if(headerTitle.equalsIgnoreCase(getString(R.string.string_extra_mode))){
				CheckBox chkbox = (CheckBox) convertView.findViewById(R.id.mode_extra);
				chkbox.setVisibility(View.VISIBLE);
				chkbox.setChecked(ExtraWordActionsBase.EXTRA);
				chkbox.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						change_mode();
						notifyDataSetChanged();
					}
				});
			}
			else{
				convertView.findViewById(R.id.mode_extra).setVisibility(View.GONE);
			}
			if(headerTitle.equalsIgnoreCase(getString(R.string.string_mylanguage)))
				autrelblListHeader .setText(ExtraWordActionsBase.MY_LANGUAGE.getName());
			if(headerTitle.equalsIgnoreCase(getString(R.string.string_wordlanguage)))
				autrelblListHeader .setText(ExtraWordActionsBase.TRANSLATED_LANGUAGE.getName());
			if(headerTitle.equalsIgnoreCase(getString(R.string.string_languages_displayed))){
				autrelblListHeader .setText("");
			}


			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
	
	
	protected int MyLanguageselectedposition = 0;
	protected int TranslationLanguageselectedposition = 0;
	public void setChecked(String parenttexttt, CompoundButton r, int childPosition) {
		if(parenttexttt.compareToIgnoreCase(getString(R.string.string_mylanguage))==0){
			r.setChecked(childPosition==MyLanguageselectedposition);
		}else{
			if(parenttexttt.compareToIgnoreCase(getString(R.string.string_wordlanguage))==0){
				r.setChecked(childPosition==TranslationLanguageselectedposition);
			}else{
				if(parenttexttt.compareToIgnoreCase(getString(R.string.string_languages_displayed))==0){
					r.setChecked(!r.isChecked());
				}else{

				}
			}
		}
	}
	public void setChecked(String parenttexttt, CompoundButton r) {
		if(parenttexttt.equalsIgnoreCase(getString(R.string.string_mylanguage))){
			r.setChecked(ExtraWordActionsBase.isMyLanguage((Language) r.getTag()));
		}else{
			if(parenttexttt.equalsIgnoreCase(getString(R.string.string_wordlanguage))){
				r.setChecked(ExtraWordActionsBase.isTLanguage(((Language) r.getTag())));
			}else{
				if(parenttexttt.equalsIgnoreCase(getString(R.string.string_languages_displayed))){
					r.setChecked(ExtraWordActionsBase.isLanguageDisplayed(((Language) r.getTag())));
				}else{

				}
			}
		}
	}
	protected void setLanguagesbyCheck(CompoundButton r, String parenttexttt) {
		if(parenttexttt.equalsIgnoreCase(getString(R.string.string_mylanguage))){
			ExtraWordActionsBase.changeMyLanguage((Language)r.getTag());
		}else{
			if(parenttexttt.equalsIgnoreCase(getString(R.string.string_wordlanguage))){
				ExtraWordActionsBase.changeLanguage((Language)r.getTag());
				setLanguagesbyCheck(r, getString(R.string.string_languages_displayed));
			}else{
				if(parenttexttt.equals(getString(R.string.string_languages_displayed))){
					if(!ExtraWordActionsBase.isTLanguage( ((Language)r.getTag() ))){
						if(ExtraWordActionsBase.containsLanguage(((Language) r.getTag()))){
							ExtraWordActionsBase.removeLanguage((Language)r.getTag());
							Toast.makeText(BaseActivity.this, "\" PAADDSS " + ExtraWordActionsBase.LANGUAGES_DISPLAYED.getCode()+ "\""+ " saved", Toast.LENGTH_SHORT).show();						
						}else{
							ExtraWordActionsBase.displayAnotherLanguage((Language)r.getTag());
							Toast.makeText(BaseActivity.this, "\"" + ExtraWordActionsBase.LANGUAGES_DISPLAYED.getCode()+ "\""+ " saved", Toast.LENGTH_SHORT).show();			
						}
					}else{
						if(!ExtraWordActionsBase.containsLanguage(((Language) r.getTag()))){
							ExtraWordActionsBase.displayAnotherLanguage((Language)r.getTag());
							Toast.makeText(BaseActivity.this, "\"" + ExtraWordActionsBase.LANGUAGES_DISPLAYED.getCode()+ "\""+ " saved", Toast.LENGTH_SHORT).show();			
						}else{

						}
					}
				}else{

				}
			}
		}
	}

}