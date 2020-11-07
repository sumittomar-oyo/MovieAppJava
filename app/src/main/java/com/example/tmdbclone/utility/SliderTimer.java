package com.example.tmdbclone.utility;

import android.app.Activity;
import android.content.Context;

import androidx.viewpager.widget.ViewPager;

import java.util.TimerTask;

public class SliderTimer extends TimerTask {
    Activity context;
    ViewPager viewPager;
    int size;
    public SliderTimer(Activity context, ViewPager viewPager, int size){
        this.context = context;
        this.viewPager = viewPager;
        this.size = size;
    }

    @Override
    public void run() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() < size - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
                } else {
                    viewPager.setCurrentItem(0);
                }
            }
        });
    }
}
