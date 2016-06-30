package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnFragment extends Fragment {

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	String currentWord, currentWordTrans;
	private Word_Translation word_obj;
		
	public static final LearnFragment newInstance(Word_Translation word_obj)
	{
		LearnFragment f = new LearnFragment();
		Bundle bdl = new Bundle(1);
		//TODO Replace (if possible) the string array into an instance of Word_Translation
		//String[] str_array = {word_obj.getWord(),word_obj.getTraduction_of_word()};
		String[] str_array = {word_obj.getWord(),word_obj.getTraduction_of_word(),Integer.toString(word_obj.getId())};
		bdl.putStringArray(EXTRA_MESSAGE, str_array);
	    f.setArguments(bdl);
	    
	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.word_layout, container, false);
		TextView word = (TextView)v.findViewById(R.id.tvWord);
		TextView wordTrans = (TextView)v.findViewById(R.id.tvWordTrans);

		String[] words = getArguments().getStringArray(EXTRA_MESSAGE);
		List<Word_Translation> res = MainActivity.cbd.getWordFromTable(new Word_Translation("%", "%", Integer.parseInt(words[2])),Order.NULL,-1);
		if(res.size()==1)
			word_obj = res.get(0);
		else 
			MainActivity.printDebug(1, "Renvoie liste trop longue");
		currentWord = words[0];
		currentWordTrans = words[1];
		word.setText(currentWord);
		wordTrans.setText(currentWordTrans);		
		
		return v;
    }
	
	public String[] getCurrentWords() {
		String[] words = {currentWord,currentWordTrans};
		return words;
	}
	
	public Word_Translation getCurrentWord_T() {
		return word_obj;
	}
	
	public void setCurrentWord_T(Word_Translation toto){
		word_obj=toto;
		currentWord = word_obj.getWord();
		currentWordTrans = word_obj.getTraduction_of_word();
	}
}
