package com.example.guojiawei.finderproject.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.util.BitMapUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by guojiawei on 2017/11/28.
 */

@SuppressLint("ValidFragment")
public class ImageFragment extends Fragment {

    @BindView(R.id.image)
    PhotoView image;
    Unbinder unbinder;

    private String url = "";

    @SuppressLint("ValidFragment")
    public ImageFragment(String s){
        this.url = s;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        unbinder = ButterKnife.bind(this, view);
        Glide.with(getContext()).load(url).into(image);
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
