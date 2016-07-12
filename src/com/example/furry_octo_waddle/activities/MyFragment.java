package com.example.furry_octo_waddle.activities;

import com.example.furry_octo_waddle.sql_manager.Word_Translation;
import android.view.View;

public interface MyFragment{
		public View getViewPos();
		public Word_Translation getCurrentWord_T();
	}