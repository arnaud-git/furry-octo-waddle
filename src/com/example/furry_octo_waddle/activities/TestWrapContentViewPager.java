package com.example.furry_octo_waddle.activities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TestWrapContentViewPager extends WrapContentViewPager {

    public TestWrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }
}
