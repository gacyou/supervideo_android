package com.supervideo;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.socks.library.KLog;
import com.supervideo.common.GetTabListResult;
import com.supervideo.common.SlidingTabLayout;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static Context mContext;

    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    private static GetTabListResult gt;
    private static AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    private List<FragmentItem> mTabs = new ArrayList<FragmentItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        gt = (GetTabListResult)getIntent().getSerializableExtra("GetTabListResult");

        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);

        for (int i = 0; i < gt.t.size(); ++i) {
            mTabs.add(new FragmentItem(
                    gt.t.get(i).TabName, // Title
                    Color.WHITE, // Indicator color
                    Color.GRAY // Divider color
            ));

            KLog.d("gaga",gt.t.get(i).TabName);
        }

        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mSlidingTabLayout.setViewPager(mViewPager);
    }



    private class AppSectionsPagerAdapter extends FragmentStatePagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mTabs.get(i).createFragment(i);
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }
    }

    static class FragmentItem {

        private final CharSequence mTitle;
        private final int mIndicatorColor;
        private final int mDividerColor;

        FragmentItem(CharSequence title, int indicatorColor, int dividerColor) {
            mTitle = title;
            mIndicatorColor = indicatorColor;
            mDividerColor = dividerColor;
        }

        Fragment createFragment(int i) {

            Bundle args = new Bundle();
            args.putInt("MenuId", gt.t.get(i).MenuId);

            SuperVideoFragment sf = new SuperVideoFragment();
            sf.setArguments(args);
            return sf;
        }

        CharSequence getTitle() {
            return mTitle;
        }
        int getIndicatorColor() {
            return mIndicatorColor;
        }
        int getDividerColor() {
            return mDividerColor;
        }
    }


    //螢幕旋轉不更新Active
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 什麼都不用寫
        }
        else {
            // 什麼都不用寫
        }
    }

}

