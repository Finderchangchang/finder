package com.example.guojiawei.finderproject.widget.camera;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.base.BaseActivity;
import com.example.guojiawei.finderproject.ui.MainActivity;
import com.example.guojiawei.finderproject.ui.activity.EditorActivity;
import com.example.guojiawei.finderproject.util.Constant;
import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.CameraFragmentApi;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultAdapter;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by guojiawei on 2017/11/11.
 */

public class CameraActivity extends BaseActivity {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.ic_deng)
    ImageView icDeng;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ic_switch)
    ImageView icSwitch;
    @BindView(R.id.clap)
    ImageView clap;
    @BindView(R.id.cancel)
    ImageView cancel;
    @BindView(R.id.sure)
    ImageView sure;
    @BindView(R.id.toptool)
    RelativeLayout toptool;
    @BindView(R.id.video_iv)
    ImageView video_iv;
    private CameraFragment cameraFragment;

    private boolean isClap = false;
    private boolean isDeng = false;
    private static final int REQUEST_CAMERA_PERMISSIONS = 931;


    private double mLat = 0;
    private double mLon = 0;

    private String url = "";

    @Override
    protected void onStart() {
        super.onStart();
        isClap = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {


        mLat = getIntent().getDoubleExtra(Constant.TAG_LAT, 0);
        mLon = getIntent().getDoubleExtra(Constant.TAG_LON, 0);


        //you can configure the fragment by the configuration builder
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        cameraFragment = CameraFragment.newInstance(new Configuration.Builder().build());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, cameraFragment, "camera")
                .commit();
        cameraFragment.setResultListener(new CameraFragmentResultListener() {
            @Override
            public void onVideoRecorded(String filePath) {

            }

            @Override
            public void onPhotoTaken(byte[] bytes, String filePath) {
                url = filePath;
                //    Toast.makeText(getBaseContext(), filePath +"      "+bytes.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        video_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(CameraActivity.this, VideoAcitvity.class)
//                        .putExtra(Constant.TAG_LAT, mLat)
//                        .putExtra(Constant.TAG_LON, mLon));
                setResult(1);
                finish();
            }
        });
    }


    @OnClick({R.id.ic_deng, R.id.back, R.id.ic_switch, R.id.clap, R.id.cancel, R.id.sure})
    public void onViewClicked(View view) {
        final CameraFragmentApi cameraFragment = getCameraFragment();
        switch (view.getId()) {
            case R.id.ic_deng:
                if (cameraFragment != null) {
                    if (isDeng == false) {
                        cameraFragment.toggleFlashMode();
                        cameraFragment.toggleFlashMode();
                        icDeng.setImageDrawable(getResources().getDrawable(R.drawable.ic_deng));
                        isDeng = true;
                    } else {
                        cameraFragment.toggleFlashMode();

                        icDeng.setImageDrawable(getResources().getDrawable(R.drawable.ic_deng_normal));
                        isDeng = false;
                    }

                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.ic_switch:
                if (cameraFragment != null) {
                    cameraFragment.switchCameraTypeFrontBack();
                }
                break;
            case R.id.clap:

                toptool.setVisibility(View.GONE);
                clap.setVisibility(View.GONE);
                sure.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                clapAnim(cancel, sure);
                if (cameraFragment != null && isClap == false) {
                    cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultAdapter() {
                                                               @Override
                                                               public void onVideoRecorded(String filePath) {
                                                               }

                                                               @Override
                                                               public void onPhotoTaken(byte[] bytes, String filePath) {
                                                               }
                                                           },
                            "/storage/self/primary",
                            System.currentTimeMillis() + "");
                    isClap = true;
                }

                break;
            case R.id.cancel:
                toptool.setVisibility(View.VISIBLE);
                clap.setVisibility(View.VISIBLE);
                sure.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);

                if (cameraFragment != null) {
                    isClap = false;
                    getSupportFragmentManager().findFragmentByTag("camera").onResume();
                }

                break;
            case R.id.sure:
                startActivity(new Intent(this, EditorActivity.class)
                        .putExtra(Constant.TAG_LAT, mLat)
                        .putExtra(Constant.TAG_LON, mLon)
                        .putExtra("url", url));
                finish();
                break;
        }
    }

    private CameraFragmentApi getCameraFragment() {
        return (CameraFragmentApi) getSupportFragmentManager().findFragmentByTag("camera");
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, R.anim.actionsheet_dialog_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void clapAnim(View left, View right) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(left, "translationX", 0f, -300f);
        animator.setDuration(300);
        animator.start();


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(right, "translationX", 0f, 300f);
        animator2.setDuration(300);
        animator2.start();
    }

}
