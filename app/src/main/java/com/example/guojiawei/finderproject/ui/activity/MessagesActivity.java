package com.example.guojiawei.finderproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.MessageRecyclerAdapter;
import com.example.guojiawei.finderproject.adapter.listener.OnRecyclerViewItemClickListener;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.MessageEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.DividerItemDecoration;
import com.example.guojiawei.finderproject.widget.MessageDividerItemDecoration;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/10.
 */

public class MessagesActivity extends BaseActivity {
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SuperSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.right_iv)
    ImageView right_iv;

    private MessageRecyclerAdapter messageRecyclerAdapter;
    private int defaultPage = 1;
    private int defaultRows = 10;
    private int mPage = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_messages;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        setRecyclerView();
        right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_all) {//true:所有人。false：
                    right_iv.setImageResource(R.mipmap.f_you_true);
                } else {
                    right_iv.setImageResource(R.mipmap.f_you_false);
                }
                is_all = !is_all;
                getMyMessage(UserStatusUtil.getUserId(), defaultPage + "", defaultRows + "");
            }
        });
        getMyMessage(UserStatusUtil.getUserId(), defaultPage + "", defaultRows + "");
        messageRecyclerAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                startActivity(new Intent(getContext(), MessageDetailActivity.class)
                        .putExtra("user_re_id", messageRecyclerAdapter.getDatas().get(position).getUser().getId())
                        .putExtra("nickname", messageRecyclerAdapter.getDatas().get(position).getUser().getNickname()));

                messageRecyclerAdapter.getDatas().get(position).setUnshow("0");
                messageRecyclerAdapter.notifyItemChanged(position);
            }
        });
    }


    @OnClick(R.id.ic_back)
    public void onViewClicked() {
        finish();
    }


    private View createHeaderView() {
        //TODO 创建下拉刷新头部的View样式
        View view = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        return view;
    }

    private void setRecyclerView() {
        messageRecyclerAdapter = new MessageRecyclerAdapter(this);
        recyclerView.setAdapter(messageRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MessageDividerItemDecoration(this, MessageDividerItemDecoration.VERTICAL_LIST));
        swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        // add headerView
        swipeRefreshLayout.setHeaderView(createHeaderView());
        //下拉刷新
        setOnPullRefresh();
        //上拉加载更多
        setOnPushLoadMore();

    }

    private void setOnPullRefresh() {
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        //TODO 开始刷新
                        getMyMessage(UserStatusUtil.getUserId(), defaultPage + "", defaultRows + "");
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        //TODO 下拉距离
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                        //TODO 下拉过程中，下拉的距离是否足够出发刷新
                    }
                });
    }

    private void setOnPushLoadMore() {
        swipeRefreshLayout
                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {

                    @Override
                    public void onLoadMore() {
                        mPage++;
                        getMyMessage(UserStatusUtil.getUserId(), mPage + "", defaultRows + "");
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        //TODO 上拉过程中，上拉的距离是否足够出发刷新
                    }

                    @Override
                    public void onPushDistance(int distance) {
                        // TODO 上拉距离

                    }

                });
    }

    boolean is_all = false;

    private void getMyMessage(String user_id, String page, String rows) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("page", page);
        params.put("rows", rows);
        EncryptUtil.EncryptAutoSort(params);
//        String url = API.MY_MESSAGE;
        String url = API.MY_FOLLOW_TO;//关注我的人
        if (is_all) url = API.MY_FOLLOW_MY;//我关注的人
        Observable<Response<String>> observable = OkGo.<String>post(url)
                .params(params, false)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefreshLayout.setRefreshing(false);
                        swipeRefreshLayout.setLoadMore(false);
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        MessageEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), MessageEntity.class);
                        if (entity.getCode() == 1) {
                            if (entity.getData().getRows().size() > 0) {
                                swipeRefreshLayout.setVisibility(View.VISIBLE);
                                tvNothing.setVisibility(View.GONE);
                            }
                            if (mPage == 1) {
                                messageRecyclerAdapter.refreshDatas(entity.getData().getRows());

                            } else {
                                messageRecyclerAdapter.addItems(entity.getData().getRows());

                            }
                            swipeRefreshLayout.setRefreshing(false);
                            swipeRefreshLayout.setLoadMore(false);
                        } else {
                            swipeRefreshLayout.setRefreshing(false);
                            swipeRefreshLayout.setLoadMore(false);
                        }
                    }
                });
    }

}
