package com.example.furry_octo_waddle.activities;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Extra_Word_Translation;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ExtraWordActions extends WordActions {
	
	protected TextView tvRomanizationWord;
	protected TextView tvPronunciationWord;
	protected EditText editRomanizationWord;
	protected EditText editPronunciationWord;
	
	public ExtraWordActions(BaseActivity ba) {
		super(ba);
		String[] args = {Word_Translation.ENGLISH,"","","",Word_Translation.FRENCH,""};
		word_obj=new Extra_Word_Translation(args);
		MainActivity.printDebug(1,word_obj.getType().name());
	}
	
	@Override
	protected void setViewByLayout(){
		super.setViewByLayout();
		editRomanizationWord = (EditText) ba.findViewById(R.id.editRomanizationWord);
		editPronunciationWord = (EditText) ba.findViewById(R.id.editPronunciationWord);
		tvRomanizationWord = (TextView) ba.findViewById(R.id.tvRomanizationWord);
		tvPronunciationWord = (TextView) ba.findViewById(R.id.tvPronunciationWord);
	}

	@Override
	protected void setViewByFragment(View v){
		super.setViewByFragment(v);
			editRomanizationWord = (EditText) v.findViewById(R.id.editRomanizationWord);
			editPronunciationWord = (EditText) v.findViewById(R.id.editPronunciationWord);
			tvRomanizationWord = (TextView) v.findViewById(R.id.tvRomanizationWord);
			tvPronunciationWord = (TextView) v.findViewById(R.id.tvPronunciationWord);
	}


	@Override
	protected void modify_current_word() {
		super.modify_current_word();
		//copy the words from the TextViews in the EditTexts
		editRomanizationWord.setText(((Extra_Word_Translation)word_obj).getRomanization());
		editPronunciationWord.setText(((Extra_Word_Translation)word_obj).getPronunciation());
	}

	@Override
	protected void showAllEditTexts(){
		super.showAllEditTexts();
		editRomanizationWord.setVisibility(View.VISIBLE);
		editPronunciationWord.setVisibility(View.VISIBLE);
		MainActivity.printDebug(1, "Visible " +editPronunciationWord.getVisibility());
		tvRomanizationWord.setVisibility(View.GONE);
		tvPronunciationWord.setVisibility(View.GONE);
	}
	
	@Override
	protected void showAllTextViews(){
		super.showAllTextViews();
		tvRomanizationWord.setVisibility(View.VISIBLE);
		tvPronunciationWord.setVisibility(View.VISIBLE);		
		editRomanizationWord.setVisibility(View.GONE);
		editPronunciationWord.setVisibility(View.GONE);
	}
	
	@Override
	protected boolean save_current_word(){
		String word = editWord.getText().toString();
		String wordTrad = editWordTrans.getText().toString();
		String Romword = editRomanizationWord.getText().toString();
		String Pronword = editPronunciationWord.getText().toString();
		
		if(word != "" || wordTrad != "") {
			//create the new object with the typed words
			//save the new object in the database
			// Will be better to put the languages in the inputs
			String[] args = {Word_Translation.ENGLISH,word,Romword,Pronword,Word_Translation.FRENCH,wordTrad};
			word_obj=new Extra_Word_Translation(args);
			
			// TODO cbd one function for thes cases
			if(word_obj.getId()>0)
				MainActivity.cbd.modifyWordbyId(word_obj);
			else
				MainActivity.cbd.writeWord(word_obj);
			return true;
		}return false;
	}
	
	
	@Override
	protected void setCurrentWord(Word_Translation word){
		word_obj=(Extra_Word_Translation)word;
	}

	@Override
	protected Word_Translation getCurrentWord(){
		return ((Extra_Word_Translation)word_obj);
	}
	
	@Override
	protected void updateWordinViews(){
		super.updateWordinViews();
		tvRomanizationWord.setText(((Extra_Word_Translation)word_obj).getRomanization());
		tvPronunciationWord.setText(((Extra_Word_Translation)word_obj).getPronunciation());
		editRomanizationWord.setText(((Extra_Word_Translation)word_obj).getRomanization());
		editPronunciationWord.setText(((Extra_Word_Translation)word_obj).getPronunciation());
	}
	
	@Override
	protected void display_correct_word_views_TEST (){
		updateWordinViews();
		editPronunciationWord.setText("");
		editPronunciationWord.setHint("...");
		editRomanizationWord.setVisibility(View.INVISIBLE);
		editPronunciationWord.setVisibility(View.VISIBLE);
		editPronunciationWord.requestFocus();
		editPronunciationWord.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
				if(Word_Translation.matches(editPronunciationWord.getText().toString(),((Extra_Word_Translation)word_obj).getTraduction_of_word()) && !TestActivity.getAnswerClicked()) {
					
					//MainActivity.printDebug(25, pager.getCurrentItem() +" / "+pager.getAdapter().getCount());
					TestActivity.incrementScore();
					((TestActivity)ba).refreshTestContent();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {			
			}
			
		});
		tvRomanizationWord.setVisibility(View.VISIBLE);
		tvPronunciationWord.setVisibility(View.INVISIBLE);
	}

	@Override
	protected Word_Translation query(String word, String transWord){
		String[] args={CURRENT_LANGUAGE,word, "%","%",CURRENT_TRANS_LANGUAGES_DISPLAY,transWord };
		return new Extra_Word_Translation(args);
	}
	
	@Override
	public Word_Translation query(int int1) {
		String[] args={CURRENT_LANGUAGE,"%", "%","%",CURRENT_TRANS_LANGUAGES_DISPLAY,"%"};
		return new Extra_Word_Translation(String.valueOf(int1),args);
	}

	@Override
	public void show_answer() {
		editPronunciationWord.setVisibility(View.VISIBLE);
		editPronunciationWord.setText(((Extra_Word_Translation)word_obj).getTraduction_of_word());
		editPronunciationWord.setTextColor(Color.parseColor("#FF0033"));
		MainActivity.printDebug(100, R.id.editPronunciationWord+" vs "+ba.getCurrentFocus().getId());
	}
	
	@Override
	protected Word_Translation nowordindb(){
		return new Extra_Word_Translation(super.nowordindb());
	}
}
