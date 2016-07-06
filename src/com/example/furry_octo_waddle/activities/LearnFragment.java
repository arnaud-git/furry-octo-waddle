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

	private Word_Translation word_obj= null;
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
		TextView word = (TextView)v.findViewById(R.id.tvWord);
		TextView wordTrans = (TextView)v.findViewById(R.id.tvWordTrans);
		if(word_obj==null){
			List<Word_Translation> res = MainActivity.cbd.getWordFromTable(new Word_Translation("%", "%", getArguments().getInt(Word_Translation.WORD_ID)),Order.NULL,-1);
			if(res.size()==1)
				word_obj = res.get(0);
			else 
				MainActivity.printDebug(1, "Renvoie liste trop longue");
		}
		word.setText(word_obj.getWord());
		wordTrans.setText(word_obj.getTraduction_of_word());		
		((LearnActivity)getActivity()).setListenerActionMode(v);
		return v;
	}


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
}
