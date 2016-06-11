package com.example.furry_octo_waddle.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.furry_octo_waddle.R;

public class LearnFragment extends Fragment {

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	
	public static final LearnFragment newInstance(String[] words)
	{
		LearnFragment f = new LearnFragment();
		Bundle bdl = new Bundle(1);
		bdl.putStringArray(EXTRA_MESSAGE, words);
	    f.setArguments(bdl);
	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		String[] words = getArguments().getStringArray(EXTRA_MESSAGE);
		View v = inflater.inflate(R.layout.learn_words_layout, container, false);
		TextView word = (TextView)v.findViewById(R.id.word);
		
		word.setText(words[0]);
		TextView wordTrad = (TextView)v.findViewById(R.id.wordTrad);
		wordTrad.setText(words[1]);
		return v;
    }
	
}
