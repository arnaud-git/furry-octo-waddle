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

public class LearnFragment extends Fragment implements MyFragment{

	private Word_Translation word_obj= null;
	TextView word, wordTrans;
	EditText editWord, editWordTrans;
	private View view ;
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

		View view = inflater.inflate(R.layout.word_layout, container, false);

		if(word_obj==null){
			word_obj = ((BaseActivity)getActivity()).action.getWordFromTable(getArguments().getInt(Word_Translation.WORD_ID));
		}
		MainActivity.printDebug(63,"Salut "+word_obj.getWord() );

		this.view=view;
		((LearnActivity)getActivity()).showFragment(this);		
		return view;
	}


	@Override
	public Word_Translation getCurrentWord_T() {
		return word_obj;
	}


	public void setCurrentWord_T(Word_Translation toto){
		word_obj=toto;
	}

	public boolean getCurrentStatus() {
		return modified;
	}

	public void setCurrentStatus(boolean modified){
		this.modified=modified;
	}

	@Override
	public View getViewPos(){
		return view;
	}

}
