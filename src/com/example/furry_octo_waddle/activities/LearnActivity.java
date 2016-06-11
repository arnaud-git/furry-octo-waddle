package com.example.furry_octo_waddle.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.furry_octo_waddle.R;

public class LearnActivity extends FragmentActivity {
	MyPageAdapter pageAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_layout);

		List<Fragment> fragments = getFragments();

		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

		ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);

	}

	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();

		String word1[] = {"ingrained","enraciné"};
		String word2[] = {"to enthrall","captiver"};
		String word3[] = {"a ribbon","un ruban"};

		fList.add(LearnFragment.newInstance(word1));
		fList.add(LearnFragment.newInstance(word2));
		fList.add(LearnFragment.newInstance(word3));

		return fList;
	}

	private class MyPageAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}
		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}
}