package com.example.furry_octo_waddle.activities;

import com.example.furry_octo_waddle.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class TestFragment extends Fragment {

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	String word, wordTrans;
	EditText etTrad;
	TextView tvWord;
	View v;
	ViewPager pager;
		
	public static final TestFragment newInstance(String word, String wordTrans)
	{
		TestFragment f = new TestFragment();
		Bundle bdl = new Bundle(1);

		String[] str_array = {word, wordTrans};
		bdl.putStringArray(EXTRA_MESSAGE, str_array);
	    f.setArguments(bdl);

	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.test_words_layout, container, false);
		tvWord = (TextView)v.findViewById(R.id.word);
		etTrad = (EditText)v.findViewById(R.id.test_word);

		String[] words = getArguments().getStringArray(EXTRA_MESSAGE);
		pager = TestActivity.getPager();
		
		tvWord.setText(words[0]);
		wordTrans = words[1];
		
		etTrad.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if(etTrad.getText().toString().equals(wordTrans)) {
					pager.setCurrentItem(pager.getCurrentItem() + 1);
				}
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		return v;
    }
}
