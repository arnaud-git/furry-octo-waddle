package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

public class LearnFragment extends Fragment {

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	String actualWord, actualWordTrad;
		
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
		actualWord = words[0];
		actualWordTrad = words[1];
		word.setText(actualWord);
		wordTrad.setText(actualWordTrad);		
		
		return v;
    }
	
	public String actualId() {
		return actualWord;
	}
	
}
