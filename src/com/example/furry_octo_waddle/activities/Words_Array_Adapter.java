package com.example.furry_octo_waddle.activities;

import java.util.List;

import com.example.furry_octo_waddle.R;
import com.example.furry_octo_waddle.sql_manager.Word_Translation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Words_Array_Adapter extends ArrayAdapter<Word_Translation> {
	private final Context context;
	private final  List<Word_Translation> values;

	public Words_Array_Adapter(Context context, int resource, List<Word_Translation> objects) {
		super(context, resource, objects);
		values = objects;
		this.context = context;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_layout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		TextView textView_t= (TextView) rowView.findViewById(R.id.label_t);
		textView.setText(values.get(position).getWord());
		textView_t.setText(values.get(position).getTraduction_of_word());

		return rowView;
	}
}
