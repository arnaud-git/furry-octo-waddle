package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnFragment extends Fragment {

	private Word_Translation word_obj= null;
	TextView word, wordTrans;
	EditText editWord, editWordTrans;
	ArrayList<Button> butList;
	private boolean modified = false;

	public static final LearnFragment newInstance(Word_Translation word_obj)
	{
		LearnFragment f = new LearnFragment();
		Bundle bdl = new Bundle(1);
		bdl.putInt(Word_Translation.WORD_ID, word_obj.getId());
		f.setArguments(bdl);

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.word_layout, container, false);
		word = (TextView)v.findViewById(R.id.tvWord);
		wordTrans = (TextView)v.findViewById(R.id.tvWordTrans);
		editWord = (EditText)v.findViewById(R.id.editWord);
		editWordTrans = (EditText)v.findViewById(R.id.editWordTrans);
		
		if(word_obj==null){
			List<Word_Translation> res = MainActivity.cbd.getWordFromTable(new Word_Translation("%", "%", getArguments().getInt(Word_Translation.WORD_ID)),Order.NULL,-1);
			if(res.size()==1)
				word_obj = res.get(0);
			else 
				MainActivity.printDebug(1, "Renvoie liste trop longue");
		}
		
		word.setText(word_obj.getWord());
		editWord.setText(word_obj.getWord());
		wordTrans.setText(word_obj.getTraduction_of_word());
		editWordTrans.setText(word_obj.getTraduction_of_word());
		
		word.setClickable(true);
		wordTrans.setClickable(true);
		
		word.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				butList = LearnActivity.getButtons();
				LearnActivity.modifyButtonsVisibility(butList.get(0),4,butList.get(1),0,butList.get(2),0,butList.get(3),4);
				
				wordTrans.setVisibility(0);
				editWordTrans.setVisibility(4);
				word.setVisibility(4);
				editWord.setVisibility(0);
				editWord.setText(word.getText().toString());
				wordTrans.setText(editWordTrans.getText().toString());
			}	
		});
		
		wordTrans.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				butList = LearnActivity.getButtons();
				LearnActivity.modifyButtonsVisibility(butList.get(0),4,butList.get(1),0,butList.get(2),0,butList.get(3),4);
				
				wordTrans.setVisibility(4);
				editWordTrans.setVisibility(0);
				word.setVisibility(0);
				editWord.setVisibility(4);
				word.setText(editWord.getText().toString());
				editWordTrans.setText(wordTrans.getText().toString());
			}	
		});

		((LearnActivity)getActivity()).setListenerActionMode(v);
		
		return v;
	}


	public Word_Translation getCurrentWord_T() {
		return word_obj;
	}

	public void setCurrentWord_T(Word_Translation toto){
		word_obj=toto;
	}
	
	public TextView[] getVisibleViews() {
		TextView[] displayedViews = new TextView[2];
		
		if(word.getVisibility() == View.VISIBLE)
			displayedViews[0] = word; //1 if the textview is currently displayed
		else
			displayedViews[0] = editWord; //2 if the edittext is currently displayed
		
		if(wordTrans.getVisibility() == View.VISIBLE)
			displayedViews[1] = wordTrans;
		else
			displayedViews[1] = editWordTrans;
		
		return displayedViews;
	}

	public boolean getCurrentStatus() {
		return modified;
	}

	public void setCurrentStatus(boolean modified){
		this.modified=modified;
	}
}
