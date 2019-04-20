package com.dinesh_singh.healthcare;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dinesh_singh.healthcare.Adapters.TutorialViewPagerAdapter;
import com.dinesh_singh.healthcare.databinding.ActivityTutorialScreenBinding;

public class TutorialScreenActivity extends AppCompatActivity {

    private ActivityTutorialScreenBinding getBinding;
    private Context mContext;
    private int[] images={
            R.mipmap.tutorial_img,
            R.mipmap.tutorial_img,
            R.mipmap.tutorial_img

    };

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        getBinding = DataBindingUtil.setContentView(this,R.layout.activity_tutorial_screen);

        getBinding.tutorialViewPager.setAdapter(new TutorialViewPagerAdapter(this,images));
        getBinding.myTab.setupWithViewPager(getBinding.tutorialViewPager,true);
        getBinding.myTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getBinding.tutorialViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        getBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(TutorialScreenActivity.this,SearchPatientActivity.class));
            }
        });
        getBinding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                startActivity(new Intent(TutorialScreenActivity.this,SearchPatientActivity.class));
            }
        });
    }
}
