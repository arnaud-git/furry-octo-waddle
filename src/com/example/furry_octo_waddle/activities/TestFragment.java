package com.example.furry_octo_waddle.activities;

import java.io.Serializable;
import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.BD_rw.Order;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends Fragment implements Serializable, MyFragment{
	View v;
	private Word_Translation word_obj= null;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.word_layout, container, false);
		
		if(word_obj==null){
			List<Word_Translation> res = ((BaseActivity)getActivity()).action.getWordFromTable(new Word_Translation("%", "%", getArguments().getInt(Word_Translation.WORD_ID)),Order.NULL,-1);
			if(res.size()==1)
				word_obj = res.get(0);
			else 
				MainActivity.printDebug(1, "Renvoie liste trop longue");
		}
		((BaseActivity)getActivity()).setFragment(this);
		((BaseActivity)getActivity()).display_correct_word_views_TEST();	
		return v;
    }

	public static final Fragment newInstance(Word_Translation word_Translation) {
		TestFragment f = new TestFragment();
		Bundle bdl = new Bundle(1);
		bdl.putInt(Word_Translation.WORD_ID, word_Translation.getId());
	    f.setArguments(bdl);

	    return f;
	}
	
	

	@Override
	public View getViewPos(){
		return v;
	}

	@Override
	public Word_Translation getCurrentWord_T() {
		return word_obj;
	}
}
