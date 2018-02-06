package com.example.guojiawei.finderproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guojiawei on 2017/11/28.
 */

public class PreviewPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private List<Fragment> datas = new ArrayList<>();

    public PreviewPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.datas.clear();
        this.datas.addAll(data);
    }



    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }


}
