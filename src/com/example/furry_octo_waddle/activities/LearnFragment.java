package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnFragment extends Fragment {

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	String currentWord, currentWordTrad;
		
	public static final LearnFragment newInstance(Word_Translation word_obj)
	{
		LearnFragment f = new LearnFragment();
		Bundle bdl = new Bundle(1);
		
		String[] str_array = {word_obj.getWord(),word_obj.getTraduction_of_word()};
		bdl.putStringArray(EXTRA_MESSAGE, str_array);
	    f.setArguments(bdl);
	    
	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.learn_words_layout, container, false);
		TextView word = (TextView)v.findViewById(R.id.word);
		TextView wordTrad = (TextView)v.findViewById(R.id.wordTrad);

		String[] words = getArguments().getStringArray(EXTRA_MESSAGE);
		
		currentWord = words[0];
		currentWordTrad = words[1];
		word.setText(currentWord);
		wordTrad.setText(currentWordTrad);		
		
		return v;
    }
	
	public String[] getCurrentWords() {
		String[] words = {currentWord,currentWordTrad};
		return words;
	}
}
