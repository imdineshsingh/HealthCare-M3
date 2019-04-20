package com.dinesh_singh.healthcare.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinesh_singh.healthcare.R;

import java.util.Objects;

public class TutorialViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflator;
    private int[] images;
    private String[] mTitle={
            "LOREM ISPUM SIT",
            "LOREM ISPUM SIT",
            "LOREM ISPUM SIT"
    };
    private String[] mDescription={
            "Lorem ipsum dolor sit amet, consectetur\n ipiscing elit, set do eiusmod, temporig\n dunt ut albore et dolore.",
            "Lorem ipsum dolor sit amet, consectetur\n  ipiscing elit, set do eiusmod,temporig\n dunt ut albore et dolore.",
            "Lorem ipsum dolor sit amet, consectetur\n ipiscing elit, set do eiusmod, temporig\n dunt ut albore et dolore."
    };
    public TutorialViewPagerAdapter(Context mContext,int[] images) {
        this.mContext=mContext;
        this.images=images;
    }
    @Override
    public int getCount() {
        return mTitle.length;

    }
    @Override
    public boolean isViewFromObject( @NonNull View view, @NonNull Object object) {
        return view==(LinearLayout)object;
    }
    @NonNull
    @Override
    public Object instantiateItem( @NonNull ViewGroup container, int position) {
        mLayoutInflator= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= mLayoutInflator.inflate(R.layout.tutorial_item_slider,container,false);
        ImageView tutorialImage=view.findViewById(R.id.iv_tutotial);
        TextView title=view.findViewById(R.id.tv_title);
        TextView desc=view.findViewById(R.id.tv_desc);
        tutorialImage.setImageResource(images[position]);
        title.setText(mTitle[position]);
        desc.setText(mDescription[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem( @NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
