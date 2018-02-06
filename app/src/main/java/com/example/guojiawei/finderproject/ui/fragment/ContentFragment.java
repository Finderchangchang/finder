package com.example.guojiawei.finderproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guojiawei.finderproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by guojiawei on 2017/11/28.
 */

public class ContentFragment extends Fragment {
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;

    private OnPagerContentClickListener onPagerContentClickListener;
    private String str = "";

    public ContentFragment(String s) {
        this.str = s;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);

        content.setText(str);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPagerContentClickListener != null) {
                    onPagerContentClickListener.onClick();
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnPagerContentClickListener {
        void onClick();
    }

    public void setOnPagerContentClickListener(OnPagerContentClickListener onPagerContentClickListener) {
        this.onPagerContentClickListener = onPagerContentClickListener;
    }
}
