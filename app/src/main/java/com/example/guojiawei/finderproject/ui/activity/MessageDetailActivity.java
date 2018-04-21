package com.example.guojiawei.finderproject.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.adapter.MessageDetailAdapter;
import com.example.guojiawei.finderproject.adapter.PreviewPagerAdapter;
import com.example.guojiawei.finderproject.adapter.listener.OnRecyclerViewItemClickListener;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.entity.CodeEntity;
import com.example.guojiawei.finderproject.entity.MessageDetailEntity;
import com.example.guojiawei.finderproject.net.API;
import com.example.guojiawei.finderproject.ui.Video_playAty;
import com.example.guojiawei.finderproject.ui.fragment.ContentFragment;
import com.example.guojiawei.finderproject.ui.fragment.ImageFragment;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.util.EncryptUtil;
import com.example.guojiawei.finderproject.util.GsonUtil;
import com.example.guojiawei.finderproject.util.UserStatusUtil;
import com.example.guojiawei.finderproject.widget.MessageDividerItemDecoration;
import com.example.guojiawei.finderproject.widget.dialog.ContentPreviewDialog;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelector;
import com.example.guojiawei.finderproject.widget.dialog.DialogSelectorListener;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx.adapter.ObservableResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guojiawei on 2017/11/24.
 */

