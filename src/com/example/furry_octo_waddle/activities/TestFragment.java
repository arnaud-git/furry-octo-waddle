package com.example.furry_octo_waddle.activities;

import java.io.Serializable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class TestFragment extends Fragment implements Serializable{

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	String word, wordTrans;
	EditText editWord, editWordTrans;
	TextView tvWord, tvWordTrans;
	TextView liveScoreTextView;
	View v;
	ViewPager pager;
	String liveScore;
	int listLength;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.word_layout, container, false);

		MainActivity.printDebug(2, "msg");
		String[] words = getArguments().getStringArray(EXTRA_MESSAGE);

		editWord = (EditText) v.findViewById(R.id.editWord);
		tvWordTrans = (TextView) v.findViewById(R.id.tvWordTrans);
		editWordTrans = (EditText) v.findViewById(R.id.editWordTrans);
		tvWord = (TextView) v.findViewById(R.id.tvWord);
		
		display_correct_word_views();
		
		wordTrans = words[1];
		tvWord.setText(words[0]);
		editWordTrans.setHint("...");
		
		pager = TestActivity.getPager();
		listLength = pager.getAdapter().getCount();
		
		liveScoreTextView = (TextView) getActivity().findViewById(R.id.liveScore);
		liveScoreTextView.setText("0/0");
		
		editWordTrans.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
				if(Word_Translation.matches(editWordTrans.getText().toString(),wordTrans) && !TestActivity.getAnswerClicked()) {
					
					//MainActivity.printDebug(25, pager.getCurrentItem() +" / "+pager.getAdapter().getCount());
					TestActivity.incrementScore();
					refreshTestContent();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {			
			}
			
		});
		
		return v;
    }

	public static final Fragment newInstance(Word_Translation word_Translation) {
		TestFragment f = new TestFragment();
		Bundle bdl = new Bundle(1);

		String[] str_array = {word_Translation.getWord(), word_Translation.getTraduction_of_word(),Integer.toString(word_Translation.getId())};
		bdl.putStringArray(EXTRA_MESSAGE, str_array);
	    f.setArguments(bdl);

	    return f;
	}
	
	public String[] getCurrentWords() {
		String[] ans = {word, wordTrans};
		return ans;
	}
	
	private void display_correct_word_views (){
		
		editWord.setVisibility(View.INVISIBLE);
		editWordTrans.setVisibility(View.VISIBLE);
		editWordTrans.requestFocus();
		tvWord.setVisibility(View.VISIBLE);
		tvWordTrans.setVisibility(View.INVISIBLE);
	}
	
	//displays a new fragment (or the result) and updates the score
	public void refreshTestContent(){
		
		if(pager.getCurrentItem() + 1 < pager.getAdapter().getCount()) {
			pager.setCurrentItem(pager.getCurrentItem() + 1);
			updateTextLiveScore(getLiveScore());
		}
		
		//TODO print results
		else {
			Intent i = new Intent(getActivity(), TestResultsActivity.class);
			i.putExtra("score", (100*TestActivity.getNumWordsFound())/pager.getAdapter().getCount());
			getActivity().finish();
			startActivity(i);
		}
	}
	
	public void updateTextLiveScore(String liveScore) {
		liveScoreTextView.setText(liveScore);
	}
	
	public String getLiveScore() {
		return String.valueOf(TestActivity.getNumWordsFound() + "/" + String.valueOf(pager.getCurrentItem()));
	}
	public TextView getTextWordTrans(){
		return (TextView) getView().findViewById(R.id.tvWordTrans);
	}
	
	public EditText getEditTestWord() {
		return editWordTrans;
	}
}