public class MessageDetailActivity extends BaseActivity {
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SuperSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private MessageDetailAdapter messageDetailAdapter;
    private ImageFragment imageFragment;
    private ContentFragment contentFragment;
    private PreviewPagerAdapter adapter;
    private List<Fragment> datas;
    private GestureDetector tapGestureDetector;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        title.setText(getIntent().getStringExtra("nickname"));
        setRecyclerView();
        getMoodMessage("1", "20", "", getIntent().getStringExtra("user_re_id"));
        setPageClickListener();

    }

    private void setPageClickListener() {
        tapGestureDetector = new GestureDetector(this, new TapGestureListener());
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    class TapGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // Your Code here
            viewpager.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            return true;
        }
    }

    class FlipHorizontalTransformer extends TransformAdapter {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onTransform(View view, float position) {
//            float rotation = 180f * position;
//
//            view.setTranslationX(view.getWidth() * -position);
//            view.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
//            view.setPivotX(view.getWidth() * 0.5f);
//            view.setPivotY(view.getHeight() * 0.5f);
//            view.setRotationY(rotation);
//
//            if (position > -0.5f && position < 0.5f) {
//                view.setVisibility(View.VISIBLE);
//            } else {
//                view.setVisibility(View.INVISIBLE);
//            }
        }
    }

    abstract class TransformAdapter implements ViewPager.PageTransformer {

        private boolean debug = true;

        @Override
        public void transformPage(View page, float position) {

            if (position > 0 && position <= 1) {
                onRightScorlling(page, position);
            } else if (position < 0 && position >= -1) {
                onLeftScorlling(page, position);
            } else if (position == 0) {
                onCenterIdle(page);
            }
            onTransform(page, position);
        }

        /**
         * @param view     right view
         * @param position right to center 1->0
         *                 center to right 0->1
         */
        public void onRightScorlling(View view, float position) {

        }

        /**
         * @param view     left view
         * @param position left to center  -1->0
         *                 center to left  0->-1
         */
        public void onLeftScorlling(View view, float position) {

        }

        public void onCenterIdle(View view) {

        }

        /**
         * @param view     left and right view both callback
         * @param position [-1,1]
         */
        public void onTransform(View view, float position) {

        }

        public void log(Class<? extends TransformAdapter> clazz, String msg) {
            if (debug) {
                Log.d(clazz.getSimpleName(), msg);
            }
        }
    }

    private void setRecyclerView() {
        messageDetailAdapter = new MessageDetailAdapter(this);
        recyclerView.setAdapter(messageDetailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MessageDividerItemDecoration(this, MessageDividerItemDecoration.VERTICAL_LIST));
        swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        // add headerView
        swipeRefreshLayout.setHeaderView(createHeaderView());
        //下拉刷新
        setOnPullRefresh();
        //上拉加载更多
        setOnPushLoadMore();

        messageDetailAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                if (!UserStatusUtil.getUserId().equals(messageDetailAdapter.getDatas().get(position).getUser_id())) {

//                    final ContentPreviewDialog dialog = new ContentPreviewDialog(getContext(),
//                            messageDetailAdapter.getDatas().get(position).getContent().toString());
//                    dialog.show();
//                    startActivity(new Intent(MessageDetailActivity.this, DetailActivity.class)
//                            .putExtra(Constant.TAG_MOOD_ID, messageDetailAdapter.getDatas().get(position).getId()));
                    startActivity(new Intent(MessageDetailActivity.this, ReplyMessageActivity.class)
                            .putExtra(Constant.TAG_MOOD_ID, messageDetailAdapter.getDatas().get(position).getMood_id())
                            .putExtra(Constant.TAG_COMMENT_ID, messageDetailAdapter.getDatas().get(position).getId())
                            .putExtra("name", messageDetailAdapter.getDatas().get(position).getUser().getNickname()));

                } else {
                    final DialogSelector dialogSelector = new DialogSelector(MessageDetailActivity.this);
                    dialogSelector.setTitle("删除");
                    dialogSelector.setListener(new DialogSelectorListener() {
                        @Override
                        public void onSure() {
                            delMessage(UserStatusUtil.getUserId(), messageDetailAdapter.getDatas().get(position).getId(), position);
                        }

                        @Override
                        public void onCancel() {
                            dialogSelector.dismiss();
                        }
                    });
                    dialogSelector.show();
                }
            }

        });

        messageDetailAdapter.setOnImagePreviewClickListener(new MessageDetailAdapter.OnImagePreviewClickListener() {
            @Override
            public void onClick(View v, String url, int postion) {
                initFragment(url, messageDetailAdapter.getDatas().get(postion).getContent());
                viewpager.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setVisibility(View.GONE);
            }

            public void play(View v, int position) {
                String videoid = messageDetailAdapter.getDatas().get(position).getMood().getVideo_id();
                Intent intent = new Intent(MessageDetailActivity.this, Video_playAty.class);
                intent.putExtra("videoid", videoid);
                startActivity(intent);
            }
        });

    }

    private void initFragment(String url, String Content) {
        imageFragment = new ImageFragment(url);
        contentFragment = new ContentFragment(Content);
        datas = new ArrayList<>();
        datas.add(imageFragment);
        //datas.add(contentFragment);
        initPagerAdapter();
        contentFragment.setOnPagerContentClickListener(new ContentFragment.OnPagerContentClickListener() {
            @Override
            public void onClick() {
                viewpager.setVisibility(View.GONE);
            }
        });
    }

    private void initPagerAdapter() {
        //viewpager.setPageTransformer(true, new FlipHorizontalTransformer());
        adapter = new PreviewPagerAdapter(getSupportFragmentManager(), datas);
        viewpager.setAdapter(adapter);
    }

    private View createHeaderView() {
        //TODO 创建下拉刷新头部的View样式
        View view = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        return view;
    }


    private void setOnPullRefresh() {
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        //TODO 开始刷新
                        getMoodMessage("1", "10", "", getIntent().getStringExtra("user_re_id"));
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


    private void getMoodMessage(String page, String rows, String mood_id, String user_re_id) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("mood_id", "");
        params.put("user_id", UserStatusUtil.getUserId());
        params.put("user_re_id", user_re_id);
        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.MOOD_MESSAGE)
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
                        closeRefush();
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        closeRefush();
                        MessageDetailEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), MessageDetailEntity.class);
                        if (entity.getCode() == 1) {
                            if (entity.getData() != null) {
                                messageDetailAdapter.refreshDatas(entity.getData().getRows());
                            }
                        }

                    }
                });
    }

    private void delMessage(String user_id, String comment_id, final int postion) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("comment_id", comment_id);

        EncryptUtil.EncryptAutoSort(params);

        Observable<Response<String>> observable = OkGo.<String>post(API.DEL_MESSAGE)
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
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        CodeEntity entity = GsonUtil.GosnToEntity(stringResponse.body(), CodeEntity.class);
                        if (entity.getCode() == 1) {
                            showToast("删除成功");
                            messageDetailAdapter.remove(postion);
                        }
                    }
                });
    }

    @OnClick(R.id.ic_back)
    public void onViewClicked() {
        finish();
    }

    private void closeRefush() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setLoadMore(false);
    }
}
